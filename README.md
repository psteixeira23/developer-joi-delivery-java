[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=psteixeira23_developer-joi-delivery-java&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=psteixeira23_developer-joi-delivery-java)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=psteixeira23_developer-joi-delivery-java&metric=bugs)](https://sonarcloud.io/summary/new_code?id=psteixeira23_developer-joi-delivery-java)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=psteixeira23_developer-joi-delivery-java&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=psteixeira23_developer-joi-delivery-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=psteixeira23_developer-joi-delivery-java&metric=coverage)](https://sonarcloud.io/summary/new_code?id=psteixeira23_developer-joi-delivery-java)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=psteixeira23_developer-joi-delivery-java&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=psteixeira23_developer-joi-delivery-java)

# Welcome to JOI Delivery

JOI Delivery is built for real life. For the young professional who gets home late and doesn’t have the energy to cook. For the student with an exam tomorrow and an empty fridge tonight. These aren’t exceptions — they’re everyday moments. That’s why JOI Delivery brings food and groceries to your door, fast, fresh, and right when you need them.

Customers struggle with:

- Cluttered browsing experiences that don’t understand their preferences.
- Limited customization when ordering meals or groceries.
- Unclear order status or delivery timelines.
- Poor payment experience, or failed checkouts.
- Lack of timely feedback channels to report a bad experience or appreciate a good one.

JOI Delivery was built not just as another delivery app, but as a thoughtful, technology-first platform that reimagines how essentials reach customers in the most seamless way.

# Introducing JOI Delivery

JOI Delivery, launched in 2024, is a hyperlocal delivery app designed to bring food and groceries to your doorstep in under 45 minutes. With the tagline "Speed meets convenience," it connects customers to nearby restaurants and stores through a seamless digital experience. The app solves the hassle of long wait times and limited local options by offering real-time tracking, instant order updates, and a wide network of trusted vendors.

## Business Goals

- Differentiated Value Proposition & Niche Dominance
- Deliver Unmatched Customer Experience & Loyalty
- Superior Operational Efficiency & Cost Advantage
- Robust & Engaged Partner Ecosystem

## Why they need Thoughtworks help

As JOI Delivery continues to grow and serve more neighborhoods, we’re scaling our platform to handle increasing demand, enhance user experience, and support smarter delivery logistics. They're looking for passionate developers to help us build robust, efficient, and scalable solutions that power everything from order placement to real-time tracking.
Your expertise will directly impact how quickly and reliably customers receive their essentials—and how smoothly local vendors and delivery partners operate within our ecosystem.

### Users/Customers

Sample user profiles are available in the repository to support development and testing scenarios.

| UserId  | FirstName | LastName |
| ------- | --------- | -------- |
| user101 | John      | Doe      |

### Stores

Sample store data seeded for development purposes only.

| StoreId  | OutletName     |
| -------- | -------------- |
| store101 | Fresh Picks    |
| store102 | Natural Choice |

### Grocery Products

Dummy Products for Stores to sell and users to buy from.

| ProductId  | ProductName | StoreRefId |
| ---------- | ----------- | ---------- |
| product101 | Wheat Bread | store101   |
| product102 | Spinach     | store101   |
| product103 | Crackers    | store101   |

## API

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be running for the following endpoints to work. For more information about how to run the application, please refer to run the application section above.

## Improvements Applied

### API

- Inventory Health endpoint with status calculation (HEALTHY/LOW_STOCK/OUT_OF_STOCK) and response DTO.
  - Classes: `src/main/java/com/tw/joi/delivery/controller/InventoryController.java`, `src/main/java/com/tw/joi/delivery/service/InventoryService.java`, `src/main/java/com/tw/joi/delivery/dto/response/InventoryHealthResponse.java`, `src/main/java/com/tw/joi/delivery/enums/InventoryStatus.java`
- Request validation (`@Valid`, `@NotBlank`) and centralized error handling with a consistent error model.
  - Classes: `src/main/java/com/tw/joi/delivery/controller/CartController.java`, `src/main/java/com/tw/joi/delivery/controller/InventoryController.java`, `src/main/java/com/tw/joi/delivery/dto/request/AddProductRequest.java`, `src/main/java/com/tw/joi/delivery/exception/GlobalExceptionHandler.java`, `src/main/java/com/tw/joi/delivery/dto/response/ErrorResponse.java`
- Domain error codes via enums and standardized API error payloads.
  - Classes: `src/main/java/com/tw/joi/delivery/enums/ErrorCode.java`, `src/main/java/com/tw/joi/delivery/dto/response/ErrorResponse.java`
- Stable, idempotent load-test endpoint (`/loadtest/ping`) for benchmarking.
  - Classes: `src/main/java/com/tw/joi/delivery/controller/LoadTestController.java`, `src/main/java/com/tw/joi/delivery/dto/response/LoadTestResponse.java`

### Services & Domain

- Seed data inconsistencies fixed (user/cart/store linkage, initialization order) and made deterministic.
  - Classes: `src/main/java/com/tw/joi/delivery/seed/SeedData.java`
- Seed data no longer relies on mutable static singleton references; IDs are constants and lookups are deterministic.
  - Classes: `src/main/java/com/tw/joi/delivery/seed/SeedData.java`
- Seed initialization now declares objects close to first use to reduce scope and prevent ordering issues flagged by static analysis.
  - Gain: clearer flow, fewer code smells, and easier future maintenance.
  - Classes: `src/main/java/com/tw/joi/delivery/seed/SeedData.java`
- Service refactors to be safer against NPEs and easier to test (smaller methods, clear responsibilities).
  - Classes: `src/main/java/com/tw/joi/delivery/service/CartService.java`, `src/main/java/com/tw/joi/delivery/service/ProductService.java`, `src/main/java/com/tw/joi/delivery/service/UserService.java`, `src/main/java/com/tw/joi/delivery/service/StoreService.java`
- Inventory and store services to isolate business rules from controllers.
  - Classes: `src/main/java/com/tw/joi/delivery/service/InventoryService.java`, `src/main/java/com/tw/joi/delivery/service/StoreService.java`
- Enums reorganized into a dedicated package for maintainability.
  - Package: `src/main/java/com/tw/joi/delivery/enums`

### Tests

- Test utilities (fixtures/constants) to reduce duplication and improve clarity.
  - Classes: `src/test/java/com/tw/joi/delivery/testutil/TestFixtures.java`, `src/test/java/com/tw/joi/delivery/testutil/TestConstants.java`
- Expanded test coverage across services, controllers, and domain models.
  - Classes: `src/test/java/com/tw/joi/delivery/service/*Test.java`, `src/test/java/com/tw/joi/delivery/controller/*Test.java`, `src/test/java/com/tw/joi/delivery/domain/DomainModelTest.java`, `src/test/java/com/tw/joi/delivery/seed/SeedDataTest.java`

### Tooling & Quality

- JSON output formatting for better API readability.
  - File: `src/main/resources/application.yaml`
- JaCoCo coverage verification integrated into the build pipeline.
  - File: `build.gradle`
- Google Checkstyle rules to enforce code style consistency.
  - Files: `build.gradle`, `config/checkstyle/google_checks.xml`
- SonarCloud configuration tailored for Gradle build outputs.
  - File: `sonar-project.properties`

### Add Product to Cart

```http
POST /cart/product
Content-Type: application/json
```

Request Body

```json
{
  "userId": "user101",
  "productId": "product101",
  "outletId": "store101"
}
```

Response Body

```json
{
  "cart": {
    "cartId": "cart101",
    "outlet": null,
    "products": [
      {
        "productId": "product103",
        "productName": "Crackers",
        "mrp": 10.5,
        "sellingPrice": null,
        "weight": 500,
        "expiryDate": 0,
        "threshold": 10,
        "availableStock": 30,
        "discount": null,
        "store": {
          "name": "Fresh Picks",
          "description": null,
          "outletId": "store101",
          "inventory": []
        }
      }
    ],
    "user": null
  },
  "product": {
    "productId": "product103",
    "productName": "Crackers",
    "mrp": 10.5,
    "sellingPrice": null,
    "weight": 500,
    "expiryDate": 0,
    "threshold": 10,
    "availableStock": 30,
    "discount": null,
    "store": {
      "name": "Fresh Picks",
      "description": null,
      "outletId": "store101",
      "inventory": []
    }
  },
  "sellingPrice": null
}
```

### View Cart

```http
GET /cart/view?userId=user101
```

Response Body

```json
{
  "cartId": "cart101",
  "outlet": null,
  "products": [],
  "user": null
}
```

### Inventory Health

```http
GET /inventory/health?storeid=<storeid>
```

Response Body

```json lines
{
  // to be implemented.
}
```

## Tech Requirements

The project requires Java 24. If you have multiple JVMs on your machine, you might want to
consider using a tool such as [sdkman](https://sdkman.io/) to handle switching between versions.

The project makes use of Gradle and uses the Gradle wrapper, which means you don't need Gradle installed.

### Installing Java

Install java using homeBrew

```console
brew install openjdk@24
```

Installing java on Windows, [refer](https://www.java.com/en/download/help/windows_manual_download.html#xd_co_f=NzA3YTZmNzAtOTEzMS00OWFiLTk2NjUtODg0NjNhMjRhMjkw~)

Other ways to Download and install java, [refer](https://www.oracle.com/in/java/technologies/downloads/#java24).

#### Verify Java Version Installed

```console
java -version
```

### Useful Gradle commands

#### Build the project

Compiles the project, runs the test and then creates an executable JAR file

```bash
$ ./gradlew build
```

#### Run the application

Run the application which will be listening on port `8080`.

```bash
$ ./gradlew bootRun
```

Run the application using Java and the executable JAR file produced by the Gradle `build` task. The application will be listening on port `8080`.

```bash
$ java -jar  build/libs/joi-delivery-java.jar
```

#### Run the tests

There are two types of tests, the unit tests and the functional tests. These can be executed as follows.

- Run unit tests only

```bash
$ ./gradlew test
```

- Run both unit and functional tests

```bash
$ ./gradlew check
```

#### List all Gradle tasks

List all the tasks that Gradle can run, such as `build ` and `test`.

```bash
$ ./gradlew tasks
```

> Note: We are currently using Java 24, which is not an LTS version. Once Java 25 is released, we will upgrade to it.
