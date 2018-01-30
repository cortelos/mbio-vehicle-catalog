## Workshop Exercise

In this exercise we will show you how simple is to create a new feature using provided CI/CD environment and sample application. We will simplify and run our application in jenkins machine. Off course this is not the ideal scenario for a real application, but fits the propose.

In this exercise you will add a new field to a car, which tell us what's the car color. For this, you have to:

1. Clone git repository
2. Setup job in Jenkins
3. Change your application ports
4. Add the new field to E2E Testing
5. Add the new field to Backend Integration Testing
6. Add the new field in Backend using TDD
7. Add the new field to the Frontend
8. Enjoy your new field!


## Clone the repository

Please clone this repository to your github account!


## Setup job in Jenkins

Create a new job in jenkins Pipeline job and select option to load Jenkinsfile from SCM in Pipeline tab. Specify your git repository, and load the Jenkinsfile from infrastructure/Jenkinsfile.

Save your job.

Not your job is ready to start doing all the automation!


## Change your application ports

Change following files to avoid conflicts in ports with other groups:

* infrastructure/docker-compose-prod.yml: 9001 and 9002 for your ports
* infrastructure/docker-compose-prod.yml: 8001 and 8002 for your ports
* catalog-frontend/webpack.production.js: localhost:9001 to jenkins host:port

Commit your changes! Schedule a buil and Jenkins and at the end check if application is up and running in: http://<jenkins_host>:<jenkins_port>:<your_prod_frontend_port>

Now ahead, on every commit the jenkins job will start and do all the magic!


## Add the new field to E2E Testing

Your feature file `e2e-tests/src/test/features/vehicle.feature` add the field color in all tables, both to populate the database and also to check the field in available in the frontend table.

Add the field color in `e2e-tests/src/test/java/io/mercedesbenz/dls/test/dto/VehicleDto.java` to map this to cucumber methods.

In class `e2e-tests/src/test/java/io/mercedesbenz/dls/test/steps/VehicleSteps.java` please perform two changes:

* map the field in method `map` to send the field to the database while populating the database
* Parset he field in method `parseVehicleTable` in order to check if it's displayed on the frontend (assume it's the last field of the table)

Start both backend and Frontend.

Run tests locally.

You will find they will fail (as expected).


## Add the new field to Backend Integration Testing

Open file `catalog-backend/src/test/java/com/mbio/workshops/catalog/controller/CatalogControllerV1IT.java` and perform two changes:

* createVehicle_successCase_returnCreatedVehicle: set the field color in the beggining of the method and validate it's return in the end (on then section)
* getVehicleByIs_successCase_returnVehicle: validate color field is returned.

Run this tests locally.

It will pass since we already provide the field in the Vehicle.java class. Now our backend should be ready to handle this field!


## Add the new field in Backend

Open file `catalog-backend/src/test/java/com/mbio/workshops/catalog/VehicleHelper.java` and set field color in method 'buildVehicle'.

It will pass since we already provide the field in the Vehicle.java class. Now our backend should be ready to handle this field!

## Add the new field to the Frontend

Open file `catalog-frontend/src/components/Form.vue` and perform three changes:

* Add a new form group like vehicleModel changing the name to color
* in data() add color field as empty string ''
* update clearForm() method to also clean color field to ''


Open file `catalog-frontend/src/components/List.vue` and perform one change:

* Add field color in both table head and body


## Enjoy your new field!

Now everything should be ready! Commit your changes and check if everything is fine in jenkins.

Your change should now be available in http://<jenkins_host>:<jenkins_port>:<your_prod_frontend_port>