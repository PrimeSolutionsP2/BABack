package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type ReadGateway interface {
	ReadById(id string) (*domain.User, *domain.ErrorMessage)
	ReadByMail(mail string) (*domain.User, *domain.ErrorMessage)
	ReadAll() (*[]domain.User, *domain.ErrorMessage)
}
