package errors

var (
	ErrParam = &LopyError{ErrCode: 10000, Msg: "error.req.params.invalid"}
)
