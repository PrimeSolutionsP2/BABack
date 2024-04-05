package handler

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type LoginHandler interface {
	CheckPassword(c *gin.Context)
}

type loginHandler struct {
	loginUserUsecase usecase.LoginUsecase
}

func NewLoginHandler(loginUserUsecase usecase.LoginUsecase) *loginHandler {
	return &loginHandler{
		loginUserUsecase: loginUserUsecase,
	}
}

func (l *loginHandler) CheckPassword(c *gin.Context) {
	var body dto.UserRequestDTO

	if err := c.BindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.BodyError,
		})
		return
	}

	user, err := l.loginUserUsecase.CheckPassword(c, body.Mail, body.Password)

	if err != nil {
		c.JSON(err.Code, err)
		return
	}
	c.JSON(http.StatusOK, user)
}
