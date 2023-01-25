SHELL=/bin/bash

b: build-maven
build-maven:
	mvn clean install
run:
	java -jar backend-kotlin/target/backend-kotlin.jar
build-run: b run
dcd:
	docker-compose down
dcup: dcd
	docker-compose up -d
dbuild:
	docker-compose build
dcup-build-run: b dbuild dcup
