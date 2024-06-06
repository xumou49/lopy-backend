package api

import (
	"go-tom/api/resp"
	"go-tom/service"

	log "github.com/dzhcool/sven/zapkit"
	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
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
	err := s.ServerCommandSvc.Execute("/docker-compose/backend/lopy-svc/restart-with-build.sh", []string{})
	if err != nil {
		log.Info("RebuildLopySvc failed", zap.Error(err))
	}
	resp.Success(c, nil)
}
