package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type Deletegateway interface {
	DeleteById(id string) (bool, *domain.ErrorMessage)
}
