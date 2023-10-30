package routers

import (
	"go-tom/api"

	"github.com/gin-gonic/gin"
)

type Router struct {
	PaymentAPI       *api.PaymentAPI
	ServerCommandAPI *api.ServerCommandAPI
}

func NewRouter() Router {
	paymentAPI := api.NewPaymentAPI()
	serverCommandAPI := api.NewServerCommandAPI()
	return Router{
		PaymentAPI:       paymentAPI,
		ServerCommandAPI: serverCommandAPI,
	}
}

func (r *Router) Register(app *gin.Engine) {
	r.registerStaticFile(app)
	r.registerApi(app)
	r.registerOthers(app)
}

func (r *Router) registerStaticFile(app *gin.Engine) {
	app.GET("/favicon.ico", func(c *gin.Context) {
		c.String(200, "")
	})
}

func (r *Router) registerApi(app *gin.Engine) {
	v1 := app.Group("/api/v1")
	{
		payment := v1.Group("/payment")
		{
			payment.PUT("/create", r.PaymentAPI.Create)
		}
		serverCommand := v1.Group("/server_command")
		{
			serverCommand.GET("/rebuild_lopy_svc", r.ServerCommandAPI.RebuildLopySvc)
		}
	}
}

func (r *Router) registerOthers(app *gin.Engine) {
	// app.NoMethod(middleware.NoMethodHandler())
	// app.NoRoute(middleware.NoRouteHandler())
}
