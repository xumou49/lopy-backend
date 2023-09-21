package locale

import (
	"encoding/json"
	"github.com/dzhcool/sven/setting"
	log "github.com/dzhcool/sven/zapkit"
	"github.com/nicksnyder/go-i18n/v2/i18n"
	"go.uber.org/zap"
	"golang.org/x/text/language"
	"os"
	"strings"
)

var bundle = i18n.NewBundle(language.English)

func InitLocale() {
	// register unmarshal func as yaml
	bundle.RegisterUnmarshalFunc("json", json.Unmarshal)

	// read the locale config
	bundleDirs, err := setting.Config.GetString("message.bundle.dir")
	if err != nil {
		log.Info("fail to get message.bundle.dir config", zap.String("err", err.Error()))
		return
	}

	if bundleDirs == "" {
		log.Info("value of message.bundle.dir is empty")
		return
	}

	dirs := strings.Split(bundleDirs, ";")
	for _, dir := range dirs {
		if dir != "" {
			files, err := os.ReadDir(dir)
			if err != nil {
				log.Info("fail to read the dir", zap.String("dir", dir), zap.String("err", err.Error()))
				continue
			}
			for _, file := range files {
				_, err = bundle.LoadMessageFile(dir + "/" + file.Name())
				if err != nil {
					log.Info("fail to load the message file", zap.String("err", err.Error()))
				}
			}
		}
	}
}

// Translate translates a message into the given language.
func Translate(code, lang string) string {
	return TranslateWithArgs(code, lang, nil)
}

// TranslateWithArgs translates a message into the given language, using the given arguments.
func TranslateWithArgs(code, lang string, template map[string]interface{}) string {
	localizer := i18n.NewLocalizer(bundle, lang)
	localizedMessage, err := localizer.Localize(&i18n.LocalizeConfig{
		DefaultMessage: &i18n.Message{
			ID:    code,
			Other: code,
		},
		TemplateData: template,
	})

	if err != nil {
		log.Info("fail to localize the message", zap.String("err", err.Error()))
		return code
	}

	return localizedMessage
}
