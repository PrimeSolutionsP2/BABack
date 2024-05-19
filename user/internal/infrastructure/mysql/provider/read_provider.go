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
	ReadByIdKey   = "read-by-id"
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

	err = row.Scan(&userDTO.Id, &userDTO.Name, &userDTO.LastName, &userDTO.PhoneNumber, &userDTO.Mail, &userDTO.Password, &userDTO.Role)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusNotFound,
			Message: constant.UserNotFound,
		}
	}
	return &domain.User{
		Id:          userDTO.Id,
		Name:        userDTO.Name,
		LastName:    userDTO.LastName,
		PhoneNumber: userDTO.PhoneNumber,
		Mail:        userDTO.Mail,
		Password:    userDTO.Password,
		Role:        userDTO.Role,
	}, nil
}

func (r *readProvider) ReadById(id string) (*domain.User, *domain.ErrorMessage) {
	var userDTO dto.UserDTO

	query := config.GetStringPropetyBykey(fmt.Sprintf(config.QueryKey, ReadByIdKey))

	stmt, err := r.connection.Prepare(query)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ConnectingDataBase,
		}
	}
	defer stmt.Close()

	row := stmt.QueryRow(id)

	err = row.Scan(&userDTO.Id, &userDTO.Name, &userDTO.LastName, &userDTO.PhoneNumber, &userDTO.Mail, &userDTO.Password, &userDTO.Role)
	if err != nil {
		return nil, &domain.ErrorMessage{
			Code:    http.StatusNotFound,
			Message: constant.UserNotFound,
		}
	}
	return &domain.User{
		Id:          userDTO.Id,
		Name:        userDTO.Name,
		LastName:    userDTO.LastName,
		PhoneNumber: userDTO.PhoneNumber,
		Mail:        userDTO.Mail,
		Password:    userDTO.Password,
		Role:        userDTO.Role,
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
		err = rows.Scan(&userDTO.Id, &userDTO.Name, &userDTO.LastName, &userDTO.PhoneNumber, &userDTO.Mail, &userDTO.Password, &userDTO.Role)
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
			Id:          dto.Id,
			Name:        dto.Name,
			LastName:    dto.LastName,
			PhoneNumber: dto.PhoneNumber,
			Mail:        dto.Mail,
			Password:    dto.Password,
			Role:        dto.Role,
		}
		users = append(users, user)
	}
	return &users
}
