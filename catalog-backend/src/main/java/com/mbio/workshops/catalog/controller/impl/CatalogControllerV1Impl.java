package com.mbio.workshops.catalog.controller.impl;

import java.util.List;
import javax.validation.Valid;

import com.mbio.workshops.catalog.controller.CatalogControllerV1;
import com.mbio.workshops.catalog.exception.NotFoundException;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.service.CatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing the vehicle catalog.
 */
@Slf4j
@RestController
public class CatalogControllerV1Impl implements CatalogControllerV1 {

    private CatalogService service;

    @Autowired
    public CatalogControllerV1Impl(CatalogService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Vehicle> createVehicle(@ApiParam(value = "Details of the vehicle to create",
                                                           required = true) @RequestBody @Valid Vehicle vehicle) {

        log.debug("Creating vehicle {}", vehicle);

        Vehicle createdVehicle = service.createVehicle(vehicle);

        log.debug("Created vehicle {}", vehicle);

        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteVehicleById(@ApiParam(value = "Identifier of the vehicle to delete",
                                                            required = true) @PathVariable final Short vehicleId)
            throws NotFoundException {

        log.debug("Deleting vehicle {}", vehicleId);

        service.deleteVehicleById(vehicleId);

        log.debug("Deleted vehicle {}", vehicleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Vehicle>> getVehicles() {

        log.debug("Fetching all vehicles");

        List<Vehicle> vehicles = service.getVehicles();

        log.debug("Fetched #{} vehicles", vehicles != null ? vehicles.size() : 0);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Vehicle> getVehicleById(@ApiParam(value = "Identifier of the vehicle to attain",
                                                            required = true) @PathVariable final Short vehicleId)
            throws NotFoundException {

        log.debug("Getting vehicle {}", vehicleId);

        Vehicle response = service.getVehicleById(vehicleId);

        log.debug("Returning vehicle {}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
