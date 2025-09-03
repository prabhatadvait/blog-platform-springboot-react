# Blog Platform - Spring Boot & React

A secure, full-stack, and scalable blog platform engineered with **Spring Boot** for the backend and **React** for the frontend.  
This project features robust **JWT-based authentication**, comprehensive **REST APIs**, and fine-grained **role-based access control (RBAC)**.  
The application is containerized using **Docker** for streamlined deployment and development.

---

## 📋 Table of Contents
- [Architectural Overview](#-architectural-overview)
- [Key Features](#-key-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation & Setup](#installation--setup)
- [🐳 Docker Deployment](#-docker-deployment)
- [🔑 Environment Variables](#-environment-variables)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)

---

## 🏛️ Architectural Overview

The application follows a classic **client-server architecture**:  
- The **React frontend** acts as the client, providing a dynamic and responsive user interface.  
- It communicates with the **Spring Boot backend** via RESTful APIs.  
- The **backend** handles business logic, data persistence, and security.  
- **PostgreSQL** stores user data, blog posts, and application-related information.

```
+-----------------+      +------------------------+      +------------------+
|                 |      |                        |      |                  |
|   React Client  |----->| Spring Boot Backend    |----->|   PostgreSQL DB  |
| (Web Browser)   |      | (REST API)             |      |                  |
|                 |      |                        |      |                  |
+-----------------+      +------------------------+      +------------------+
                         | - JWT Authentication   |
                         | - Role-Based Access    |
                         | - Business Logic       |
                         +------------------------+
```

---

## ✨ Key Features
- **Full-Stack Application**: Modern frontend + robust backend.  
- **User Authentication**: Secure registration/login with JWT.  
- **Role-Based Access Control (RBAC)**: User/Admin roles with distinct permissions.  
- **RESTful API**: For seamless frontend-backend communication.  
- **CRUD Operations**: Create, Read, Update, Delete blog posts.  
- **Containerized**: Ready with Dockerfile & docker-compose.  
- **Scalable & Maintainable**: Clean codebase, best practices.  

---

## 🛠️ Tech Stack

**Backend**  
- Framework: Spring Boot 3  
- Language: Java 17  
- Security: Spring Security, JWT  
- Database: PostgreSQL  
- ORM: Spring Data JPA (Hibernate)  
- API: Spring Web (REST controllers)  
- Build Tool: Maven  

**Frontend**  
- Library: React 18  
- State Management: Redux / Context API  
- Routing: React Router  
- API Client: Axios  
- Styling: Tailwind CSS / Material-UI  

**DevOps**  
- Containerization: Docker, Docker Compose  

---

## ⚙️ Project Structure

The **Spring Boot backend** is organized into a modular, layered architecture:

```
src/main/java/com/prabhat/blog
│
├── config        # Configuration classes (e.g., SecurityConfig)
├── controllers   # REST API endpoint definitions
├── domain        # JPA entities (database models)
├── mappers       # DTO mappers (Entity <-> DTO conversions)
├── repositories  # Spring Data JPA interfaces
├── security      # JWT generation, validation, filters
└── services      # Business logic layer
```

---

## 🚀 Getting Started

Follow these steps to run the project locally.

### Prerequisites
- JDK 17+  
- Maven 3.8+  
- Node.js 18+ & npm  
- Docker & Docker Compose  
- PostgreSQL instance  

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/blog-platform-springboot-react.git
   cd blog-platform-springboot-react
   ```

2. **Backend Setup**
   - Navigate to the backend directory.  
   - Copy `application.properties.example` → rename to `application.properties`.  
   - Update DB credentials + JWT secret key.  
   - Run backend:  
     ```bash
     mvn spring-boot:run
     ```

3. **Frontend Setup**
   - Navigate to the frontend directory.  
   - Install dependencies:  
     ```bash
     npm install
     ```
   - Start development server:  
     ```bash
     npm start
     ```

---

## 🐳 Docker Deployment

The easiest way to run the app is with **Docker Compose**.

1. Clone the repository (if not already).  
2. Create `.env` in root directory (refer `.env.example`).  
3. Build & run containers:  
   ```bash
   docker-compose up --build
   ```
   - Backend API runs in one container.  
   - Frontend runs in another.  
   - PostgreSQL runs in a separate container.  

---

## 🔑 Environment Variables

### PostgreSQL Database
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

### JWT Configuration
```properties
jwt.secret=your_super_secret_key_for_jwt_tokens
jwt.expiration=86400000   # 24 hours
```

---

## 🤝 Contributing

Contributions are always welcome! 🚀  

1. Fork the Project  
2. Create your Feature Branch  
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. Commit Changes  
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. Push to Branch  
   ```bash
   git push origin feature/AmazingFeature
   ```
5. Open a Pull Request  

---

## 📄 License

Distributed under the **MIT License**.  
See [`LICENSE`](LICENSE) for details.
