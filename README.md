# Customer Management System

## Overview

The **Customer Management System** is a Spring Boot application designed to manage customer data. It supports operations to create, update, retrieve, and delete customers, and includes functionality for searching and syncing customer data with a remote API.

## Features

- **CRUD Operations:** Create, Read, Update, and Delete customers.
- **Search Functionality:** Search for customers by first name, city, email, or phone number with support for pagination and sorting.
- **API Synchronization:** Sync customer data from a remote API, updating existing records or inserting new ones.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL Database**
- **RestTemplate for API Calls**
- **Lombok for reducing boilerplate code**

## Project Setup

### Prerequisites

- **JDK 17** or later
- **Maven 3.6** or later
- **MySQL Database**
- **IDE** (e.g., IntelliJ IDEA) with Spring Boot support

### Installation Steps

1. **Clone the Repository**

    ```bash
    git clone https://github.com/arupx3492/SunBaseAssignment.git
    cd customer-management-system
    ```

2. **Configure MySQL Database**

    - Create a MySQL database named `customer_db`.
    - Update the `application.properties` file with your MySQL username and password:

    ```properties
    server.port=8080
    spring.datasource.url=jdbc:mysql://localhost:3306/customer_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Build the Project**

    ```bash
    mvn clean install
    ```

4. **Run the Application**

    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Create Customer

```http
POST /api/create
```

- **Request Body:**

    ```json
    {
      "first_name": "John",
      "last_name": "Doe",
      "street": "123 Main St",
      "address": "Apartment 1",
      "city": "New York",
      "state": "NY",
      "email": "john.doe@example.com",
      "Phone": "1234567890"
    }
    ```

### Update Customer

```http
PUT /api/{id}
```

- **Path Variable:** `id` (UUID of the customer)
- **Request Body:**

    ```json
    {
      "uuid": "updated-uuid",
      "first_name": "John",
      "last_name": "Doe",
      "street": "456 Elm St",
      "address": "Suite 2",
      "city": "Los Angeles",
      "state": "CA",
      "email": "john.doe@example.com",
      "Phone": "0987654321"
    }
    ```

### Get Customer by ID

```http
GET /api/{id}
```

- **Path Variable:** `id` (UUID of the customer)

### Delete Customer

```http
DELETE /api/{id}
```

- **Path Variable:** `id` (UUID of the customer)

### Get Customers (with Search, Pagination, and Sorting)

```http
GET /api/customers
```

- **Query Parameters:**
  - `searchBy` (optional): Field to search by (e.g., `first_name`, `city`, `email`, `phone`).
  - `searchTerm` (optional): Search term to look for.
  - `page` (optional): Page number (default is 0).
  - `size` (optional): Number of records per page (default is 100).
  - `sortBy` (optional): Field to sort by (default is `Phone`).
  - `sortDir` (optional): Sort direction (`asc` or `desc`, default is `asc`).

### Sync Customers with Remote API

```http
POST /api/sync
```

- **Request Body:** None


# For the Frontend go to The UI folder 

- **Run the login.html file**
- **use "arup9883m@gmail.com" as email and "12345" as password**
- **You will be redirected to the customers.html file where you can do the above operations**

- ## Login Page
![Login Page](https://github.com/arupx3492/SunBaseAssignment/blob/master/UI/Screenshoots/Loging%202.png)


- ## Customers Page
![Customers Table](https://github.com/arupx3492/SunBaseAssignment/blob/master/UI/Screenshoots/Customer2.png)

- ## Add Customer Page
![Add Customer](https://github.com/arupx3492/SunBaseAssignment/blob/master/UI/Screenshoots/add%20Customer.png)

- ## Update Customer Page

![Edit Customer](https://github.com/arupx3492/SunBaseAssignment/blob/master/UI/Screenshoots/Edit%20Customer.png)

