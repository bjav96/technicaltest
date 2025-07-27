# README

---

## Project Overview

This project is a Spring Boot application that provides an API to retrieve the applicable price for a product based on
the brand, product ID, and application date. The application uses an in-memory H2 database to store data about brands
and prices.

---

## Prerequisites

Before running the application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.8 or higher

---

The application runs on port **8081**. Ensure this port is available before starting the application.

---
## How to Run the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/bjav96/technicaltest.git
   git switch develop
   ```

2. **Build the Project**:
   Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   Start the application using the following command:
   ```bash
   mvn spring-boot:run
   ```
   The application will start on port `8081` as configured in the `application.yml` file.

4. **Access the H2 Console** (Optional):
    - URL: [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (leave blank)

5. **Swagger Documentation**:
    - API Docs: [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)
    - Swagger UI: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

---

## API Endpoints

### 1. **Get Applicable Price**

- **URL**: `/api/v1/getPrice`
- **Method**: `GET`
- **Query Parameters**:
    - `applicationDate` (required): The date and time in the format `yyyy-MM-dd HH:mm:ss`.
    - `productId` (required): The ID of the product.
    - `brandId` (required): The ID of the brand.
- **Example**:
  ```bash
  curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-14%2010%3A00%3A00&productId=35455&brandId=1"
  ```

---

## Test Cases

Below are the test cases to verify the functionality of the API:

### Test 1

- **Request**:
-

```bash
curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-14%2010%3A00%3A00&productId=35455&brandId=1"
```

- **Expected Response**:
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
  }
  ```

### Test 2

- **Request**:
  ```bash
  curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-14%2016%3A00%3A00&productId=35455&brandId=1"
  ```
- **Expected Response**:
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 2,
    "startDate": "2020-06-14T15:00:00",
    "endDate": "2020-06-14T18:30:00",
    "price": 25.45,
    "currency": "EUR"
  }
  ```

### Test 3

- **Request**:

 ```bash
  curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-14%2021%3A00%3A00&productId=35455&brandId=1"
  ```

- **Expected Response**:
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
  }
  ```

### Test 4

- **Request**:

 ```bash
  curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-15%2010%3A00%3A00&productId=35455&brandId=1"
  ```

- **Expected Response**:
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 3,
    "startDate": "2020-06-15T00:00:00",
    "endDate": "2020-06-15T11:00:00",
    "price": 30.50,
    "currency": "EUR"
  }
  ```

### Test 5

- **Request**:

 ```bash
  curl "http://localhost:8081/api/v1/getPrice?applicationDate=2020-06-16%2021%3A00%3A00&productId=35455&brandId=1"
  ```

- **Expected Response**:
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 4,
    "startDate": "2020-06-15T16:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 38.95,
    "currency": "EUR"
  }
  ```

---

## Notes

- The application uses an in-memory H2 database, so the data is reset every time the application restarts.
- The `data.sql` file initializes the database with sample data for testing purposes.
- Ensure the `applicationDate` parameter is URL-encoded when making requests.
