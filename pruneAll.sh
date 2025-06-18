#!/usr/bin/env bash

docker-compose down

docker rmi rockstars-manager_postgres

docker rmi rockstars-manager_rockstars
