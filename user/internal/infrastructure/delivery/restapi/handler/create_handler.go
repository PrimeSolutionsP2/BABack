package handler

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type CreateHandler interface {
	Create(c *gin.Context)
}

type createHandler struct {
	createUsecase usecase.CreateUsecase
}

func NewCreateHandler(createUsecase usecase.CreateUsecase) *createHandler {
	return &createHandler{
		createUsecase: createUsecase,
	}
}

func (ch *createHandler) Create(c *gin.Context) {
	var body dto.UserRequestDTO

	if err := c.BindJSON(&body); err != nil {
		c.JSON(http.StatusBadRequest, dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.BodyError,
		})
		return
	}

	user, err := ch.createUsecase.Create(c, body.Id, body.Name, body.LastName, body.PhoneNumber, body.Mail, body.Type)

	if err != nil {
		c.JSON(err.Code, err)
		return
	}
	c.JSON(http.StatusOK, user)
}
