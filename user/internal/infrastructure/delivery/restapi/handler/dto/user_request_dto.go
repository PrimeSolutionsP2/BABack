package dto

type UserRequestDTO struct {
	Id          string `json:"id"`
	Name        string `json:"name"`
	LastName    string `json:"last_name"`
	PhoneNumber string `json:"phone_number"`
	Mail        string `json:"mail"`
	Password    string `json:"password"`
	Type        int    `json:"type"`
}
