include Makefile.mk

b: build-maven
build-maven:
	mvn clean install -DskipTests
test-maven:
	mvn test
coverage-maven:
	mvn jacoco:prepare-agent package jacoco:report
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
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-plugins-update deps-java-update deps-gradle-update
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
