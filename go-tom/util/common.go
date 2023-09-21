package util

import (
	"go-tom/constant"

	"github.com/gin-gonic/gin"
)

func GetLocale(c *gin.Context) string {
	header := c.Request.Header
	locale := header.Get(constant.AuthUserLocaleHeader)
	if locale == "" {
		return constant.LocaleEnglish
	}
	return locale
}
