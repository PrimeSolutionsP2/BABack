package usecase

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/constant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type EmailUsecase interface {
	SendEmail(c *gin.Context, email string, subject string, body string) (*dto.ReponseMessageDTO, *dto.ReponseMessageDTO)
}

type emailUsecase struct {
	emailGateway gateway.EmailGateway
}

func NewEmailUsecase(emailGateway gateway.EmailGateway) *emailUsecase {
	return &emailUsecase{
		emailGateway: emailGateway,
	}
}

func (cu *emailUsecase) SendEmail(c *gin.Context, email string, subject string, body string) (*dto.ReponseMessageDTO, *dto.ReponseMessageDTO) {
	sent, err := cu.emailGateway.SendEmail(email, subject, body)
	if err != nil && !sent {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	return &dto.ReponseMessageDTO{
		Code:    http.StatusAccepted,
		Message: constant.EmailAlreadySent,
	}, nil
}
