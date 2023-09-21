package api

import (
	"go-tom/api/resp"
	"go-tom/service"

	"github.com/gin-gonic/gin"
)

type PaymentAPI struct {
	PaymentSvc *service.PaymentService
}

func NewPaymentAPI() *PaymentAPI {
	return &PaymentAPI{
		PaymentSvc: service.NewPaymentService(),
	}
}

func (p *PaymentAPI) Create(c *gin.Context) {
	resp.Success(c, nil)
}
