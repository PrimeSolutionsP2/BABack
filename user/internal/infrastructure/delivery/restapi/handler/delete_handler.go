package handler

import (
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/gin-gonic/gin"
)

type DeleteHandler interface {
	Delete(c *gin.Context)
}

type deleteHandler struct {
	deleteUsecase usecase.DeleteUsecase
}

func NewDeleteHandler(deleteUsecase usecase.DeleteUsecase) *deleteHandler {
	return &deleteHandler{
		deleteUsecase: deleteUsecase,
	}
}

func (d *deleteHandler) Delete(c *gin.Context) {
	userIdParam := c.Param(constant.UserId)

	response, errDelete := d.deleteUsecase.DeleteById(userIdParam)
	if errDelete != nil {
		c.JSON(errDelete.Code, errDelete)
		return
	}
	c.JSON(response.Code, response)
}
