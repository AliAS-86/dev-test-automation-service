Introduction:
- Project Objective:
    - The resturama-food-app is a food delivery app MVP, founded and created with 1 goal in mind, that is being used to demonstrate QA automation framework, covering multiple stages of testing (unit, interface, intgration, E2E, performance, and security)

- High-Level Architecture:
    - The 10000 ft view at this web-based app is that it's microservice-based architecture deployed locally for testing using Docker Compose and in production-like environment using single node Kubernetes cluster, with communication between UI and backend as well as inter-services using REST APIs, and message queue for event-driven actions

- Architecture Diagram
    ![Architecture Diagram](resources/restaurama-app.drawio.png)

- Tech Stack Overview:

| Layer                 | Technology         | Reason for Choice                                                                                   |
|-----------------------|--------------------|-----------------------------------------------------------------------------------------------------|
| Frontend              | React              | Lightweight and widely used framework, excellent for creating simple, responsive UIs.               |
| API Gateway           | Flask/FastAPI      | Simple Python frameworks for routing API requests and managing authentication.                      |
| Authentication Service| Flask (Python)     | Provides flexibility for JWT-based authentication with minimal setup.                               |
| Backend Microservices | Flask/FastAPI      | Enables rapid development and easy API integration.                                                 |
| Databases (SQL)       | PostgreSQL         | Reliable and feature-rich relational database for handling transactional data (e.g., users, orders).|
| Databases (NoSQL)     | MongoDB            | Ideal for storing semi-structured data like menus and restaurant details.                           |
| Message Queue         | RabbitMQ           | Lightweight and easy to integrate for event-driven messaging (e.g., sending notifications).         |
| Containerization      | Docker             | Standard tool for containerizing microservices to ensure consistent environments.                   |
| Orchestration         | Kubernetes (Minikube) | Used to deploy, manage, and scale services for production-like environments.                     |
| Testing Frameworks    | pytest, Testcontainers | pytest for unit and integration tests, Testcontainers for dynamic environment simulation during testing. |
| Security Testing      | OWASP ZAP          | Automated security scans for APIs to identify vulnerabilities like XSS and SQL Injection.           |
| Performance Testing   | Locust/JMeter      | Tools for load testing backend REST APIs to ensure acceptable performance under stress.             |