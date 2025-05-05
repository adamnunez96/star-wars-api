# Conexa Star Wars API

## Description

This test project demonstrates the implementation of security using Spring Boot 3, JSON Web Tokens (JWT), Unit Tests with Junit 5 and Mockito 4, Jacoco for summarization and coverage, Docker for deployment. The architecture is layered, seeking well-defined responsibilities and component scalability.

The information displayed is consumed from the [Swappi](https://www.swapi.tech/documentation) API. Character query endpoints are currently implemented. Movie, Ship, and Vehicle queries will also be added in subsequent phases.

The application is currently displayed on an [INSTANCE](http://13.59.27.108:8080/swagger-ui/index.html#) on AWS (EC2).

## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Maven 3+

1. Clone repository: git clone https://github.com/adamnunez96/star-wars-api.git

2. Configure environment variables (Optional). This is necessary in case the application is installed in a different environment than the local one, so that the DNS or IP address for paging can be dynamically managed. This is not required for a local environment.
- In macOS/Linux:
    ```bash
    export SERVICE_URL=https://api.example.com
    ```
- In Windows (CMD):
    ```bash
    $env:SERVICE_URL = "https://api.example.com"
    ```
3. Navigate to the project directory: `cd conexa.star.wars`
4. Build the project: `mvn clean install`
5. Run the project: `mvn spring-boot:run`
6. Test the API Rest using Postman or another application at `http://localhost:8080`.

