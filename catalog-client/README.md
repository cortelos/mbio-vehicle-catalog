# `catalog-client`

> MB.io Vehicle Catalog Backend Client (API)

## Getting Started

The following instructions will get you a copy of the _catalog-client_ ready to use in your java application.

### Building

Before include the module in your app, it must be built. In order to achieve this, the following command should be executed:

`mvn clean install`

It will generate a Java Client in top of Backend REST API.

### Integrate in Your Application

In order to use this client in your application you have to import the client in you `pom.xml`:

```
<dependency>
	<groupId>com.mbio.workshops</groupId>
	<artifactId>catalog-client</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

In your code:

```
// Get a proxy to catalog API
CatalogApi catalogApi = new CatalogApi(new ApiClient().setBasePath("http://127.0.0.1:8080"));

//Callmethod
catalogApi.deleteVehicleById(1);
```

Check available methods and documentation in [Swagger UI](http://localhost:8080/swagger-ui.html) or [Swagger DOCS API](http://localhost:8080/docs/api).