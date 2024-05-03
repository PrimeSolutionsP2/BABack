package handler

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type EmailHandler interface {
	SendEmail(c *gin.Context)
}

type emailHandler struct {
	emailUsecase usecase.EmailUsecase
}

func NewEmailHandler(emailUsecase usecase.EmailUsecase) *emailHandler {
	return &emailHandler{
		emailUsecase: emailUsecase,
	}
}

func (e *emailHandler) SendEmail(c *gin.Context) {
	var body dto.Email

	if err := c.BindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.BodyError,
		})
		return
	}

	response, err := e.emailUsecase.SendEmail(c, body.Email, body.Subject, body.Body)

	if err != nil {
		c.JSON(err.Code, err)
		return
	}

	c.JSON(http.StatusOK, response)
}
