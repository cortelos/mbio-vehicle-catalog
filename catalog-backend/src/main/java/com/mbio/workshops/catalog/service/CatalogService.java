package com.mbio.workshops.catalog.service;

import java.util.ArrayList;
import java.util.List;

import com.mbio.workshops.catalog.exception.NotFoundException;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.repository.VehicleRepository;
import com.mbio.workshops.catalog.utils.ValidationUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Catalog service implementation.
 */
@Slf4j
@Service
public class CatalogService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public CatalogService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(final Vehicle vehicle) {

        ValidationUtils.assertNotNull(vehicle, "vehicle");
        ValidationUtils.assertNotNull(vehicle.getVehicleBrand(), "brand");
        ValidationUtils.assertNotEmpty(vehicle.getVehicleModel(), "model");
        ValidationUtils.assertNotNull(vehicle.getFuelType(), "fuel type");
        ValidationUtils.assertNotNull(vehicle.getHybrid(), "hybrid");

        log.info("Creating vehicle {}", vehicle);

        Vehicle createdVehicle = vehicleRepository.save(vehicle);

        log.info("Created vehicle {}", createdVehicle);

        return createdVehicle;
    }

    public void deleteVehicleById(final Short vehicleId) throws NotFoundException {

        ValidationUtils.assertNotNull(vehicleId, "vehicle id");

        log.info("Deleting vehicle {}", vehicleId);

        try {
            vehicleRepository.delete(vehicleId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Unable to find vehicle with id " + vehicleId);
        }

        log.info("Deleted vehicle {}", vehicleId);
    }

    public List<Vehicle> getVehicles() {

        log.info("Fetching all vehicles");

        Iterable<Vehicle> repositoryResponse = vehicleRepository.findAll();

        List<Vehicle> response = new ArrayList<>();
        repositoryResponse.forEach(response::add);

        log.info("Fetched #{} vehicles", response.size());

        return response;
    }

    public Vehicle getVehicleById(final Short vehicleId) throws NotFoundException {

        ValidationUtils.assertNotNull(vehicleId, "id");

        log.info("Getting vehicle {}", vehicleId);

        Vehicle response = vehicleRepository.findOne(vehicleId);
        if (response == null) {
            throw new NotFoundException("Unable to find vehicle with id " + vehicleId);
        }

        log.info("Found vehicle {}", response);

        return response;
    }
}
