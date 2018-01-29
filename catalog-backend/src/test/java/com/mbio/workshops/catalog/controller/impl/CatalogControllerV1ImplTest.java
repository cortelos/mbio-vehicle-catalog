package com.mbio.workshops.catalog.controller.impl;

import java.util.Arrays;
import java.util.List;

import com.mbio.workshops.catalog.VehicleHelper;
import com.mbio.workshops.catalog.exception.NotFoundException;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.service.CatalogService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit testing for catalog controller V1.
 */
public class CatalogControllerV1ImplTest {

    private CatalogControllerV1Impl controller;

    @Mock
    private CatalogService service;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        controller = new CatalogControllerV1Impl(service);
    }

    @Test
    public void createVehicle_validRequest_returnVehicle() {

        //Create request data and mock service response
        Vehicle request = VehicleHelper.buildVehicle(false);
        Vehicle serviceResponse = VehicleHelper.buildVehicle(true);

        doReturn(serviceResponse).when(service).createVehicle(request);

        //Call controller and assert
        ResponseEntity<Vehicle> controllerResponse = controller.createVehicle(request);

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.CREATED, controllerResponse.getStatusCode());
        assertNotNull(controllerResponse.getBody());
        assertEquals(serviceResponse.getVehicleId(), controllerResponse.getBody().getVehicleId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_errorInService_throwIllegalArgumentException() {

        //Mock service to throw an exception
        Vehicle request = VehicleHelper.buildVehicle(false);
        doThrow(new IllegalArgumentException("Invalid request")).when(service).createVehicle(request);

        //Call service and assert it was called
        try {
            controller.createVehicle(request);
        } finally {
            verify(service).createVehicle(request);
        }
    }

    @Test
    public void deleteVehicleById_validRequest_doNothing() throws Exception {

        //Mock service to return nothing when deleting current id
        Vehicle vehicleToDelete = VehicleHelper.buildVehicle(true);
        doNothing().when(service).deleteVehicleById(vehicleToDelete.getVehicleId());

        //Call service and assert service was called with proper
        ResponseEntity<Void> controllerResponse = controller.deleteVehicleById(vehicleToDelete.getVehicleId());
        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());

        verify(service).deleteVehicleById(vehicleToDelete.getVehicleId());
    }

    @Test(expected = RuntimeException.class)
    public void deleteVehicleById_errorInService_throwRuntimeException() throws Exception {

        //Mock service to throw an exception when deleting current id
        Vehicle vehicleToDelete = VehicleHelper.buildVehicle(true);
        doThrow(new RuntimeException("Runtime error")).when(service).deleteVehicleById(vehicleToDelete.getVehicleId());

        //Call controller and assert service when called
        try {
            controller.deleteVehicleById(vehicleToDelete.getVehicleId());
        } finally {
            verify(service).deleteVehicleById(vehicleToDelete.getVehicleId());
        }
    }

    @Test
    public void getVehicles_validRequest_returnVehicle() {

        //Mock service to return a list of vehicles
        Vehicle vehicle1 = VehicleHelper.buildVehicle(true);
        Vehicle vehicle2 = VehicleHelper.buildVehicle(true);

        List<Vehicle> serviceResponse = Arrays.asList(vehicle1, vehicle2);
        doReturn(serviceResponse).when(service).getVehicles();

        //Call controller and assert controller response
        ResponseEntity<List<Vehicle>> controllerResponse = controller.getVehicles();

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertNotNull(controllerResponse.getBody());
        assertEquals(serviceResponse, controllerResponse.getBody());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getVehicles_errorInService_throwException() {

        //Mock service to throw an exception while getting vehicles
        doThrow(new UnsupportedOperationException("Operation not implemented yet!")).when(service).getVehicles();

        //Call controller and ensure service was called
        try {
            controller.getVehicles();
        } finally {
            verify(service).getVehicles();
        }
    }

    @Test
    public void getVehicleById_validRequest_returnVehicle() throws Exception {

        //Mock service to return current vehicle data
        Vehicle vehicle = VehicleHelper.buildVehicle(true);

        doReturn(vehicle).when(service).getVehicleById(vehicle.getVehicleId());

        //Call controller and assert vehicle is returned
        ResponseEntity<Vehicle> controllerResponse = controller.getVehicleById(vehicle.getVehicleId());

        assertNotNull(controllerResponse);
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
        assertNotNull(controllerResponse.getBody());
        assertEquals(vehicle, controllerResponse.getBody());
    }

    @Test(expected = NotFoundException.class)
    public void getVehicleById_errorInService_throwExpection() throws Exception {

        //Mock service to throw an exception
        Vehicle vehicle = VehicleHelper.buildVehicle(true);
        doThrow(NotFoundException.class).when(service).getVehicleById(vehicle.getVehicleId());

        //Call controller and assert service was called
        try {
            controller.getVehicleById(vehicle.getVehicleId());
        } finally {
            verify(service).getVehicleById(vehicle.getVehicleId());
        }

    }
}