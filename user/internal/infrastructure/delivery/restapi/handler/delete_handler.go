package handler

import (
	"net/http"
	"strconv"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
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
	userId, err := strconv.Atoi(userIdParam)
	if err != nil {
		c.JSON(http.StatusBadRequest, dto.ReponseMessageDTO{
			Code:    http.StatusBadRequest,
			Message: constant.InvalidParams,
		})
		return
	}

	response, errDelete := d.deleteUsecase.DeleteById(userId)
	if errDelete != nil {
		c.JSON(errDelete.Code, errDelete)
		return
	}
	c.JSON(response.Code, response)
}
