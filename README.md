# Banking Ledger System

A Spring Boot application that implements a banking ledger system for managing customer accounts and financial transactions.

## 📋 Overview

This project is a demo banking system built with Spring Boot that provides basic account management functionality. It follows Domain-Driven Design (DDD) principles with a clean architecture approach.

## 🚀 Features

- **Account Management**: Create and manage bank accounts

## 🛠️ Technology Stack

- **Java 21**: Programming language
- **Spring Boot 3.5.4**: Application framework
- **Spring Data JPA**: Data access layer
- **PostgreSQL**: Database
- **Maven**: Build tool

## ⚙️ Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL database

## 🏃‍♂️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/peemtanapat/Banking-Ledger-System.git
cd Banking-Ledger-System
```

### 2. Database Setup

- Install and start PostgreSQL
- Create a database (the application will create tables automatically)
- Update database credentials in `src/main/resources/application.yml` if needed

### 3. Run the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven
mvn spring-boot:run
```

The application will start on `http://localhost:8088`

### 4. Run Tests

```bash
./mvnw test
```

## 🔧 Configuration

The application can be configured through `application.yml`:

- **Port**: Server runs on port 8088
- **Database**: PostgreSQL connection settings
- **JPA**: Hibernate DDL auto-configuration set to `create-drop`

## 🏗️ Architecture

This project follows Domain-Driven Design principles:

- **Entities**: Core business objects (Account)
- **Value Objects**: Immutable objects (AccountNumber, Money, AccountName)
- **Enums**: Account status, types, and currency definitions
- **Repositories**: Data access abstractions

## 📄 License

This project is for educational purposes.

---

**Note**: This is a demo project for learning Spring Boot and banking system concepts.
