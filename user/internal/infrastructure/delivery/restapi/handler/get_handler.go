package handler

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/gin-gonic/gin"
)

type GetHandler interface {
	GetAll(c *gin.Context)
	GetByID(c *gin.Context)
}

type getHandler struct {
	getUsecase usecase.GetUsecase
}

func NewGetHandler(getUsecase usecase.GetUsecase) *getHandler {
	return &getHandler{
		getUsecase: getUsecase,
	}
}

func (g *getHandler) GetAll(c *gin.Context) {
	users, err := g.getUsecase.GetAll(c)
	if err != nil {
		c.JSON(err.Code, err)
		return
	}

	c.JSON(http.StatusOK, users)
}

func (g *getHandler) GetByID(c *gin.Context) {
	userIdParam := c.Param(constant.UserId)

	response, err := g.getUsecase.GetByID(c, userIdParam)
	if err != nil {
		c.JSON(err.Code, err)
		return
	}
	c.JSON(http.StatusOK, response)
}
