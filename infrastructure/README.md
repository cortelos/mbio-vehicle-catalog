# `infrastructure`

> MB.io Vehicle Catalog Infrastructure

## Getting Started

In this project we provide all resources needed to run MB.io Vehicle Catalog.

You need a machine for CI/CD where all steps will run using jenkins.


## Dependencies

You have to install in your machine:

* [Docker](https://docs.docker.com/install/)
* [Docker compose](https://docs.docker.com/compose/)
* [Google Chrome](https://www.google.com/chrome/browser/desktop/index.html)
* [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/)
* [Git](https://git-scm.com)
* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Jenkins](https://jenkins.io)
* [Xvfb](https://www.x.org/releases/X11R7.7/doc/man/man1/Xvfb.1.xhtml)
* Jenkins plugins:
  - NodeJS
  - Xvfb


Finally, you have to create in Jenkins an installation for:
* JDK version 8 with name `jdk8`
* Nodejs version 9 with name `nodejs9`
* Maven version 3 with name `maven3`
* Xvfb with name `xvfb`


## Setup Jenkins Job

In this directory you will find a file called `Jenkinsfile` which defines a [pipeline as code](https://jenkins.io/doc/book/pipeline-as-code/) in order to build, test and deploy Vehicle Catalog.

## Environments

We provide two different environments: one to run automated testing (test environment) and other called prod where we will run our application if no tests fail.

You can access prod environment at: [http://localhost:9002](http://localhost:9002) (frontend) and [http://localhost:9001](http://localhost:9001) (backend).

Test environment uses ports 8002 and 8001 for frontend and backend. This environment is only available during testing phase. Please check documentation in `docs/presentation.pptx`.

## Files description

* docker-compose-prod.yml: runs two docker images (backend + frontend) for 'PROD'
* docker-compose-test.tml: runs two docker images (backend + frontend) for 'TEST'
* Jenkinsfile: jenkins pipeline definition