.DEFAULT_GOAL := build-run

clean:
	@./gradlew clean

build:
	@./gradlew clean build

install:
	@./gradlew clean install

run-dist:
	@./build/install/tickets-analyzer/bin/tickets-analyzer

run:
	@./gradlew run

test:
	@./gradlew test

lint:
	@./gradlew checkstyleMain checkstyleTest

build-run: build run

.PHONY: build