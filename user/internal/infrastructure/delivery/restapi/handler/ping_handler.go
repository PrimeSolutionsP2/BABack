package handler

import "github.com/gin-gonic/gin"

type PingHanlder interface {
	Ping(c *gin.Context)
}

type pingHandler struct{}

func NewPingHandler() *pingHandler {
	return &pingHandler{}
}

func (p *pingHandler) Ping(c *gin.Context) {
	c.IndentedJSON(200, "pong")
}
