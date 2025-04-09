# RESTful-BookStore

The **Restful Book Store** project is a Java-based RESTful web service that provides an API for querying books stored in an SQLite database. The service allows users to search for books by category or keyword and retrieve a list of all available books.

## Overview

This project is a refactored version of a book search application, initially implemented using servlets and JSP. The original application utilized the MVC and DAO patterns, which were converted into a RESTful web service to improve scalability and maintainability.

The new architecture removes the need for servlets and JSPs, exposing direct endpoints for accessing book data via RESTful principles.

## Technologies Used

- **Java** (JAX-RS for RESTful API)
- **SQLite** (lightweight relational database for storing book data)
- **Maven** (dependency and build management)
- **Jersey** (framework for implementing JAX-RS-based RESTful services)
- **JDBC** (Java Database Connectivity for interacting with SQLite)
- **Tomcat** (or any servlet container for deploying the application)
- **Servlet API** (for handling HTTP requests and responses)

## Database Structure

The application utilizes an SQLite database (`Books.db`) that contains three tables:

- **BOOK** – Stores book details such as title, author, category, price, and description.
- **AUTHOR** – Stores author details.
- **CATEGORY** – Stores different book categories.

## Features
Get all books: Retrieves a list of all books from the database.

Get all categories: Retrieves a list of all available book categories.

Search books by category: Allows users to search books by category.

Search books by keyword: Allows users to search books by a specified keyword.

## REST API Endpoints

The web service is structured under the `/Books` resource path, with the following endpoints:

### Retrieve all books
```http
GET /rest/Books
```

### Retrieve all categories
```http
GET /rest/Books/category
```

### Search books by category
```http
GET /rest/Books/searchByCat/{category}
```
**Example:**
```http
GET /rest/Books/searchByCat/Fiction
```

### Search books by keyword
```http
GET /rest/Books/searchByKey/{keyword}
```
**Example:**
```http
GET /rest/Books/searchByKey/Java
```

## Implementation Details

- **DAO (Data Access Object):** The `BookDAOImpl` class handles database queries, requiring a connection to the SQLite database.
- **REST Service:** The `BookService` class acts as the front controller, handling all HTTP requests.
- **Database Access:** The SQLite database file (`Books.db`) is placed in the `WebContent` folder, and its absolute path is resolved using `ServletContext` via the `@Context` annotation.
- **Maven Dependency:** Instead of manually adding the SQLite JDBC driver, it is included via the Maven dependency:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.34.0</version>
</dependency>
```

## Running the Project

### Clone the repository:
```sh
git clone https://github.com/jacobm00517/Restful-BookStore.git
cd Restful-Book-Store
```

### Build the project using Maven:
```sh
mvn clean package
```

### Run the application:
```sh
java -jar target/server-1.0-RELEASE.jar
```

### Use `curl` or a REST client to interact with the API:
```sh
curl http://localhost:8080/booksREST/rest/Books
```
