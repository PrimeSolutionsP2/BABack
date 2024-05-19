package dto

type UserDTO struct {
	Id          string `json:"id"`
	Name        string `json:"name"`
	LastName    string `json:"last_name"`
	PhoneNumber string `json:"phone_number,omitempty"`
	Mail        string `json:"mail,omitempty"`
	Role        int 	`json:"role,omitempty"`
}
