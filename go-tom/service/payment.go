package service

type PaymentService struct{}

func NewPaymentService() *PaymentService {
	return &PaymentService{}
}

func (p *PaymentService) Create() {}
