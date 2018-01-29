package com.mbio.workshops.catalog.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration representative of available vehicle brands.
 */
public enum VehicleBrand {

	/**
	 * Mercedes-Benz brand.
	 */
	MERCEDES_BENZ("Mercedes-Benz"),
	/**
	 * Mercedes-Benz AMG brand.
	 */
	MERCEDES_AMG("Mercedes-AMG"),
	/**
	 * smart brand.
	 */
	SMART("smart");

	private final String vehicleBrand;

	private VehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	@JsonValue
	public String getVehicleBrand() {
		return vehicleBrand;
	}
}
