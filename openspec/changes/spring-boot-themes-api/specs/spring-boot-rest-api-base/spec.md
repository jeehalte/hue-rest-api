## ADDED Requirements

### Requirement: Spring Boot REST API foundation
The system SHALL establish a Spring Boot 4.1 REST API with proper project structure and configuration.

#### Scenario: API starts successfully
- **WHEN** the Spring Boot application initializes
- **THEN** the application starts without errors and listens on port 8080 (or configured port)

#### Scenario: Project structure is organized
- **WHEN** examining the source code
- **THEN** code is organized into controller, service, and model packages

### Requirement: RestClient configuration
The system SHALL provide a configured Spring RestClient bean for external REST communication.

#### Scenario: RestClient bean available
- **WHEN** a service requests RestClient via dependency injection
- **THEN** a properly configured RestClient instance is provided

#### Scenario: RestClient can make GET requests
- **WHEN** RestClient is used to make a GET request to an external endpoint
- **THEN** the request succeeds (with appropriate mock/test setup)

### Requirement: Test infrastructure
The system SHALL support JUnit 5 with MockMvc for controller testing and AssertJ for assertions.

#### Scenario: JUnit 5 tests execute
- **WHEN** running tests with `mvn test`
- **THEN** all JUnit 5 tests execute successfully

#### Scenario: MockMvc available in tests
- **WHEN** a test class uses MockMvc
- **THEN** MockMvc is injected and available for HTTP simulation

#### Scenario: AssertJ assertions used
- **WHEN** writing test assertions
- **THEN** AssertJ fluent assertions are available and working (e.g., `assertThat(result).isEqualTo(expected)`)

### Requirement: Dependency management
The system SHALL include all required dependencies for Spring Boot 4.1, JUnit 5, Spring Test, and AssertJ.

#### Scenario: Dependencies resolve
- **WHEN** running `mvn clean install` or equivalent
- **THEN** all dependencies download and build succeeds

#### Scenario: Correct versions in use
- **WHEN** examining the classpath
- **THEN** Spring Boot 4.1, JUnit 5, and AssertJ are present at appropriate versions
