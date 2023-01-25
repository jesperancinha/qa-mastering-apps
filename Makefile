SHELL=/bin/bash

b: build-maven
build-maven:
	mvn clean install
run:
	java -jar backend-kotlin/target/backend-kotlin.jar
build-run: b run
