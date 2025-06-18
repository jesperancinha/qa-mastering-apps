#!/usr/bin/env bash

java -jar -Dspring.profiles.active=prod rockstars.jar &
tail -f /dev/null
