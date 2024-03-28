package email

import (
	"net/http"
	"net/smtp"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/config"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
)

type sendEmail struct {
	smtpHost string
	smtpPort string
	email    string
	auth     smtp.Auth
}

func NewSendEmail() *sendEmail {
	smtpHost := config.GetStringPropetyBykey(config.SmtHost)
	smtpPort := config.GetStringPropetyBykey(config.SmtPort)
	email := config.GetStringPropetyBykey(config.Email)
	password := config.GetStringPropetyBykey(config.EmailPass)

	auth := smtp.PlainAuth("", email, password, smtpHost)

	return &sendEmail{
		smtpHost: smtpHost,
		smtpPort: smtpPort,
		email:    email,
		auth:     auth,
	}
}

func (s *sendEmail) SendEmail(mail string, subject string, body string) (bool, *domain.ErrorMessage) {

	from := s.email
	to := []string{mail}

	msg := []byte("To: " + to[0] + "\r\n" +
		"Subject: " + subject + "\r\n" +
		"\r\n" +
		body + "\r\n")

	err := smtp.SendMail(s.smtpHost+":"+s.smtpPort, s.auth, from, to, msg)
	if err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorSendingEmail,
		}
	}
	return true, nil
}
