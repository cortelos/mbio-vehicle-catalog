# Vehicle Catalog Workshop

The purpose of this project is to demonstrate how to properly automate most of an application's development lifecycle steps (deployment, testing, etc.) using CI/CD - Continuous Deployment / Continuous Integration.

## Getting Started

In order to get a copy of this project up and running on your local machine, for development and testing purposes, please follow the instructions present on the `README.md` files of each one of the project modules.

### Prerequisites

In order for being able to properly develop and run the project, the following components should be installed:

- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [NodeJS & npm](https://www.npmjs.com/get-npm)
- [JDK 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- [Maven 3.5.x](https://maven.apache.org/download.cgi)

### Checkout

In order to checkout the Vehicle Catalog Workshop project, the following command should be issued:

`git clone https://gitlab.com/lisbon-hub/catalog-workshop.git`

## Modules

The project workshop is divided into the following modules:

- `catalog-backend`
- `catalog-client`
- `catalog-frontend`
- `docs`
- `e2e-tests`
- `infrastructure`

### `catalog-backend`

Module that contains the code for the backend: a simple Spring Boot application that exposes a REST API with the Vehicle Catalog services.

For persistence purposes an in-memory H2 database is being used.

### `catalog-client`

Module containing the Vehicle Catalog REST API client used by the end-to-end (e2e) tests to call (and test) the Vehicle Catalog services.

### `catalog-frontend`

Module that contains the code for the frontend: a VueJS application that communicates with the backend module.

### `docs`

Module containing the Vehicle Catalog workshop documentation.

### `e2e-tests`

Module where the end-to-end tests are depicted.

### `infrastructure`

Module that contains all files required to set up the CI/CD infrastructure.
