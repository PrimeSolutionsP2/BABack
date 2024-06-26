package restapi

import "github.com/gin-gonic/gin"

func mapUrls(router *gin.Engine, dependencies *Dependencies) {
	groupPath := router.Group("/user/v1")
	groupPath.GET("/ping", dependencies.pingHandler.Ping)
	groupPath.GET("/get/users", dependencies.getUsecase.GetAll)
	groupPath.GET("/get/users/:user_id", dependencies.getUsecase.GetByID)
	groupPath.POST("/login/user", dependencies.loginHandler.CheckPassword)
	groupPath.POST("/create/user", dependencies.createHandler.Create)
	groupPath.DELETE("/delete/user/:user_id", dependencies.deleteUsecase.Delete)
	groupPath.POST("/send/email", dependencies.emailHandler.SendEmail)
}
