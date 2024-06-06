FROM openjdk:17

# copy target to container
COPY target/lopy-0.0.1.jar /usr/app/

# expose log dir
WORKDIR /usr/app
VOLUME /usr/logs

EXPOSE 8080

# start app
ENTRYPOINT ["java", "-Dtrillio.config.env=stg", "-Xms512m", "-Xmx512m", "-jar", "lopy-0.0.1.jar"]
