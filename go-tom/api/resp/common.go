package resp

import (
	"fmt"
	"go-tom/constant/errors"
	"go-tom/locale"
	"go-tom/util"
	"net/http"

	"github.com/gin-gonic/gin"
)

func ReturnJson(c *gin.Context, httpCode int, dataCode int, msg string, data interface{}) {
	c.JSON(httpCode, gin.H{
		"code": dataCode,
		"msg":  msg,
		"data": data,
	})
}

func Success(c *gin.Context, data interface{}) {
	ReturnJson(c, http.StatusOK, http.StatusOK, "success", data)
}

func Fail(c *gin.Context, dataCode int, msg string) {
	ReturnJson(c, http.StatusOK, dataCode, locale.Translate(msg, util.GetLocale(c)), "")
}

func ErrorReqParam(c *gin.Context) {
	ReturnJson(c, http.StatusOK, errors.ErrParam.ErrCode, locale.Translate(errors.ErrParam.Msg, util.GetLocale(c)), "")
}

type Result struct {
	Code int                    `json:"code"`
	Msg  string                 `json:"msg"`
	Data map[string]interface{} `json:"data"`
}

func NewResult() *Result {
	re := new(Result)
	re.Code = http.StatusOK
	re.Data = make(map[string]interface{})
	return re
}

func (r *Result) Error(code int, format string, params ...interface{}) {
	r.Code = code
	r.Msg = fmt.Sprintf(format, params...)
}

func (r *Result) Set(key string, val interface{}) {
	if len(key) > 0 {
		r.Data[key] = val
	}
}

func (r *Result) Success(c *gin.Context, data interface{}) {
	c.JSON(http.StatusOK, data)
}
