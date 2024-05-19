package usecase

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/constant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type LoginUsecase interface {
	CheckPassword(c *gin.Context, mail string, password string) (*dto.UserDTO, *dto.ReponseMessageDTO)
}

type loginUsecase struct {
	readGateway gateway.ReadGateway
}

func NewLoginUsecase(readGateway gateway.ReadGateway) *loginUsecase {
	return &loginUsecase{
		readGateway: readGateway,
	}
}

func (l *loginUsecase) CheckPassword(c *gin.Context, mail string, password string) (*dto.UserDTO, *dto.ReponseMessageDTO) {
	user, err := l.readGateway.ReadByMail(mail)
	if err != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	if user.Password != password {
		return nil, &dto.ReponseMessageDTO{
			Code:    http.StatusUnauthorized,
			Message: constant.InvalidPassword,
		}
	}

	return &dto.UserDTO{
		Id:          user.Id,
		Name:        user.Name,
		LastName:    user.LastName,
		PhoneNumber: user.PhoneNumber,
		Mail:        user.Mail,
		Role:        user.Role,
	}, nil
}
