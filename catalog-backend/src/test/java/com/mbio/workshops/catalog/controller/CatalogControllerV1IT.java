package com.mbio.workshops.catalog.controller;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbio.workshops.catalog.VehicleBackendApp;
import com.mbio.workshops.catalog.model.FuelType;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.model.VehicleBrand;

/**
 * Test class for {@link CatalogControllerV1} where will be depicted its
 * integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = VehicleBackendApp.class)
@AutoConfigureMockMvc
public class CatalogControllerV1IT {

	/**
	 * Common request mapping URL path.
	 */
	private static final String REQUEST_MAPPING = "/catalog/v1";

	@Autowired
	private MockMvc mvc;

	// =====
	// createVehicle tests
	// =====

	@Test
	public void createVehicle_successCase_returnCreatedVehicle() throws Exception {
		// given
		Vehicle vehicle = new Vehicle();
		vehicle.setFuelType(FuelType.DIESEL);
		vehicle.setHybrid(Boolean.FALSE);
		vehicle.setVehicleBrand(VehicleBrand.SMART);
		vehicle.setVehicleModel("fortwo");

		MockHttpServletRequestBuilder post = post(REQUEST_MAPPING + "/vehicle")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).contentType(MediaType.APPLICATION_JSON_UTF8);

		post.content(new ObjectMapper().writeValueAsString(vehicle));

		// when
		ResultActions resultActions = mvc.perform(post).andDo(print());

		// then
		resultActions.andExpect(status().isCreated());
		resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
		resultActions.andExpect(jsonPath("$", not(empty())));
		resultActions.andExpect(jsonPath("$.vehicleId", not(empty())));
		resultActions.andExpect(jsonPath("$.vehicleId", greaterThan(0)));
		resultActions.andExpect(jsonPath("$.vehicleBrand", equalTo(vehicle.getVehicleBrand().getVehicleBrand())));
		resultActions.andExpect(jsonPath("$.vehicleModel", equalTo(vehicle.getVehicleModel())));
		resultActions.andExpect(jsonPath("$.fuelType", equalTo(vehicle.getFuelType().getFuelType())));
		resultActions.andExpect(jsonPath("$.hybrid", equalTo(vehicle.getHybrid())));
	}

	// =====
	// deleteVehicleById tests
	// =====

	@Test
	public void deleteVehicle_successCase_returnVoid() throws Exception {
		// given
		MockHttpServletRequestBuilder delete = delete(REQUEST_MAPPING + "/vehicle/1")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
		MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/vehicle/1")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

		// when
		ResultActions deleteResultActions = mvc.perform(delete).andDo(print());
		ResultActions getResultActions = mvc.perform(get).andDo(print());

		// then
		deleteResultActions.andExpect(status().isOk());
		getResultActions.andExpect(status().isNotFound());
	}

	@Test
	public void deleteVehicle_vehicleNotFoundException_return404() throws Exception {
		// given
		MockHttpServletRequestBuilder delete = delete(REQUEST_MAPPING + "/vehicle/99")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

		// when
		ResultActions resultActions = mvc.perform(delete).andDo(print());

		// then
		resultActions.andExpect(status().isNotFound());
	}

	// =====
	// getVehicles tests
	// =====

	@Test
	public void getVehicles_successCase_returnAvailableVehicleList() throws Exception {
		// given
		MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/vehicles");

		// when
		ResultActions resultActions = mvc.perform(get).andDo(print());

		// then
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
		resultActions.andExpect(jsonPath("$", not(empty())));
	}

	// =====
	// getVehicleById tests
	// =====

	@Test
	public void getVehicleByIs_successCase_returnVehicle() throws Exception {
		// given
		MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/vehicle/2")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

		// when
		ResultActions resultActions = mvc.perform(get).andDo(print());

		// then
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
		resultActions.andExpect(jsonPath("$", not(empty())));
		resultActions.andExpect(jsonPath("$.vehicleId", equalTo(2)));
		resultActions.andExpect(jsonPath("$.vehicleBrand", equalTo("Mercedes-AMG")));
		resultActions.andExpect(jsonPath("$.vehicleModel", equalTo("C 63 S Coupé")));
		resultActions.andExpect(jsonPath("$.fuelType", equalTo("Petrol")));
		resultActions.andExpect(jsonPath("$.hybrid", equalTo(false)));
	}

	@Test
	public void getVehicleById_vehicleNotFoundException_return404() throws Exception {
		// given
		MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/vehicle/99")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

		// when
		ResultActions resultActions = mvc.perform(get).andDo(print());

		// then
		resultActions.andExpect(status().isNotFound());
	}
}
