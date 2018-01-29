package com.mbio.workshops.catalog;

import java.util.Random;

import com.mbio.workshops.catalog.model.FuelType;
import com.mbio.workshops.catalog.model.Vehicle;
import com.mbio.workshops.catalog.model.VehicleBrand;

import org.apache.commons.text.RandomStringGenerator;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

/**
 * This helper provides a method to generate random vehicles with random data.
 */
public class VehicleHelper {

    private static Random random = new Random();

    private static RandomStringGenerator stringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z')
            .filteredBy(LETTERS, DIGITS).build();

    /**
     * Build a vehicle with random values.
     *
     * @param withId if id should be populated
     * @return the generated vehicle
     */
    public static Vehicle buildVehicle(boolean withId) {

        //Random vehicle data
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleBrand(VehicleBrand.values()[random.nextInt(VehicleBrand.values().length)]);
        vehicle.setVehicleModel(stringGenerator.generate(1 + random.nextInt(99)));
        vehicle.setFuelType(FuelType.values()[random.nextInt(FuelType.values().length)]);
        vehicle.setHybrid(random.nextBoolean());

        if (withId) {
            vehicle.setVehicleId((short) random.nextInt(Short.MAX_VALUE + 1));
        }

        return vehicle;
    }
}
