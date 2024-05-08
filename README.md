# Order Management System

This Spring Boot application is designed to manage orders and process payments, demonstrating a simple order management system with RESTful services and microservice architecture.

## Prerequisites

Ensure you have the following software installed on your system:

- **Java JDK 21**: Ensure Java 21 or higher is installed.
- **Maven**: Required for project building and dependency management.
- **PostgreSQL**: Needed for the database.

## Setup Instructions

1. **Clone or fork the repository**:
2. **Project includes two microservices**
3. **Open the location where pom.xml is present and use mvn to build the project**
4. **Configure the url for postgres sql in application properties**
5. **After running the microservices, use the frontend index.html to interact with the application**

## Database Setup
```sql

  CREATE DATABASE orderdb;
  CREATE DATABASE paymentdb;
```
### API Documentation for Order Microservice

```markdown
### Order Microservice API

- **GET /order**
  Retrieves a list of all orders.
  - **Success Response**: `200 OK`

- **POST /order/placeOrder**
  Creates a new order.
  - **Body**:
    ```json
    {
      "customerName": "Waah",
      "productName": "keys",
      "quantity": 3,
      "totalPrice": 1200.00
    }
    ```
  - **Success Response**: `201 Created`

- **PUT /order/update/{id}**
  Updates an existing order.
  - **Body**:
    ```json
    {
      "customerName": "gojo",
      "productName": "keys",
      "quantity": 3,
      "totalPrice": 1200.00
    }
    ```
  - **Success Response**: `200 OK`

- **DELETE /order/delete/{id}**
  Deletes an order.
  - **Success Response**: `200 OK`

```


   
### API Documentation for Payment Microservice

```markdown
### Payment Microservice API

- **GET /payment/all**
  Retrieves a list of all payments.
  - **Success Response**: `200 OK`

- **POST /payment**
 processes the payment.
  - **Body**:
    ```json
    {
      "orderId": "5",
      "referenceNumber": "14euiqbn",
      "paymentAmount": 1200.00
    }
    ```
  - **Success Response**: `201 Created`


```


   
