package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type CreateGateway interface {
	Create(name string, mail string, password string, userType string) (*domain.User, *domain.ErrorMessage)
}
