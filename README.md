# RentalApp - Spring Boot & MariaDB

A Spring Boot application with MariaDB integration, automated database migrations using Flyway, and initial data seeding.

## Prerequisites

- **Java 17** or higher
- **MariaDB** server
- **Maven** (optional, uses `./mvnw` wrapper)

## Setup Process

### 1. Database Creation
Create a database named `spring-api` in your MariaDB instance:
```sql
CREATE DATABASE `spring-api`;
```

### 2. Configuration
Update `src/main/resources/application.properties` with your MariaDB credentials:
```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/spring-api
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 3. Database Migrations (Flyway)
Flyway manages your database schema via SQL scripts located in `src/main/resources/db/migration`.

- **Creating a new migration**: Add a new `.sql` file following the naming convention `V<Number>__<Description>.sql` (e.g., `V2__add_phone_to_user.sql`).
- **Initial Schema**: The table `user` is defined in [V1__init_schema.sql](src/main/resources/db/migration/V1__init_schema.sql).

### 4. Data Seeding
Initial data is automatically populated by the [DataSeeder.java](src/main/java/com/springdev/rentalApp/config/DataSeeder.java) class.
- It checks if the `user` table is empty on startup.
- If empty, it adds pre-defined users (John Doe, Jane Smith).

## Running the Application

Use the Maven wrapper to start the application:

```bash
./mvnw spring-boot:run
```

The server will start on `http://localhost:8080`.

## API Documentation

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | List all users (includes seeded data) |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update an existing user |
| DELETE| `/api/users/{id}` | Delete a user |

## Troubleshooting

- **Dialect Detection**: If Hibernate fails with "Unknown column 'RESERVED'", ensure `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect` is set in your properties (this is already configured).
- **Driver Issues**: This project uses the official `mariadb-java-client`. Ensure your connection string starts with `jdbc:mariadb://`.
