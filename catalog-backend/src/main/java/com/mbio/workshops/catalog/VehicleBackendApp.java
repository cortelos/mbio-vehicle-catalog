package com.mbio.workshops.catalog;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * The bootstrap (base) application that will trigger Spring Boot for the
 * Catalog application.
 */
@SpringBootApplication
public class VehicleBackendApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(VehicleBackendApp.class).run(args);
    }
}
