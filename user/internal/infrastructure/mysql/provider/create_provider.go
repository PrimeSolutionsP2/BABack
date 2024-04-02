package provider

import (
	"database/sql"
	"fmt"
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/config"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
)

var (
	CreateKey = "create-user"
)

type createProvider struct {
	connection *sql.DB
}

func NewCreateProvider(connection *sql.DB) *createProvider {
	return &createProvider{
		connection: connection,
	}
}

func (c *createProvider) Create(id string, name string, lastName string, phoneNumber string, mail string, password string, userType int) (*domain.User, *domain.ErrorMessage) {
	query := config.GetStringPropetyBykey(fmt.Sprintf(config.QueryKey, CreateKey))

	stmt, err := c.connection.Prepare(query)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ConnectingDataBase,
		}
	}
	defer stmt.Close()

	_, err = stmt.Exec(id, name, lastName, phoneNumber, mail, password, userType)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorCreatingUser,
		}
	}

	return &domain.User{
		Id:       id,
		Name:     name,
		LastName: lastName,
		Mail:     mail,
	}, nil
}
