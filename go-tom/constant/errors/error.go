package errors

type LopyError struct {
	ErrCode int
	Msg     string
}

func (l *LopyError) Error() string {
	return l.Msg
}

func (l *LopyError) Code() int {
	return l.ErrCode
}
