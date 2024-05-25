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
	DeleteKey = "delete-user"

)

type deleteProvider struct {
	connection *sql.DB
}

func NewDeleteProvider(connection *sql.DB) *deleteProvider {
	return &deleteProvider{
		connection: connection,
	}
}

func (d *deleteProvider) DeleteById(id string) (bool, *domain.ErrorMessage) {
	query := config.GetStringPropetyBykey(fmt.Sprintf(config.QueryKey, DeleteKey))

	stmt, err := d.connection.Prepare(query)
	if err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: err.Error(),
		}
	}
	defer stmt.Close()

	if _, err = d.connection.Exec("DELETE FROM pickup_request WHERE user_id = ?", id); err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorDeletingUser,
		}
    }

    // Delete from collection_point
    if _, err = d.connection.Exec("DELETE FROM collection_point WHERE user_id = ?", id); err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorDeletingUser,
		}
    }


	result, err := stmt.Exec(id)
	if err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorDeletingUser,
		}
	}
	

	rowsAffected, err := result.RowsAffected()
	if err != nil {
		return false, &domain.ErrorMessage{
			Code:    http.StatusInternalServerError,
			Message: constant.ErrorDeletingUser,
		}
	}

	if rowsAffected == 0 {
		return false, &domain.ErrorMessage{
			Code:    http.StatusNotFound,
			Message: constant.UserNotFoundById,
		}
	}

	return true, nil
}
