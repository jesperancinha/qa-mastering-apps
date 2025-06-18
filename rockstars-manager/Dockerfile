FROM eclipse-temurin:21-alpine

ENV ROCKSTARS /usr/local/bin/

WORKDIR ${ROCKSTARS}

COPY target/rockstars-manager*.jar rockstars.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "rockstars.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]