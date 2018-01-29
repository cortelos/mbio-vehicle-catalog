package io.mercedesbenz.dls.test.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mbio.workshops.catalog.api.CatalogApi;
import com.mbio.workshops.catalog.client.ApiClient;
import com.mbio.workshops.catalog.client.ApiException;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.model.Vehicle.FuelTypeEnum;
import com.mbio.workshops.catalog.model.Vehicle.VehicleBrandEnum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.mercedesbenz.dls.test.dto.VehicleDto;
import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Defines the steps required to execute the end-to-end (e2e) test suite
 */
@Slf4j
public class VehicleSteps {

	private WebDriver driver = null;

	// Maintains the most recent vehicle IDs
	private final List<Integer> availableIds = new ArrayList<>();

	@After
	public void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}

	// ===
	// Background case
	// ===

	@Given("^Vehicle Database has vehicles$")
	public void vehicle_database_has_vehicles(List<VehicleDto> vehicleList) throws ApiException {

		log.info("Loading vehicles {}" + vehicleList);

		CatalogApi catalogApi = getCatalogApi();

		// Clean all vehicles currently on database and insert new ones
		List<Vehicle> vehiclesToDelete = catalogApi.getVehicles();
		for (Vehicle vehicleToDelete : vehiclesToDelete) {
			log.info("Deleting vehicle {}", vehicleToDelete.getVehicleId());
			catalogApi.deleteVehicleById(vehicleToDelete.getVehicleId());
		}

		// Clears available IDs list
		availableIds.clear();

		List<Vehicle> vehiclesToCreate = vehicleList.stream().map(this::map).collect(Collectors.toList());
		for (Vehicle vehicleToCreate : vehiclesToCreate) {
			Vehicle createdVehicle = catalogApi.createVehicle(vehicleToCreate);

			availableIds.add(createdVehicle.getVehicleId());

			if (createdVehicle != null) {
				log.info("Created vehicle {}", createdVehicle.getVehicleId());
			}
		}

	}

	// ===
	// Scenario 1 - list vehicles
	// ===

	@Then("^Available Vehicles table has vehicles$")
	public void available_vehicles_table_has_vehicles(List<VehicleDto> expectedVehicleList) {
		validateVehicleTable(expectedVehicleList, false);
	}

	// ===
	// Scenario 2 - delete vehicle
	// ===

	@And("^Selects a vehicle for deletion$")
	public void selects_vehicle_for_deletion() throws ApiException {
		getCatalogApi().deleteVehicleById(availableIds.get(1));
	}

	@Then("^The vehicle no longer appears on Available Vehicles table$")
	public void vehicle_no_longer_appears_available_vehicles_table(List<VehicleDto> expectedVehicleList) {
		validateVehicleTable(expectedVehicleList, true);
	}

	// ===
	// Scenario 3 - delete vehicle with invalid vehicle ID
	// ===

	@And("^Selects an unlisted vehicle for deletion$")
	public void selects_unlisted_vehicle_for_deletion() {
		try {
			getCatalogApi().deleteVehicleById(availableIds.get(9999));
			fail("Expected exception to be thrown");
		} catch (ApiException ae) {
			fail("This exception shouldn't have occurred: " + ae.getLocalizedMessage());
		} catch (IndexOutOfBoundsException iobe) {
			assertThat(iobe, instanceOf(IndexOutOfBoundsException.class));
		}
	}

	@Then("^Available Vehicles table maintains same vehicles$")
	public void available_vehicles_table_maintains_same_vehicles(List<VehicleDto> expectedVehicleList) {
		validateVehicleTable(expectedVehicleList, true);
	}

	// ===
	// Scenario 4 - create new vehicle
	// ===

	@And("^Creates a new vehicle$")
	public void create_new_vehicle() throws ApiException {
		Vehicle vehicle = new Vehicle();
		vehicle.setFuelType(FuelTypeEnum.PETROL);
		vehicle.setHybrid(Boolean.FALSE);
		vehicle.setVehicleBrand(VehicleBrandEnum.MERCEDES_AMG);
		vehicle.setVehicleModel("C63");

		getCatalogApi().createVehicle(vehicle);
	}

	@Then("^The new vehicle appears on Available Vehicles table$")
	public void vehicle_appears_available_vehicles_table(List<VehicleDto> expectedVehicleList) {
		validateVehicleTable(expectedVehicleList, true);
	}

	// ===
	// Auxiliary methods
	// ===

	@Given("^User is on Homepage$")
	public void user_is_on_homepage() {

		// Quit previous driver before creating a new one
		if (driver != null) {
			driver.quit();
		}

		driver = new ChromeDriver();

		driver.get(getFrontendUrl());
	}

	private VehicleDto parseVehicleTable(WebElement row) {

		VehicleDto vehicleDto = new VehicleDto();

		List<WebElement> columns = row.findElements(By.tagName("td"));
		assertEquals("Vehicle table should have columns: Brand, Model, Fuel Type and Hybrid.", 5, columns.size());

		vehicleDto.setBrand(columns.get(0).getText());
		vehicleDto.setModel(columns.get(1).getText());
		vehicleDto.setFuelType(columns.get(2).getText());
		vehicleDto.setHybrid(Boolean.valueOf(columns.get(3).getText()));

		return vehicleDto;
	}

	private Vehicle map(VehicleDto vehicleDto) {

		Vehicle vehicle = new Vehicle();

		vehicle.setVehicleBrand(Vehicle.VehicleBrandEnum.fromValue(vehicleDto.getBrand()));
		vehicle.setVehicleModel(vehicleDto.getModel());
		vehicle.setFuelType(Vehicle.FuelTypeEnum.fromValue(vehicleDto.getFuelType()));
		vehicle.setHybrid(vehicleDto.getHybrid());

		return vehicle;
	}

	private String getFrontendUrl() {

		String frontendUrl = getEnvironmentVariable("endpoint_frontend_url", "http://127.0.0.1:3000");

		log.info("Using frontend url {}", frontendUrl);

		return frontendUrl;
	}

	private CatalogApi getCatalogApi() {
		String backendUrl = getEnvironmentVariable("endpoint_backend_url", "http://127.0.0.1:8080");

		log.info("Using backend url {}", backendUrl);

		return new CatalogApi(new ApiClient().setBasePath(backendUrl));
	}

	private String getEnvironmentVariable(String name, String defaultValue) {

		String value = System.getenv(name);
		if (value == null || "".equals(value)) {
			value = defaultValue;
		}

		return value;
	}

	private void validateVehicleTable(List<VehicleDto> expectedVehicleList, boolean reloadPage) {
		// reload page
		if (reloadPage) {
			driver.get(getFrontendUrl());
		}

		// HTML vehicle list
		List<WebElement> vehicleHtmlRows = driver.findElements(By.xpath("//table/tbody/tr"));
		assertEquals("Vehicle list size doesn't match expected size.", expectedVehicleList.size(),
				vehicleHtmlRows.size());

		// Vehicle DTO list
		List<VehicleDto> existingVehicleList = vehicleHtmlRows.stream().map(this::parseVehicleTable)
				.collect(Collectors.toList());

		// Compare lists
		assertTrue("Attained vehicle list doesn't match expected values.",
				expectedVehicleList.stream().allMatch(existingVehicleList::contains));
	}
}
