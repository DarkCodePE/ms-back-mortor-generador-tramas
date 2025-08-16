# Claude Code Configuration - TRAMAS_DB Backend Service

## 🚨 CRITICAL PRINCIPLE: RADICAL CANDOR & PROVABLE REALITY

**ABSOLUTE RULE**: Under no circumstances will this agent lie, simulate, mislead, or create the illusion of functionality. All statements, code, and architectural decisions must be based on verifiable facts and proven capabilities.

1.  **NO SIMULATION**: Functionality will only be described as "working" after successful execution of its corresponding integration test.
2.  **TRUTH IN ARCHITECTURE**: Architectural diagrams and explanations will represent the *actual* implemented state, not a theoretical ideal.
3.  **FAIL BY TELLING THE TRUTH**: If a requirement is infeasible with the chosen stack (WebFlux, R2DBC), or if a dependency (`ClinicalDB`) is unavailable, this fact will be reported immediately and clearly. No workarounds will be implemented without explicit user instruction.
4.  **VERIFIABLE OUTPUT**: Every piece of generated code must be compilable, testable, and directly address a specific, documented requirement.

This principle supersedes all others. Brutal honesty is a fundamental constraint.

---

## Project Overview: TRAMAS_DB Backend Service

This project involves the creation of a high-performance, reactive backend service responsible for orchestrating the generation and validation of TEDEF health insurance claim files. The service will act as a bridge between the existing clinical database (`ClinicalDB`) and a new, specialized database (`TRAMAS_DB`).

**Core Technology Stack:**
-   **Language/Framework:** Java 17+ / Spring Boot 3+ with WebFlux
-   **Database Access:** R2DBC for non-blocking communication with SQL Server
-   **Architecture:** Modular, service-oriented architecture aligned with the `TRAMAS_DB` modular schema.

---

## SPARC Development Methodology

This project will adhere strictly to the SPARC (Specification, Pseudocode, Architecture, Refinement, Completion) methodology.

### 1. Specification Phase
-   **Action**: Analyze the PRD and the `TRAMAS_DB` schema.
-   **Output**: Create detailed technical specifications for each API endpoint and service component. Generate OpenAPI 3.0 contract (`openapi.yaml`).

### 2. Pseudocode Phase
-   **Action**: Develop algorithmic logic for the core ETL process (Extract from `ClinicalDB`, Transform, Validate against `Reglas`, Load into `Staging`).
-   **Output**: Language-agnostic pseudocode for the `LoteProcessingService`.

### 3. Architecture Phase
-   **Action**: Design the system's component architecture, focusing on the reactive data flow between repositories, services, and controllers.
-   **Output**: Mermaid diagrams (`sequenceDiagram`, `classDiagram`) illustrating the reactive streams and component interactions.

### 4. Refinement Phase (TDD)
-   **Action**: Execute Test-Driven Development. For each endpoint/service: write the integration test first (`WebTestClient`), watch it fail (Red), implement the feature until it passes (Green), and then refactor for clarity and performance.
-   **Output**: Fully implemented and tested code.

### 5. Completion Phase
-   **Action**: Integrate all components, perform end-to-end testing, and generate final documentation.
-   **Output**: A production-ready, containerized application (`Dockerfile`).

---

## ⚡ MANDATORY CONCURRENT EXECUTION & BATCH OPERATIONS

**GOLDEN RULE: "1 MESSAGE = ALL RELATED OPERATIONS"**. Efficiency is paramount. Sequential, single-operation messages are unacceptable.

1.  **File Operations**: When scaffolding a new module (e.g., `Lotes`), create the `LoteController.java`, `LoteService.java`, `LoteRepository.java`, and `LoteControllerTest.java` files in a **single `Write` or `MultiEdit` command**.
2.  **Task Management**: Define the entire plan for a feature, including all required components and tests, in a **single `TodoWrite` command**.
3.  **Bash Commands**: When setting up, run `mkdir -p` for all required directories in a single `Bash` command. When testing, `mvn clean install` and `docker build` can be batched.

**Example of CORRECT Concurrent Scaffolding:**

## Core Backend Development Principles

-   **API-First Design**: The `openapi.yaml` specification is the source of truth. All API development must adhere to this contract.
-   **Reactive All The Way**: All data access and business logic must be implemented using Project Reactor's reactive types (`Mono`, `Flux`). Blocking calls (`.block()`) are strictly forbidden outside of top-level test assertions.
-   **Clean Architecture**: The system will be layered: `Controller` -> `Service` -> `Repository`. The `Service` layer contains all business logic and is agnostic of the web layer. DTOs will be used to transfer data between layers.
-   **Configuration as Code**: All environment-specific configurations (database URLs, credentials) will be managed in `application-{profile}.yml` and sourced from environment variables. No hardcoded secrets.
-   **Immutable DTOs and Models**: Use Java Records or Lombok's `@Value` to ensure data transfer objects and models are immutable, preventing side effects in reactive streams.
-   **Comprehensive Testing**: Every public method in the service layer must have a corresponding unit test. Every API endpoint must have a corresponding integration test using `WebTestClient`.

---

## Available Agents for This Project

The `backend-api-code-writer-agent` will orchestrate the development by spawning specialized sub-agents as needed.

**Primary Agents to be Used:**

-   `architect`: Designs the reactive data flows and component interactions.
-   `coder`: Implements the Java/Spring Boot code.
-   `tester`: Writes JUnit 5 tests, both unit (`Mockito`) and integration (`WebTestClient`).
-   `api-docs`: Manages the `openapi.yaml` specification.
-   `db-designer`: Translates `TRAMAS_DB` schema into R2DBC entity classes.

**Concurrent Agent Spawning Pattern:**

---

## Project-Specific Build & Run Commands

### Standard Maven Commands
-   `mvn clean install`: Compile the code, run tests, and package the application.
-   `mvn spring-boot:run`: Run the application locally (using the `dev` profile).
-   `mvn test`: Run the unit and integration test suite.

### Docker Commands
-   `docker build -t tramas-backend:latest .`: Build the container image for the application.
-   `docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" tramas-backend:latest`: Run the application inside a Docker container.

---

## Important Notes
-   This agent is an expert in the R2DBC specification and will correctly handle non-blocking database access, including transaction management (`@Transactional`) in a reactive context.
-   All database interactions will be performed through Spring Data R2DBC repositories.
-   Error handling will be managed centrally using `@RestControllerAdvice` and will produce consistent, structured JSON error responses as defined in the PRD.
-   The primary focus is on correctness, performance, and adherence to reactive programming principles.