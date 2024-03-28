package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type Deletegateway interface {
	DeleteById(id int) (bool, *domain.ErrorMessage)
}
