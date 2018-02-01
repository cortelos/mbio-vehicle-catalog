# `e2e-tests`

> MB.io Vehicle Catalog E2E Tests

## Getting Started

This project implements E2E testing for Mb.io Vehicle Catalog E2E Tests using Cucumber and Selenium (both using Java 8).

## Dependencies

In order to run tests locally you have to:

* Run backend in port 8080:
```
cd catalog-backend 
mvn clean install
mvn spring-boot:run
```
* Run frontend in port 3000
```
cd catalog-frontend
npm install
npm run dev
```

## Running Tests

You can use maven to compile and run the tests: `mvn clean install`