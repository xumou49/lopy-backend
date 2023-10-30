package service

import (
	"os/exec"

	log "github.com/dzhcool/sven/zapkit"
	"go.uber.org/zap"
)

type ServerCommandSvc struct{}

func NewServerCommandSvc() *ServerCommandSvc {
	return &ServerCommandSvc{}
}

func (s *ServerCommandSvc) Execute(command string, args []string) error {
	cmd := exec.Command(command, args...)
	out, err := cmd.CombinedOutput()
	log.Info("execute", zap.String("output", string(out)))
	if err != nil {
		log.Info("execute", zap.String("err", err.Error()))
	}
	return nil
}
