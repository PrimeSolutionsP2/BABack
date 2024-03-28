package dto

type UserDTO struct {
	Id   int    `json:"id"`
	Name string `json:"name"`
	Mail string `json:"mail"`
	Type string `json:"type"`
}
