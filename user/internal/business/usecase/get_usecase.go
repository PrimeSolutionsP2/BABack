package usecase

import (
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/constant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"
	"github.com/PrimeSolutionsP2/BABack/user/internal/business/gateway"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler/dto"
	"github.com/gin-gonic/gin"
)

type GetUsecase interface {
	GetAll(c *gin.Context) (*[]dto.UserDTO, *dto.ReponseMessageDTO)
	GetByID(c *gin.Context, id string) (*dto.UserDTO, *dto.ReponseMessageDTO)
}

type getUsecase struct {
	readGateway gateway.ReadGateway
}

func NewGetUsecase(readGateway gateway.ReadGateway) *getUsecase {
	return &getUsecase{
		readGateway: readGateway,
	}
}

func (g *getUsecase) GetAll(c *gin.Context) (*[]dto.UserDTO, *dto.ReponseMessageDTO) {
	users, err := g.readGateway.ReadAll()
	if err != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: err.Message,
		}
	}

	return g.buildUsersDTO(users), nil
}

func (g *getUsecase) buildUsersDTO(users *[]domain.User) *[]dto.UserDTO {
	usersDTO := []dto.UserDTO{}
	for _, user := range *users {
		userDTO := dto.UserDTO{
			Id:          user.Id,
			Name:        user.Name,
			LastName:    user.LastName,
			PhoneNumber: user.PhoneNumber,
			Mail:        user.Mail,
			Role:        user.Role,
		}
		usersDTO = append(usersDTO, userDTO)
	}
	return &usersDTO
}

func (g *getUsecase) GetByID(c *gin.Context, id string) (*dto.UserDTO, *dto.ReponseMessageDTO) {
	user, err := g.readGateway.ReadById(id)
	if err != nil {
		return nil, &dto.ReponseMessageDTO{
			Code:    err.Code,
			Message: constant.UserNotFoundById,
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
