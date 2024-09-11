.DEFAULT_GOAL := build-run

clean:
	@echo "Cleaning the project..."
	@./gradlew clean

build:
	@echo "Dependencies installing..."
	@./gradlew clean build

install:
	@echo "Installing..."
	@./gradlew clean install

run-dist:
	@./build/install/tickets-analyzer/bin/tickets-analyzer

run:
	@echo "Starting the App..."
	@./gradlew run

test:
	@echo "Statrting tests..."
	@./gradlew test
	@echo "Tests is done, check the results!"

lint:
	@./gradlew checkstyleMain checkstyleTest

build-run: build run

.PHONY: build