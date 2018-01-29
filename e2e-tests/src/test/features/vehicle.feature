Feature: As a Vehicle Manager I want to manage vehicles database.

  Background:
    Given Vehicle Database has vehicles
      | brand             | model  | fuelType | hybrid |
      | Mercedes-Benz     | C220 D | Diesel   | false  |
      | Mercedes-AMG      | AMG GT | Petrol   | false  |
      | smart             | Fortwo | Petrol   | true   |

  Scenario: Open Vehicle Manager BackOffice and list all vehicles in database.
    When User is on Homepage
    Then Available Vehicles table has vehicles
      | brand             | model  | fuelType | hybrid |
      | Mercedes-Benz     | C220 D | Diesel   | false  |
      | Mercedes-AMG      | AMG GT | Petrol   | false  |
      | smart             | Fortwo | Petrol   | true   |

  Scenario: Open Vehicle Manager BackOffice and delete one of the listed vehicles.
  	When User is on Homepage
  	And Selects a vehicle for deletion
  	Then The vehicle no longer appears on Available Vehicles table
  	  | brand             | model  | fuelType | hybrid |
      | Mercedes-Benz     | C220 D | Diesel   | false  |
      | smart             | Fortwo | Petrol   | true   |

  Scenario: Open Vehicle Manager and delete unlisted vehicle.
  	When User is on Homepage
  	And Selects an unlisted vehicle for deletion
  	Then Available Vehicles table maintains same vehicles
      | brand             | model  | fuelType | hybrid |
      | Mercedes-Benz     | C220 D | Diesel   | false  |
      | Mercedes-AMG      | AMG GT | Petrol   | false  |
      | smart             | Fortwo | Petrol   | true   |

  Scenario: Open Vehicle Manager BackOffice and creates a new vehicle.
  	When User is on Homepage
  	And Creates a new vehicle
  	Then The new vehicle appears on Available Vehicles table
  	  | brand             | model  | fuelType | hybrid |
      | Mercedes-Benz     | C220 D | Diesel   | false  |
      | Mercedes-AMG      | AMG GT | Petrol   | false  |
      | smart             | Fortwo | Petrol   | true   |
      | Mercedes-AMG      | C63    | Petrol   | false  |
