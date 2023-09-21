package main

import (
	"flag"
	"go-tom/routers"

	"github.com/dzhcool/sven/buildinfo"
	"github.com/dzhcool/sven/setting"
	log "github.com/dzhcool/sven/zapkit"
	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
)

var (
	confFile string
	confEnv  string
)

func initArgs() {
	flag.StringVar(&confFile, "c", "", "please specify config file")
	flag.StringVar(&confEnv, "e", "", "please specify running environment")
	flag.Parse()
}

func main() {
	initArgs()

	setting.InitSetting(confFile, confEnv)

	// init log module
	// log.Init(zapkitConf())
	// defer log.Sync()

	// print program start info
	// printStarting()

	app := initGinEngine()
	_ = app.Run(":" + setting.Config.MustString("http.port", "8080"))
}

func zapkitConf() *log.ZapkitConfig {
	zapkitConfig := log.ZapkitConfig{
		File:       setting.Config.MustString("zapkit.file", "/tmp/zapkit.log"),
		Level:      setting.Config.MustString("zapkit.level", "info"),
		MaxSize:    setting.Config.MustInt("zapkit.maxsize", 512),
		MaxBackups: setting.Config.MustInt("zapkit.maxbackups", 10),
		MaxAge:     setting.Config.MustInt("zapkit.age", 7),
		Compress:   setting.Config.MustBool("zapkit.compress", false),
	}
	return &zapkitConfig
}

func printStarting() {
	log.Info(setting.Config.MustString("app.name", ""), zap.String("env", setting.AppEnv),
		zap.String("version", setting.Config.MustString("app.version", "")),
		zap.String("loglevel", setting.Config.MustString("zapkit.level", "info")),
		zap.String("buildTime", buildinfo.GetBuildTime()),
		zap.String("buildGoVersion", buildinfo.GetBuildGoVersion()))
}

func initGinEngine() *gin.Engine {
	app := gin.Default()
	router := routers.NewRouter()
	router.Register(app)
	return app
}
