# Online Code Execution Platform

This is a basic Spring Boot backend project for an Online Code Execution Platform. It provides RESTful APIs for health checks and code submissions.

## Features
- Health check endpoint: `GET /health`
- Code submission endpoint: `POST /api/submit`
- In-memory queue and status tracking for submissions

## Requirements
- Java 17
- Maven

## How to Run
1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run` to start the application.

## API Endpoints
### Health Check
- **URL**: `/health`
- **Method**: `GET`
- **Response**: `Service is running`

### Code Submission
- **URL**: `/api/submit`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "problemId": "string",
    "sourceCode": "string"
  }
  ```
- **Response**:
  ```json
  {
    "submissionId": "string",
    "problemId": "string",
    "sourceCode": "string",
    "status": "PENDING"
  }
  ```