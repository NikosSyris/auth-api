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

![Swagger API Overview](docs/images/swagger-overview.png)

The Swagger UI provides a complete interactive interface for testing all API endpoints.

### 1. Register a New User

Use the `/api/auth/signup` endpoint to create a new user account with optional roles.

![User Registration](docs/images/signup-request.png)

![User Registration Response](docs/images/signup-response.png)

### 2. Sign In

Authenticate with the `/api/auth/signin` endpoint to receive JWT access token and refresh token.

![User Sign In](docs/images/signin-request.png)

![User Sign In Response](docs/images/signin-response.png)

**Response includes:**
- `accessToken` - JWT token for API authentication
- `refreshToken` - Token for obtaining new access tokens
- User details (id, username, email, roles)

### 3. Authorize Requests

Click the "Authorize" button in Swagger UI and enter your access token to authenticate subsequent requests.

![JWT Authorization](docs/images/swagger-authorize.png)

### 4. Access Protected Endpoints

Once authorized, you can access role-based endpoints like `/api/test/user`, `/api/test/mod`, or `/api/test/admin`.

![Protected Endpoint Access](docs/images/protected-endpoint.png)

### 5. Refresh Access Token

Use the `/api/auth/refreshtoken` endpoint with your refresh token to obtain a new access token and refresh token (automatic rotation).

![Refresh Token Request](docs/images/refresh-request.png)

![Refresh Token Response](docs/images/refresh-response.png)

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
