## MODIFIED Requirements

### Requirement: Spring Boot REST API foundation
The system SHALL establish a Spring Boot 4.1.0 REST API with proper project structure, Gradle build configuration, and Java Records for data models.

#### Scenario: API starts successfully
- **WHEN** the Spring Boot application initializes via `gradle bootRun`
- **THEN** the application starts without errors and listens on port 8080

#### Scenario: Project structure is organized
- **WHEN** examining the source code
- **THEN** code is organized into controller, service, model, and config packages under src/main/java/com/hue/api

### Requirement: RestClient configuration
The system SHALL provide a configured Spring RestClient bean for external REST communication, built with Gradle and Spring Boot 4.1.0.

#### Scenario: RestClient bean available
- **WHEN** a service requests RestClient via dependency injection
- **THEN** a properly configured RestClient instance is provided

#### Scenario: RestClient built with Spring Boot 4.1.0
- **WHEN** examining the Spring Boot version
- **THEN** RestClient version matches Spring Boot 4.1.0 releases

### Requirement: Test infrastructure
The system SHALL support JUnit 5 with MockMvc for controller testing and AssertJ for assertions, managed via Gradle.

#### Scenario: JUnit 5 tests execute
- **WHEN** running `gradle test`
- **THEN** all JUnit 5 tests execute successfully

#### Scenario: MockMvc available in tests
- **WHEN** a test class uses MockMvc via Spring Test
- **THEN** MockMvc is injected and available for HTTP simulation

#### Scenario: AssertJ assertions used
- **WHEN** writing test assertions
- **THEN** AssertJ fluent assertions are available and working

### Requirement: Dependency management
The system SHALL include all required dependencies for Spring Boot 4.1.0, JUnit 5, Spring Test, and AssertJ managed through Gradle.

#### Scenario: Dependencies resolve via Gradle
- **WHEN** running `gradle build`
- **THEN** all dependencies download and build succeeds

#### Scenario: Spring Boot 4.1.0 in use
- **WHEN** examining the classpath
- **THEN** Spring Boot 4.1.0, JUnit 5, and AssertJ are present at correct versions
