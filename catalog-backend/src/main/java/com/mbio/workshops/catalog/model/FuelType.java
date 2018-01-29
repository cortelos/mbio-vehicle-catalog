package com.mbio.workshops.catalog.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration representative of available fuel types for catalog vehicles.
 */
public enum FuelType {

    /**
     * Diesel fuel type.
     */
    DIESEL("Diesel"),
    /**
     * Petrol fuel type.
     */
    PETROL("Petrol"),
    /**
     * Electric fuel type.
     */
    ELECTRIC("Electric"),
    /**
     * Hybrid fuel type.
     */
    HYBRID("Hybrid");

    private final String fuelType;

    private FuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @JsonValue
    public String getFuelType() {
        return fuelType;
    }
}
