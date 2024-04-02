package handler

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	"github.com/gin-gonic/gin"
)

type GetHandler interface {
	GetAll(c *gin.Context)
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
