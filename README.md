# SaaSMultiTent — Multi-Tenant SaaS Backend

A multi-tenant SaaS backend built with **Java, Spring Boot, and MySQL**, designed to manage company-specific data through a secure and scalable REST API.

The project focuses on backend development best practices, authentication and authorization, database persistence, and modular architecture.

## Overview

SaaSMultiTent is a backend application designed to provide a centralized platform where registered users can manage their companies and business operations within an isolated environment.

The main goal is to implement a multi-tenant architecture in which each company accesses only its own information.

## Key Features

- **Authentication and Authorization:** Secure user authentication using Spring Security and JSON Web Tokens (JWT).
- **Multi-Tenant Architecture:** Company-scoped data access to separate business information between tenants.
- **Company Management:** Associate registered users with their companies.
- **Inventory Management:** Manage products, categories, quantities, and prices.
- **Sales Management:** Register sales, calculate totals, and manage product stock.
- **Dashboard:** Provide inventory and sales information for business monitoring.
- **REST API:** Expose endpoints for communication between the backend and client applications.
- **API Documentation:** Document endpoints using OpenAPI and Swagger UI.

*Note: Features may be under development as the project evolves.*

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot 4 | Backend application framework |
| Spring Security | Authentication and access control |
| JWT | Token-based authentication |
| Spring Data JPA | Data persistence and ORM |
| MySQL | Relational database |
| Gradle | Dependency management and build automation |
| Spring Validation | Request validation |
| Springdoc OpenAPI | API documentation |
| Lombok | Reduce boilerplate code |

## Architecture

The project aims to follow **Hexagonal Architecture (Ports and Adapters)** to separate business logic from infrastructure and external frameworks.

The main architectural responsibilities include:

- **Domain:** Business rules and core entities.
- **Application:** Use cases and application workflows.
- **Infrastructure:** Database persistence, security implementation, and external integrations.
- **API / Adapters:** REST controllers and request/response handling.

This separation helps improve maintainability, testability, and the ability to evolve individual components independently.

## Security

The backend uses Spring Security and JWT-based authentication.

Security goals include:

- Authenticate users through signed tokens.
- Protect endpoints according to authorization rules.
- Associate requests with the authenticated user's company.
- Enforce tenant-level data isolation.
- Validate incoming requests before processing business operations.

Tenant isolation must be enforced on the server side for every relevant operation, rather than relying on a company identifier supplied by the client.

## Getting Started

### Prerequisites

Make sure you have installed:

- Java Development Kit (JDK) 17
- MySQL
- Git

### 1. Clone the repository

```bash
git clone https://github.com/EspiSebas/SaasMultiTent.git
cd SaasMultiTent
```

### 2. Configure the database

Create a MySQL database for the application:

```sql
CREATE DATABASE saas_multitent;
```

Configure your database connection and other environment-specific properties according to the application's configuration.

For example, a local environment file could contain:

```env
DB_URL=jdbc:mysql://localhost:3306/saas_multitent
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_secure_secret
```

These variable names are examples. Make sure they match the configuration expected by the application.

**Security:** Never commit real database credentials, JWT secrets, or other sensitive environment variables to GitHub.

### 3. Run the application

On Linux or macOS:

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

Make sure your database is running and the application configuration is correct before starting the backend.

### 4. Explore the API

If the OpenAPI configuration is enabled, you can access the Swagger UI at:

```text
http://localhost:8080/swagger-ui/index.html
```

The application must be running for the documentation page to be available.

## Project Goals

This project serves as a practical implementation of backend engineering concepts, including:

- Designing secure REST APIs.
- Applying object-oriented programming principles.
- Structuring applications using Hexagonal Architecture.
- Implementing authentication and authorization with JWT.
- Managing relational data with Spring Data JPA and MySQL.
- Building company-scoped business operations.
- Applying SOLID principles and separation of concerns.
- Improving code quality, maintainability, and testability.

## Future Improvements

- Expand automated unit and integration test coverage.
- Add Docker and Docker Compose support.
- Implement CI/CD pipelines.
- Improve centralized exception handling and logging.
- Add more detailed API documentation.
- Strengthen tenant-isolation tests.
- Prepare production deployment configuration.

## Author

**Sebastian Espinosa**

Software Development Technologist | Backend Developer

- GitHub: [@EspiSebas](https://github.com/EspiSebas)
- LinkedIn: [sebastian-espinosa-dev](https://www.linkedin.com/in/sebastian-espinosa-dev/)

---

*This project is part of my ongoing journey to improve my backend development skills and build maintainable, secure, and scalable software solutions.*
