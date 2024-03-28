package usecase

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/constant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type CreateUsecase interface {
	Create(c *gin.Context, name string, mail string, password string, userType string) (*dto.UserDTO, *dto.ReponseMessageDTO)
}

type createUsecase struct {
	readGateway   gateway.ReadGateway
	createGateway gateway.CreateGateway
}

func NewCreateUsecase(readGateway gateway.ReadGateway, createGateway gateway.CreateGateway) *createUsecase {
	return &createUsecase{
		readGateway:   readGateway,
		createGateway: createGateway,
	}
}

func (cu *createUsecase) Create(c *gin.Context, name string, mail string, password string, userType string) (*dto.UserDTO, *dto.ReponseMessageDTO) {
	user, _ := cu.readGateway.ReadByMail(mail)
	if user != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.MailUsedError,
		}
	}

	user, err := cu.createGateway.Create(name, mail, password, userType)
	if err != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	return &dto.UserDTO{
		Id:   user.Id,
		Name: user.Name,
		Mail: user.Mail,
		Type: user.Type,
	}, nil
}
