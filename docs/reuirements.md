```markdown
# Restaurama Food Delivery App Requirements

This document lists all the key requirements of the Restaurama food delivery app, both functional and non-functional.

## Functional Requirements

### User Workflow
- Register/Login
- Browse restaurants and menus
- Place an order with payment simulation
- View order status (real-time updates via polling or mock WebSocket)
- Receive notification when order is ready

### Backend Services
- Clear REST API definition using OpenAPI specs
- Basic CRUD operations for restaurants, orders, and users
- Mock payment integration for simplicity

## Non-Functional Requirements

### Testability
- APIs must expose well-defined endpoints
- Mock data and services for isolated testing
- Structured test data for reproducibility

### Simplicity
- No complex real-time features like live maps
- Minimal UI design to focus on backend and testing

### Scalability
- Design services to be containerized for horizontal scaling
- Use Kubernetes for service orchestration
```