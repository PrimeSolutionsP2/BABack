package main

import "github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi"

func main() {
	deliveryStrategy := restapi.NewRestApi()
	deliveryStrategy.Start()
}
