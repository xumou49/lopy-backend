package api

import (
	"go-tom/api/resp"
	"go-tom/service"

	"github.com/gin-gonic/gin"
)

type ServerCommandAPI struct {
	ServerCommandSvc *service.ServerCommandSvc
}

func NewServerCommandAPI() *ServerCommandAPI {
	return &ServerCommandAPI{
		ServerCommandSvc: service.NewServerCommandSvc(),
	}
}

func (s *ServerCommandAPI) RebuildLopySvc(c *gin.Context) {
	_ = s.ServerCommandSvc.Execute("/docker-compose/backend/lopy-svc/restart-with-build.sh", []string{})
	resp.Success(c, nil)
}
