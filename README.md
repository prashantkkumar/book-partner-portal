#Book Partner Portal

Overview
The Book Partner Portal is a web application designed to provide a seamless user experience for authors and publishers to submit and manage their books/titles. The portal offers a common interface for both roles to publish content, with robust REST APIs, secure authentication, and comprehensive error handling.
Objectives

Enable authors and publishers to submit and manage books/titles.
Provide a unified interface for both user roles.
Ensure secure user authentication and authorization.
Deliver a scalable REST API with JSON request/response formats.
Implement global exception handling for consistent error responses.
Adhere to standard HTTP status codes (200, 201, 400, 401, 404, 500, 505).

Features

User Management: Separate tables for authentication and authorization.
Book Submission: REST APIs for creating, updating, and retrieving book details.
Role-Based Access: Common interface for authors and publishers with role-specific permissions.
Error Handling: Global REST exception handler for consistent error responses.
Testing: Comprehensive unit tests using JUnit and Mockito.

Technology Stack

Backend: Spring Boot (Java)
Database: MySQL
Testing: JUnit
API Format: JSON
Build Tool: Maven
IDE: IntelliJ IDEA
API Testing: Postman

Prerequisites

Java 17 or higher
Maven
MySQL
IntelliJ IDEA
Postman

Setup Instructions

Clone the Repository:
git clone https://github.com/your-repo/book-partner-portal.git
cd book-partner-portal


Configure Database:

Create a MySQL database (e.g., book_portal_db).
Update the database configuration in application.properties:spring.datasource.url=jdbc:mysql://localhost:3306/book_portal_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update




Install Dependencies:
mvn clean install


Run the Application:

Open the project in IntelliJ IDEA.
Run the application using the Spring Boot configuration or use the cmd line for the same.




Access the Application:

The application will run on http://localhost:8080.



API Endpoints
The REST APIs follow JSON format for requests and responses. Below are the key endpoints:
User Authentication

POST /api/auth/register: Register a new user (author/publisher).
POST /api/auth/login: Authenticate a user and return a JWT token.
GET /api/users/{id}: Retrieve user details (requires authentication).

Book Management

POST /api/books: Submit a new book/title (requires authentication).
GET /api/books/{id}: Retrieve book details.
PUT /api/books/{id}: Update book details (requires authentication).
GET /api/books: List all books (filtered by user role).

HTTP Status Codes

200 OK: Successful request.
201 Created: Resource successfully created.
400 Bad Request: Invalid request data.
401 Unauthorized: Authentication failure.
404 Not Found: Resource not found.
500 Internal Server Error: Server-side error.
505 HTTP Version Not Supported: Unsupported HTTP version.


Database Schema

Users Table: Stores user details (id, username, password, role, email).
Roles Table: Stores role information (id, role_name).
Books Table: Stores book details (id, title, author_id, publisher_id, status, etc.).
Note: Do not alter the database schema unless explicitly instructed.

Error Handling

A global RestExceptionHandler is implemented to handle exceptions across all endpoints.
Common error scenarios include invalid input, unauthorized access, and resource not found.
Error responses follow a consistent JSON format with status, error, message, timestamp, and path.

Testing
The application includes unit tests for REST APIs using JUnit.



Test Coverage

Controllers: Test API endpoints for success and error scenarios.
Services: Mock dependencies to test business logic.
Exception Handling: Verify global exception handler responses.

API Testing with Postman

Import the Postman collection (if provided) or manually test APIs.
Use Postman to send requests to http://localhost:8080/api/* endpoints.
Ensure to include JWT tokens in the Authorization header for protected endpoints.

Contributing

Fork the repository.
Create a feature branch (git checkout -b feature/your-feature).
Commit your changes (git commit -m "Add your feature").
Push to the branch (git push origin feature/your-feature).
Create a Pull Request.

For more information contact prashantoff61@gmail.com
