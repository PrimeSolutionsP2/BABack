package restapi

import (
	"database/sql"
	"fmt"

	"github.com/PrimeSolutionsP2/BABack/user/internal/business/usecase"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/config"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/delivery/restapi/handler"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/email"
	"github.com/PrimeSolutionsP2/BABack/user/internal/infrastructure/mysql/provider"
	_ "github.com/go-sql-driver/mysql"
)

type Dependencies struct {
	pingHandler   handler.PingHanlder
	loginHandler  handler.LoginHandler
	createHandler handler.CreateHandler
	getUsecase    handler.GetHandler
	deleteUsecase handler.DeleteHandler
	emailHandler  handler.EmailHandler
}

func buildDependencies() *Dependencies {
	portDB := config.GetStringPropetyBykey(config.PortDB)
	urlDB := config.GetStringPropetyBykey(config.IpDB)
	userDB := config.GetStringPropetyBykey(config.UserDB)
	passwordDB := config.GetStringPropetyBykey(config.PasswordDB)
	nameDB := config.GetStringPropetyBykey(config.NameDB)
	url := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s", userDB, passwordDB, urlDB, portDB, nameDB)
	dbConnection, err := sql.Open("mysql", url)
	if err != nil {
		panic(err)
	}

	//provider
	readGateway := provider.NewReadProvider(dbConnection)
	createGateway := provider.NewCreateProvider(dbConnection)
	deleteGateway := provider.NewDeleteProvider(dbConnection)
	emailGateway := email.NewSendEmail()

	//usecase
	loginUsecase := usecase.NewLoginUsecase(readGateway)
	createUsecase := usecase.NewCreateUsecase(readGateway, createGateway, emailGateway)
	getUsecase := usecase.NewGetUsecase(readGateway)
	deleteUsecase := usecase.NewDeleteUsecase(deleteGateway)
	sendEmailUsecase := usecase.NewEmailUsecase(emailGateway)

	return &Dependencies{
		pingHandler:   handler.NewPingHandler(),
		loginHandler:  handler.NewLoginHandler(loginUsecase),
		createHandler: handler.NewCreateHandler(createUsecase),
		getUsecase:    handler.NewGetHandler(getUsecase),
		deleteUsecase: handler.NewDeleteHandler(deleteUsecase),
		emailHandler:  handler.NewEmailHandler(sendEmailUsecase),
	}
}
