package io.mercedesbenz.dls.test.dto;

import java.util.Objects;

public class VehicleDto {

    private String brand;

    private String model;

    private String fuelType;

    private Boolean hybrid;

    @Override
    public String toString() {
        return "VehicleDto{" + "brand='" + brand + '\'' + ", model='" + model + '\'' + ", fuelType='" + fuelType + '\''
                + ", hybrid=" + hybrid + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VehicleDto that = (VehicleDto) o;
        return Objects.equals(brand, that.brand) && Objects.equals(model, that.model) && Objects
                .equals(fuelType, that.fuelType) && Objects.equals(hybrid, that.hybrid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(brand, model, fuelType, hybrid);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Boolean getHybrid() {
        return hybrid;
    }

    public void setHybrid(Boolean hybrid) {
        this.hybrid = hybrid;
    }
}
