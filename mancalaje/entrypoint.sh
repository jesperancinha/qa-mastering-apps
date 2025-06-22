#!/usr/bin/env bash
nginx
postfix start
java -jar -Dspring.profiles.active=prod mancalaje-service-0.0.0.jar
