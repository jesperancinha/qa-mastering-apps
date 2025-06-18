FROM jesperancinha/je-all-runtime-jdk-11:0.0.1

ENV ROCKSTARS /usr/local/bin/

WORKDIR ${ROCKSTARS}

COPY target/rockstars-manager*.jar rockstars.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "rockstars.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]