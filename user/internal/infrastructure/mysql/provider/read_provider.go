package provider

import (
	"database/sql"
	"fmt"
	"net/http"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/domain"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/config"
	constant "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/contant"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/mysql/provider/dto"
)

var (
	ReadByMailKey = "read-by-mail"
	ReadAllKey    = "read-all"
)

type readProvider struct {
	connection *sql.DB
}

func NewReadProvider(connection *sql.DB) *readProvider {
	return &readProvider{
		connection: connection,
	}
}

func (r *readProvider) ReadByMail(mail string) (*domain.User, *domain.ErrorMessage) {
	var userDTO dto.UserDTO

	query := config.GetStringPropetyBykey(fmt.Sprintf(config.QueryKey, ReadByMailKey))

	stmt, err := r.connection.Prepare(query)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ConnectingDataBase,
		}
	}
	defer stmt.Close()

	row := stmt.QueryRow(mail)

	err = row.Scan(&userDTO.Id, &userDTO.Mail, &userDTO.Name, &userDTO.Password, &userDTO.Type)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusNotFound,
			Message: constant.UserNotFound,
		}
	}
	return &domain.User{
		Id:       userDTO.Id,
		Mail:     userDTO.Mail,
		Name:     userDTO.Name,
		Password: userDTO.Password,
		Type:     userDTO.Type,
	}, nil
}

func (r *readProvider) ReadAll() (*[]domain.User, *domain.ErrorMessage) {
	usersDTO := []dto.UserDTO{}

	query := config.GetStringPropetyBykey(fmt.Sprintf(config.QueryKey, ReadAllKey))

	rows, err := r.connection.Query(query)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ConnectingDataBase,
		}
	}
	defer rows.Close()

	for rows.Next() {
		var userDTO dto.UserDTO
		err = rows.Scan(&userDTO.Id, &userDTO.Mail, &userDTO.Name, &userDTO.Password, &userDTO.Type)
		if err != nil {
			return nil, &domain.ErrorMessage{
				Code:    http.StatusInternalServerError,
				Message: constant.ErrorGettingData,
			}
		}
		usersDTO = append(usersDTO, userDTO)
	}
	return r.buildUsersDomain(&usersDTO), nil
}

func (r *readProvider) buildUsersDomain(usersDTO *[]dto.UserDTO) *[]domain.User {
	users := []domain.User{}
	for _, dto := range *usersDTO {
		user := domain.User{
			Id:       dto.Id,
			Mail:     dto.Mail,
			Name:     dto.Name,
			Password: dto.Password,
			Type:     dto.Type,
		}
		users = append(users, user)
	}
	return &users
}
