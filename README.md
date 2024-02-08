RewardsController Documentation
Overview

The RewardsController is a REST controller in a Spring Boot application that manages the rewards of customers. It uses the RewardsHandler service to handle the business logic.
Database

The application uses an H2 database for data storage. You can access the H2 console at the following URL to interact with the database:

http://localhost:8080/h2-console/

Please ensure that the JDBC URL, username, and password match your H2 configuration.
Endpoints
GET /customers/{customerId}/rewards

This endpoint returns the rewards of a specific customer.
Parameters

    customerId (path parameter, required): The ID of the customer.

Returns

    Reward: The reward of the specified customer.

How to Run

To run the Spring Boot application which includes this controller, you can use the Spring Boot Maven plugin with the following command in the terminal at the root directory of the project:

mvn spring-boot:run

This will start the application on the default port 8080. You can access the RewardsController endpoints at http://localhost:8080/customers/{customerId}/rewards.

Please replace {customerId} with the actual ID of the customer.
Testing

You can test the RewardsController using any HTTP client like curl or Postman. Here’s an example using curl:

curl -X GET http://localhost:8080/customers/{customerId}/rewards

Again, replace {customerId} with the actual ID of the customer.

Regards,
Krzysztof