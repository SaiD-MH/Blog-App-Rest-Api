# Blog App REST API

This is a RESTful API for a blog application built using Spring Boot and Java. The API provides essential functionalities such as user authentication, CRUD operations for managing blog posts and comments, user roles, search/filtering, pagination, security measures, documentation, testing, and more.

## Features

- **User Authentication:** Register, login, and authenticate users using JWT tokens.
- **CRUD Operations:** Create, read, update, and delete blog posts.
- **User Roles:** Define roles such as admin, author, and reader with different permissions.
- **Comments:** Allow users to comment on blog posts with CRUD operations for comments.
- **Search and Filtering:** Search for posts based on keywords, tags, or categories. Filter posts by date, popularity, and more.
- **Pagination:** Paginate posts and comments for better performance and user experience.
- **User Profile Management:** Update user profiles, change passwords, and manage profile information.
- **Validation and Error Handling:** Implement input validation and handle errors gracefully with appropriate status codes and messages.
- **Security:** Ensure security best practices with HTTPS, rate limiting, and input sanitization.
- **Documentation:** API documentation provided using Swagger/OpenAPI for easy understanding and integration.
- **Testing:** Unit tests and integration tests for reliability and correctness of API endpoints.

## Technology & Tools

- **Java8** 
- **Spring Boot:** 
- **Spring Security:** 
- **Spring Data JPA:** 
- **JWT (JSON Web Tokens):** 
- **Swagger/OpenAPI:** 
- **Maven:** 
- **JUnit:** 
- **Git:** 
- **GitHub:** 
- **MySQL:** 
- ** IntelliJ IDEA**

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven 

## Installation

1. Clone the repository:
`` git clone https://github.com/SaiD-MH/Blog-App-Rest-Api.git
  cd blog-app-rest-api ``

2. Build the project using Maven:
   ``mvn clean install``
3. Run the application:
  ``java -jar target/blog-app-rest-api.jar``
4. Set up database:
  `` Configure your database connection details in src/main/resources/application.properties.
     Run the database migrations using tools like Flyway or Liquibase.``

## API Documentation
  ``the API documentation is available at /swagger-ui.html endpoint when the application is running. You can access it in your browser to explore and test the API endpoints.``

##Contributing
  ``Contributions are welcome! Please fork the repository and create a pull request with your changes.``

##License
``This project is licensed under the MIT License - see the LICENSE file for details.``



