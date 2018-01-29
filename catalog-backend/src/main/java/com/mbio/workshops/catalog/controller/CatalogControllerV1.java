package com.mbio.workshops.catalog.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mbio.workshops.catalog.exception.NotFoundException;
import com.mbio.workshops.catalog.model.Vehicle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Vehicle catalog main controller interface.
 */
@RequestMapping(value = "/catalog/v1")
@Api(value = "/catalog/v1", tags = { "catalog" })
public interface CatalogControllerV1 {

	/**
	 * Creates new vehicle.
	 * 
	 * @param vehicle
	 *            the vehicle to be created
	 * @return the created vehicle updated with its unique identifier
	 */
	@ApiOperation(value = "Create vehicle", notes = "Operation that allows the user to create a vehicle in the system", nickname = "createVehicle", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Vehicle successfully created", response = Vehicle.class) })
	@PostMapping(value = "/vehicle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle);

	/**
	 * Deletes a vehicle based on its vehicle identifier.
	 * 
	 * @param vehicleId
	 *            the identifier of the vehicle to delete
	 */
	@ApiOperation(value = "Delete vehicle by ID", notes = "Operation that allows the user to delete a vehicle from the system taking into account the vehicle's ID", nickname = "deleteVehicleById", httpMethod = "DELETE")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Vehicle successfully deleted") })
	@DeleteMapping(value = "/vehicle/{vehicleId}")
	public ResponseEntity<Void> deleteVehicleById(Short vehicleId) throws NotFoundException;

	/**
	 * Attains all available vehicles.
	 * 
	 * @return a list of all available vehicles
	 */
	@ApiOperation(value = "Get all vehicles", notes = "Operation that allows the user to attain all vehicles from the system", nickname = "getVehicles", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Vehicles successfully attained", response = Vehicle.class, responseContainer = "List") })
	@GetMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Vehicle>> getVehicles();

	/**
	 * Attains a vehicle based on its vehicle identifier.
	 * 
	 * @param vehicleId
	 *            the identifier
	 * @return the vehicle associated with the identifier
	 */
	@ApiOperation(value = "Get a vehicle by ID", notes = "Operation that allows the user to get a vehicle from the system taking into account the vehicle's ID", nickname = "getVehicleById", httpMethod = "GET")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Vehicle successfully attained", response = Vehicle.class),
			@ApiResponse(code = 404, message = "Vehicle not found") })
	@GetMapping(value = "/vehicle/{vehicleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Vehicle> getVehicleById(Short vehicleId) throws NotFoundException;
}
