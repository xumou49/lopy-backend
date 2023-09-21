package routers

import (
	"go-tom/api"

	"github.com/gin-gonic/gin"
)

type Router struct {
	PaymentAPI *api.PaymentAPI
}

func NewRouter() Router {
	paymentAPI := api.NewPaymentAPI()
	return Router{
		PaymentAPI: paymentAPI,
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
	}
}

func (r *Router) registerOthers(app *gin.Engine) {
	// app.NoMethod(middleware.NoMethodHandler())
	// app.NoRoute(middleware.NoRouteHandler())
}
