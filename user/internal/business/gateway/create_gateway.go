package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type CreateGateway interface {
	Create(id string, name string, lastName string, phoneNumber string, mail string, password string, userType int) (*domain.User, *domain.ErrorMessage)
}
