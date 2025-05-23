 SHELL := /bin/sh
GRADLE_VERSION ?= 8.14.1

b:

install:
	sdk install kotlin
stop-all-containers:
	docker ps -a -q --filter="name=ops-postgres" | xargs -I {} docker stop {}
	docker images -a -q --filter="reference=ops*" | xargs -I {} docker rmi {}
remove-all-containers:
	docker ps -aq | xargs -I {} docker rm -f {}
dcup-full: b
	docker-compose up -d
dcup:
	docker-compose up -d
dcup-rebuild: stop-all-containers dcd
	docker-compose up -d --build --force-recreate
dcd: stop-all-containers remove-all-containers
	docker volume prune -f
	docker-compose down
