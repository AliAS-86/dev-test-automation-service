# Architecture Document for Restaurama-Food-App

## 1. Introduction

### 1.1 Project Objective
The **Restaurama-Food-App** is a food delivery app MVP, designed to demonstrate a **QA automation framework**, covering multiple stages of testing:
- Unit Testing
- Interface Testing
- Integration Testing
- End-to-End (E2E) Testing
- Performance Testing
- Security Testing

### 1.2 High-Level Architecture
The application is a **microservice-based architecture**:
- Deployed locally for testing using **Docker Compose**.
- Deployed in a production-like environment using a **single-node Kubernetes cluster**.
- Communication between the UI and backend, as well as between services, occurs via **REST APIs** and a **message queue** for event-driven actions.

### 1.3 Architecture Diagram
![Architecture Diagram](resources/restaurama-app.drawio.png)

---

## 2. Tech Stack Overview

| **Layer**              | **Technology**                                                                                                                                                           | **Reason for Choice**                                                                                   |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Frontend**           | [React](https://react.dev)                                                                                                                                              | Lightweight and widely used framework, excellent for creating simple, responsive UIs.                  |
| **API Gateway**        | [Flask](https://flask.palletsprojects.com/) / [FastAPI](https://fastapi.tiangolo.com)                                                                                   | Simple Python frameworks for routing API requests and managing authentication.                         |
| **Authentication**     | [Flask](https://flask.palletsprojects.com)                                                                                                                             | Provides flexibility for JWT-based authentication with minimal setup.                                  |
| **Backend Services**   | [Flask](https://flask.palletsprojects.com) / [FastAPI](https://fastapi.tiangolo.com)                                                                                   | Enables rapid development and easy API integration.                                                    |
| **Databases (SQL)**    | [PostgreSQL](https://www.postgresql.org)                                                                                                                               | Reliable and feature-rich relational database for handling transactional data (e.g., users, orders).   |
| **Databases (NoSQL)**  | [MongoDB](https://www.mongodb.com)                                                                                                                                      | Ideal for storing semi-structured data like menus and restaurant details.                              |
| **Message Queue**      | [RabbitMQ](https://www.rabbitmq.com)                                                                                                                                   | Lightweight and easy to integrate for event-driven messaging (e.g., sending notifications).            |
| **Containerization**   | [Docker](https://www.docker.com)                                                                                                                                       | Standard tool for containerizing microservices to ensure consistent environments.                      |
| **Orchestration**      | [Kubernetes (Minikube)](https://minikube.sigs.k8s.io/docs/)                                                                                                            | Used to deploy, manage, and scale services for production-like environments.                           |
| **Testing Frameworks** | [pytest](https://pytest.org) / [Testcontainers](https://testcontainers-python.readthedocs.io)                                                                           | pytest for unit and integration tests, Testcontainers for dynamic environment simulation during testing.|
| **Security Testing**   | [OWASP ZAP](https://owasp.org/www-project-zap/)                                                                                                                        | Automated security scans for APIs to identify vulnerabilities like XSS and SQL Injection.              |
| **Performance Testing**| [Locust](https://locust.io) / [JMeter](https://jmeter.apache.org)                                                                                                      | Tools for load testing backend REST APIs to ensure acceptable performance under stress.                |

---

## 3. Tech Stack Breakdown

### 3.1 Frontend UI Microservice
- **Technology:** [React](https://react.dev)
- **Purpose:**
  - Provides a simple interface for users to interact with the app.
  - Focus is on enabling workflows that trigger backend actions (e.g., placing orders).
- **Features:**
  - Login and registration forms.
  - Restaurant and menu browsing.
  - Order summary and status tracking.

### 3.2 API Gateway
- **Technology:** [Flask](https://flask.palletsprojects.com) / [FastAPI](https://fastapi.tiangolo.com)
- **Purpose:**
  - Serves as the entry point for all client requests.
  - Handles routing, authentication, and request validation.
- **Features:**
  - Implements JWT-based token validation.
  - Routes requests to appropriate microservices.

### 3.3 Backend Microservices
- **Technology:** [Flask](https://flask.palletsprojects.com) / [FastAPI](https://fastapi.tiangolo.com)
- **Purpose:**
  - Each microservice handles a specific domain:
    - **Auth Service:** User authentication and token management.
    - **Restaurant Service:** CRUD operations for restaurants and menus.
    - **Order Service:** Manages order placement and status updates.
    - **Payment Service:** Simulates payment processing.
    - **Notification Service:** Sends notifications via RabbitMQ.
- **Features:**
  - Stateless design for scalability.
  - RESTful APIs adhering to OpenAPI specifications.

### 3.4 Databases
- **SQL Database ([PostgreSQL](https://www.postgresql.org)):**
  - Stores transactional data such as users, orders, and payment history.
- **NoSQL Database ([MongoDB](https://www.mongodb.com)):**
  - Stores semi-structured data like restaurant details and menus.

### 3.5 Message Queue
- **Technology:** [RabbitMQ](https://www.rabbitmq.com)
- **Purpose:**
  - Implements asynchronous messaging for sending notifications and other event-driven actions.
- **Example Use Case:**
  - Trigger a notification when an order is marked as "Ready for Pickup."

### 3.6 Containerization and Orchestration
- **Technologies:** [Docker](https://www.docker.com) / [Kubernetes (Minikube)](https://minikube.sigs.k8s.io/docs/)
- **Purpose:**
  - Ensure services can be deployed and managed independently.
  - Simulate production-like environments for integration and E2E testing.

---

## 4. API Design
- **Technology:** [OpenAPI (Swagger)](https://swagger.io/specification)
- **Purpose:**
  - Define REST APIs for all microservices.
  - Ensure clear contracts between frontend and backend.
- **Features:**
  - Each service exposes well-documented endpoints.
  - Supports testing tools like [Postman](https://www.postman.com) or [PACT](https://pact.io).

---

## 5. Testing and CI/CD Integration

### 5.1 Testing Frameworks
- **Unit and Integration Tests:** [pytest](https://pytest.org)
- **UI Automation Testing:** [Selenium](https://www.selenium.dev) / [Playwright](https://playwright.dev)
- **Security Testing:** [OWASP ZAP](https://owasp.org/www-project-zap/)
- **Performance Testing:** [Locust](https://locust.io) / [JMeter](https://jmeter.apache.org)

### 5.2 CI/CD Pipeline
- **Tool:** [GitHub Actions](https://github.com/features/actions)
- **Features:**
  - Linting and static code analysis.
  - Automated test execution for each service.
  - Build and deploy containers for staging environments.