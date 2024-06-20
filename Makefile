SHELL := /bin/bash

b: build
build:
	mvn clean install
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash
