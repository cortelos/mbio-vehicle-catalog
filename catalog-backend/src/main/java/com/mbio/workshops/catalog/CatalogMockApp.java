package com.mbio.workshops.catalog;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * The bootstrap (base) application that will trigger Spring Boot for the
 * Catalog application.
 */
@SpringBootApplication
public class CatalogMockApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CatalogMockApp.class).run(args);
    }
}
