package dto

type UserRequestDTO struct {
	Name     string `json:"name"`
	Mail     string `json:"mail"`
	Password string `json:"password"`
	Type     string `json:"type"`
}
