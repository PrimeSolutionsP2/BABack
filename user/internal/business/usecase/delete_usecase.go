package usecase

import (
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
)

type DeleteUsecase interface {
	DeleteById(id string) (*dto.ReponseMessageDTO, *dto.ReponseMessageDTO)
}

type deleteUsecase struct {
	deletegateway gateway.Deletegateway
}

func NewDeleteUsecase(deletegateway gateway.Deletegateway) *deleteUsecase {
	return &deleteUsecase{
		deletegateway: deletegateway,
	}
}

func (d *deleteUsecase) DeleteById(id string) (*dto.ReponseMessageDTO, *dto.ReponseMessageDTO) {
	state, err := d.deletegateway.DeleteById(id)
	if err != nil || !state {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}
	return &dto.ReponseMessageDTO{
		Code:    http.StatusOK,
		Message: "deleted succesfully",
	}, nil
}
