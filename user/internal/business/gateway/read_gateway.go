package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type ReadGateway interface {
	ReadByMail(mail string) (*domain.User, *domain.ErrorMessage)
	ReadAll() (*[]domain.User, *domain.ErrorMessage)
}
