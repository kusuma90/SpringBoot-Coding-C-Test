# SpringBoot-Coding-C-Test

# Rewards Program API

A Spring Boot RESTful API that calculates reward points for customer transactions.

###### Overview as per given instructions/business #######

This API provides a rewards program to customers, awarding points based on each recorded purchase. Customers receive 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.

## Implementation

The project is implemented using Spring Boot and exposes a RESTful endpoint to calculate reward points for customer transactions. The application uses an H2 in-memory database for storing transaction data.

## How to Run

1. Clone the repository to your local machine.
2. Make sure you have Java 8 installed.
3. Build the project using Gradle: `./gradlew build`
4. Run the application: `./gradlew bootRun`

The API will be accessible at: `http://localhost:8080`

## API Endpoint

- **Calculate Rewards**: `/rewards/calculatePoints`
    - Method: `POST`
    - Content Type: `application/json`
    - Request Body: List of Transaction objects
    - Response: List of Reward objects containing the reward points for each customer per month.

## Example Request

```json
[
    {
        "customerId": 1,
        "transactionAmount": 400,
        "transactionDate": "2023-07-01"
    },
    {
        "customerId": 1,
        "transactionAmount": 50,
        "transactionDate": "2023-07-15"
    },
    {
        "customerId": 2,
        "transactionAmount": 80,
        "transactionDate": "2023-07-05"
    },
     {
        "customerId": 3,
        "transactionAmount": 250,
        "transactionDate": "2023-07-10"
    }
]
Example Response
ample OutPut:
{
"1": {
"2023-07": 650
},
"2": {
"2023-07": 30
}
}

Running Tests
To run the test suite, use Gradle: ./gradlew test

Dependencies
Spring Boot 2.5.5
H2 Database
Jackson for JSON serialization/deserialization
JUnit Jupiter for testing
Mockito for mocking dependencies



