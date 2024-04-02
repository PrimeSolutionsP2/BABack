package restapi

import (
	"fmt"

	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/config"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery"
	"github.com/gin-gonic/gin"
)

type restApi struct{}

func NewRestApi() delivery.Strategy {
	return &restApi{}
}

func (r *restApi) Start() {
	config.LoadConfigs()
	dependencies := buildDependencies()

	router := gin.Default()

	mapUrls(router, dependencies)

	router.Run(fmt.Sprintf(":%s", config.GetStringPropetyBykey(config.Port)))
}
