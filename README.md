# Auth API - JWT Authentication & Authorization System

A production-ready REST API built with Spring Boot that demonstrates secure authentication and authorization using JWT tokens, refresh token rotation, and role-based access control.

## Overview

This project implements a comprehensive authentication and authorization system with token-based security, featuring JWT access tokens, automatic refresh token rotation, and role-based access control. Built using modern Spring Boot practices and industry-standard security patterns.

## Key Features

- **JWT Authentication**: Secure token-based authentication using JSON Web Tokens
- **Refresh Token Rotation**: Enhanced security through automatic refresh token rotation on each refresh
- **Role-Based Authorization**: Granular access control with USER, MODERATOR, and ADMIN roles
- **Token Expiration Management**: Configurable expiration times for both access and refresh tokens
- **RESTful API Design**: Clean, well-structured endpoints following REST principles
- **API Documentation**: Interactive Swagger/OpenAPI documentation for easy testing
- **Secure Password Storage**: BCrypt password hashing for secure credential storage
- **CORS Support**: Configurable cross-origin resource sharing
- **H2 Database Integration**: In-memory database for development and testing

## Technologies Used

### Backend Framework
- **Spring Boot 4.0.1** - Modern Java application framework
- **Spring Security** - Comprehensive security framework for authentication and authorization
- **Spring Data JPA** - Data persistence with JPA/Hibernate
- **Spring Web** - RESTful web services
- **Spring Validation** - Request validation

### Security
- **JWT (JSON Web Tokens)** - Stateless authentication using JJWT library (v0.11.5)
- **OAuth 2.0 Principles** - Token-based authorization framework patterns
- **BCrypt** - Secure password encoding

### Database
- **H2 Database** - In-memory database with console access
- **Hibernate** - ORM implementation

### API Documentation
- **SpringDoc OpenAPI 3** - Swagger UI integration for API documentation and testing

### Build Tools
- **Maven** - Dependency management and build automation
- **Java 21** - Latest LTS version of Java

## Architecture Highlights

- **Stateless Authentication**: JWT-based approach eliminates server-side session storage
- **Refresh Token Pattern**: Separate refresh tokens with rotation for enhanced security
- **Custom JWT Filter**: Request interceptor for token validation
- **Exception Handling**: Global exception handling with custom error responses
- **DTO Pattern**: Request/Response DTOs for clean API contracts
- **Repository Pattern**: Data access abstraction layer

## API Endpoints

### Public Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/signup` | Register a new user with optional roles |
| POST | `/api/auth/signin` | Authenticate user and receive JWT + refresh token |
| POST | `/api/auth/refreshtoken` | Get new access token using refresh token |

### Protected Endpoints

| Method | Endpoint | Required Role | Description |
|--------|----------|---------------|-------------|
| GET | `/api/test/all` | Public | Public content |
| GET | `/api/test/user` | USER | User content |
| GET | `/api/test/mod` | MODERATOR | Moderator content |
| GET | `/api/test/admin` | ADMIN | Admin content |

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd auth-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`

4. **Initialize the database roles**

   After starting the application, access the H2 Console at `http://localhost:8080/h2-console`

   Connection details:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: *(leave empty)*

   Run the following SQL commands to create the required roles:
   ```sql
   INSERT INTO roles(name) VALUES('ROLE_USER');
   INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
   INSERT INTO roles(name) VALUES('ROLE_ADMIN');
   ```

### Configuration

Key configuration properties in `application.properties`:

```properties
# JWT Configuration
app.jwtSecret=<your-secret-key>
app.jwtExpirationMs=60000              # Access token: 1 minute
app.jwtRefreshExpirationMs=86400000    # Refresh token: 24 hours

# H2 Database
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

The Swagger UI provides:
- Complete API endpoint documentation
- Request/response schemas
- Interactive testing interface
- JWT authentication support (click "Authorize" and enter your token)

## Usage Examples

### Swagger UI Overview

<img width="1872" height="956" alt="image" src="https://github.com/user-attachments/assets/c7e427c4-31df-4180-82e6-86bba83a6df0" />


The Swagger UI provides a complete interactive interface for testing all API endpoints.

### 1. Register a New User

Use the `/api/auth/signup` endpoint to create a new user account with roles "Moderator" and "User".

<img width="1594" height="746" alt="image" src="https://github.com/user-attachments/assets/d400c611-65a9-462d-b8c9-c225586c46f9" />



<img width="1597" height="207" alt="image" src="https://github.com/user-attachments/assets/2b025e6e-0f7d-4003-88ee-32eeb5e73777" />


### 2. Sign In

Authenticate with the `/api/auth/signin` endpoint to receive JWT access token and refresh token.

<img width="1595" height="522" alt="image" src="https://github.com/user-attachments/assets/01fed82a-2416-4e87-915b-558a374a904f" />





<img width="1594" height="324" alt="image" src="https://github.com/user-attachments/assets/37fa9fd2-2a83-4ed8-a61c-25f06e5df66d" />


**Response includes:**
- `accessToken` - JWT token for API authentication
- `refreshToken` - Token for obtaining new access tokens
- User details (id, username, email, roles)

### 3. Authorize Requests

Click the "Authorize" button in Swagger UI and enter your access token to authenticate subsequent requests.

<img width="724" height="313" alt="image" src="https://github.com/user-attachments/assets/cad2f096-9fe7-420c-ad11-256149531108" />


<img width="728" height="316" alt="image" src="https://github.com/user-attachments/assets/b2034933-7cb8-4286-ad92-2d5414f5576d" />




### 4. Access Protected Endpoints

Once authorized, you can access role-based endpoints like `/api/test/user`, `/api/test/mod`, or `/api/test/admin`.

<img width="1592" height="818" alt="image" src="https://github.com/user-attachments/assets/681f1afe-19ea-42d8-9c49-7347e47d8c8d" />


### 5. Refresh Access Token

Use the `/api/auth/refreshtoken` endpoint with your refresh token to obtain a new access token and refresh token (automatic rotation).

<img width="1592" height="448" alt="image" src="https://github.com/user-attachments/assets/52ff951b-57da-4f85-a382-4313fef922c4" />




<img width="1591" height="213" alt="image" src="https://github.com/user-attachments/assets/a1043eb2-004c-4af0-b762-475caaeef287" />


## Security Features

### Token Management
- **Access Tokens**: Short-lived (1 minute default) JWT tokens for API authentication
- **Refresh Tokens**: Long-lived (24 hours default) tokens stored in database
- **Automatic Rotation**: New refresh token generated with each refresh request
- **Old Token Cleanup**: Previous refresh tokens are invalidated upon rotation

### Password Security
- BCrypt hashing algorithm with automatic salt generation
- Configurable password strength requirements via validation

### Authorization
- Method-level security with role-based access control
- Three-tier role hierarchy: USER, MODERATOR, ADMIN
- Custom access denied handling

## Database Schema

### Users Table
- `id` (Primary Key)
- `username` (Unique)
- `email` (Unique)
- `password` (BCrypt hashed)

### Roles Table
- `id` (Primary Key)
- `name` (Enum: ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN)

### Refresh Tokens Table
- `id` (Primary Key)
- `user_id` (Foreign Key)
- `token` (Unique UUID)
- `expiry_date`

### User-Roles (Join Table)
- `user_id`
- `role_id`

## Project Structure

```
src/
├── main/
│   ├── java/com/example/authapi/
│   │   ├── config/          # OpenAPI configuration
│   │   ├── controllers/     # REST controllers
│   │   ├── models/          # JPA entities
│   │   ├── repository/      # Data access layer
│   │   ├── security/        # Security configuration, JWT utilities
│   │   ├── payload/         # Request/Response DTOs
│   │   ├── exception/       # Custom exceptions
│   │   └── advice/          # Global exception handlers
│   └── resources/
│       └── application.properties
```

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

---

**Note**: This is a demonstration project for educational purposes. For production deployment, ensure you:
- Use strong, environment-specific JWT secrets
- Configure appropriate token expiration times
- Implement rate limiting
- Use a production-grade database
- Enable HTTPS
- Review and adjust CORS settings
- Implement comprehensive logging and monitoring
