package com.mbio.workshops.catalog.repository;

import com.mbio.workshops.catalog.model.Vehicle;

import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository to exposed based Vehicle operations on database.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Short> {

}
