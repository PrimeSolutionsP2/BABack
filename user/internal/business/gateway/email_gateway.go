package gateway

import "github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"

type EmailGateway interface {
	SendEmail(mail string, subject string, body string) (bool, *domain.ErrorMessage)
}
