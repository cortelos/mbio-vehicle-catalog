package com.mbio.workshops.catalog.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * POJO representative of a vehicle
 */
@Data
@Table
@Entity
@ToString
@EqualsAndHashCode(of = "vehicleId")
public class Vehicle implements Serializable {

    /**
     * The serial version unique identifier.
     */
    private static final long serialVersionUID = -7509799411186683543L;

    /**
     * The vehicle unique identifier.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short vehicleId;

    /**
     * The vehicle brand.
     */
    @Column(name = "brand", length = 50)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private VehicleBrand vehicleBrand;

    /**
     * The vehicle model.
     */
    @Column(name = "model", length = 50)
    @NotNull
    private String vehicleModel;

    /**
     * The vehicle fuel type.
     */
    @Column(length = 50)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private FuelType fuelType;

    /**
     * The indication of whether the vehicle is hybrid or not.
     */
    @NotNull
    private Boolean hybrid;

    /**
     * The color of the card.
     */
    private String color;
}
