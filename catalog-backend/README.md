# `catalog-backend`

> MB.io Vehicle Catalog Backend

## Getting Started

The following instructions will get you a copy of the _catalog-backend_ module up and running on your local machine for development and testing purposes.

### Building

Before running the backend module, it must be built. In order to achieve this, the following command should be executed:

`mvn clean install`

It will build the backend module, run both the unit and integration tests and finally generate an artefact (_jar_) that will be placed in the local Maven repository.

### Testing

In order to trigger the execution of the created tests suites, the following command should be issued:

- Unit tests: `mvn clean test`

- Integration tests: `mvn clean verify`

### Running

In order to run the `catalog-backend` module, the following command should be issued:

`mvn spring-boot:run`

This will raise a Spring Boot application running on localhost's 8080 port.

### API

In order to check and test the REST API exposed, the following links can be accessed:

- [Swagger URL](http://localhost:8080/swagger-ui.html)
- [YAML API URL](http://localhost:8080/docs/api)

### H2 console

The H2 console is available on the following url: http://localhost:8080/h2-console

The credentials for accessing the database console are available on `application.yml` under `src/main/resources`.

The `VEHICLE` table, maps the `Vehicle` entity and will be used to persist all the vehicles.
