package com.mbio.workshops.catalog.service;

import java.util.Arrays;
import java.util.List;

import com.mbio.workshops.catalog.VehicleHelper;
import com.mbio.workshops.catalog.exception.NotFoundException;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.repository.VehicleRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CatalogServiceTest {

    private CatalogService service;

    @Mock
    private VehicleRepository vehicleRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        service = new CatalogService(vehicleRepository);
    }

    @Test
    public void createVehicle_validRequest_returnVehicle() {

        //Create service request and mock repository
        Vehicle request = VehicleHelper.buildVehicle(false);
        Vehicle repositoryResponse = VehicleHelper.buildVehicle(true);

        doReturn(repositoryResponse).when(vehicleRepository).save(request);

        //Call service and assert response
        Vehicle serviceResponse = service.createVehicle(request);

        assertNotNull(serviceResponse);
        assertEquals(repositoryResponse, serviceResponse);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullRequest_throwIllegalArgumentException() {

        try {
            service.createVehicle(null);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullBrand_throwIllegalArgumentException() {

        Vehicle request = VehicleHelper.buildVehicle(false);
        request.setVehicleBrand(null);

        try {
            service.createVehicle(request);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullModel_throwIllegalArgumentException() {

        Vehicle request = VehicleHelper.buildVehicle(false);
        request.setVehicleModel(null);

        try {
            service.createVehicle(request);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullEmpty_throwIllegalArgumentException() {

        Vehicle request = VehicleHelper.buildVehicle(false);
        request.setVehicleModel("");

        try {
            service.createVehicle(request);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullFuelType_throwIllegalArgumentException() {

        Vehicle request = VehicleHelper.buildVehicle(false);
        request.setFuelType(null);

        try {
            service.createVehicle(request);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createVehicle_nullHybrid_throwIllegalArgumentException() {

        Vehicle request = VehicleHelper.buildVehicle(false);
        request.setHybrid(null);

        try {
            service.createVehicle(request);
        } finally {
            verify(vehicleRepository, never()).save(any(Vehicle.class));
        }
    }

    @Test
    public void deleteVehicleById_validId_doNothing() throws Exception {

        //Mock repository to do nothing when deleting current id
        Vehicle vehicleToDelete = VehicleHelper.buildVehicle(true);

        doNothing().when(vehicleRepository).delete(vehicleToDelete.getVehicleId());

        //Call service and assert entity was deleted on repository
        try {
            service.deleteVehicleById(vehicleToDelete.getVehicleId());
        } finally {
            verify(vehicleRepository).delete(vehicleToDelete.getVehicleId());
        }
    }

    @Test(expected = NotFoundException.class)
    public void deleteVehicleById_unknownId_throwNotFoundException() throws Exception {

        //Mock repository to throw an exception
        Vehicle vehicleToDelete = VehicleHelper.buildVehicle(true);

        doThrow(new EmptyResultDataAccessException("Unable to find id on database", 1)).when(vehicleRepository)
                .delete(vehicleToDelete.getVehicleId());

        //Call service and assert
        try {
            service.deleteVehicleById(vehicleToDelete.getVehicleId());
        } finally {
            verify(vehicleRepository).delete(vehicleToDelete.getVehicleId());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteVehicleById_nullId_throwIllegalArgumentException() throws Exception {

        try {
            service.deleteVehicleById(null);
        } finally {
            verify(vehicleRepository, never()).delete(any(Short.class));
        }
    }

    @Test
    public void getVehicles_records_returnRecordsList() {

        //Mock records
        Vehicle vehicle1 = VehicleHelper.buildVehicle(true);
        Vehicle vehicle2 = VehicleHelper.buildVehicle(true);

        List<Vehicle> repositoryResponse = Arrays.asList(vehicle1, vehicle2);
        doReturn(repositoryResponse).when(vehicleRepository).findAll();

        //Call service and assert results
        List<Vehicle> serviceResponse = service.getVehicles();
        assertNotNull(serviceResponse);
        assertEquals(repositoryResponse, serviceResponse);
    }

    @Test
    public void getVehicles_noRecords_returnEmptyList() {

        //Mock records
        Vehicle vehicle = VehicleHelper.buildVehicle(true);

        List<Vehicle> repositoryResponse = Arrays.asList(vehicle);
        doReturn(repositoryResponse).when(vehicleRepository).findAll();

        //Call service and assert results
        List<Vehicle> serviceResponse = service.getVehicles();
        assertNotNull(serviceResponse);
        assertEquals(repositoryResponse, serviceResponse);
    }

    @Test
    public void getVehicleById_validId_returnVehicle() throws NotFoundException {

        //Mock repository vehicle
        Vehicle repositoryResponse = VehicleHelper.buildVehicle(true);

        doReturn(repositoryResponse).when(vehicleRepository).findOne(repositoryResponse.getVehicleId());

        //Call service and assert
        Vehicle serviceResponse = service.getVehicleById(repositoryResponse.getVehicleId());

        assertNotNull(serviceResponse);
        assertEquals(serviceResponse, repositoryResponse);
    }

    @Test(expected = NotFoundException.class)
    public void getVehicleById_unknownId_throwNotFoundException() throws NotFoundException {

        //Mock repository vehicle
        Vehicle vehicleToGet = VehicleHelper.buildVehicle(true);

        doReturn(null).when(vehicleRepository).findOne(vehicleToGet.getVehicleId());

        //Call service and assert
        Vehicle serviceResponse = service.getVehicleById(vehicleToGet.getVehicleId());

        assertNotNull(serviceResponse);
        assertEquals(serviceResponse, vehicleToGet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getVehicleById_nullId_throwIllegalArgumentException() throws NotFoundException {

        try {
            service.getVehicleById(null);
        } finally {
            verify(vehicleRepository, never()).findOne(any(Short.class));
        }
    }
}