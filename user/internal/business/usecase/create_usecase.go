package usecase

import (
	"fmt"
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/constant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
	"github.com/sethvargo/go-password/password"
)

type CreateUsecase interface {
	Create(c *gin.Context, id string, name string, lastName string, phoneNumber string, mail string, userType int) (*dto.UserDTO, *dto.ReponseMessageDTO)
}

type createUsecase struct {
	readGateway   gateway.ReadGateway
	createGateway gateway.CreateGateway
	emailGateway  gateway.EmailGateway
}

func NewCreateUsecase(readGateway gateway.ReadGateway, createGateway gateway.CreateGateway, emailGateway gateway.EmailGateway) *createUsecase {
	return &createUsecase{
		readGateway:   readGateway,
		createGateway: createGateway,
		emailGateway:  emailGateway,
	}
}

func (cu *createUsecase) Create(c *gin.Context, id string, name string, lastName string, phoneNumber string, mail string, userType int) (*dto.UserDTO, *dto.ReponseMessageDTO) {
	user, _ := cu.readGateway.ReadByMail(mail)
	if user != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.MailUsedError,
		}
	}

	user, _ = cu.readGateway.ReadById(id)
	if user != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.IdUsedError,
		}
	}

	pass, errPass := password.Generate(16, 10, 0, false, false)
	if errPass != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorGeneratingPassword,
		}
	}

	user, err := cu.createGateway.Create(id, name, lastName, phoneNumber, mail, pass, userType)
	if err != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	subject := constant.EmailCredentialSubject
	body := fmt.Sprintf(constant.EmailCredentualBody, mail, pass)
	sent, err := cu.emailGateway.SendEmail(mail, subject, body)
	if err != nil && !sent {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	return &dto.UserDTO{
		Id:       user.Id,
		Name:     user.Name,
		LastName: user.LastName,
		Mail:     user.Mail,
	}, nil
}
