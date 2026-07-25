## 1. Gradle Build System Setup

- [x] 1.1 Create build.gradle with Spring Boot plugin and parent configuration
- [x] 1.2 Configure Java compiler target (Java 17) and source compatibility
- [x] 1.3 Add all Spring Boot dependencies (web, validation, test, etc.)
- [x] 1.4 Configure test execution with JUnit 5, MockMvc, and AssertJ
- [x] 1.5 Add Spring Boot application plugin for bootRun and bootJar tasks
- [x] 1.6 Create Gradle wrapper (gradlew, gradlew.bat) for CI/CD consistency
- [x] 1.7 Verify `gradle build` compiles project successfully
- [x] 1.8 Delete pom.xml and verify no Maven build references remain

## 2. Spring Boot Upgrade to 4.1.0

- [x] 2.1 Update build.gradle to use Spring Boot 4.1.0 parent BOM
- [x] 2.2 Verify Spring RestClient is available in Spring Boot 4.1.0
- [x] 2.3 Check for any breaking changes in dependencies
- [x] 2.4 Update RestClientConfig.java if needed for Spring Boot 4.1.0 API changes

## 3. ThemeRequest DTO - Migrate to Record

- [x] 3.1 Create new ThemeRequest.java as a Record with theme and brightness fields
- [x] 3.2 Add compact constructor for validation (theme not blank, brightness 0-100)
- [x] 3.3 Add @JsonProperty annotations for JSON serialization
- [x] 3.4 Remove old Lombok-based ThemeRequest implementation
- [x] 3.5 Verify test compilation with new Record class
- [x] 3.6 Run tests to ensure Record works with MockMvc and Jackson

## 4. ThemeResponse DTO - Migrate to Record

- [x] 4.1 Create new ThemeResponse.java as a Record with message, theme, brightness, status fields
- [x] 4.2 Add @JsonProperty annotations for JSON serialization/deserialization
- [x] 4.3 Remove old Lombok-based ThemeResponse implementation
- [x] 4.4 Test JSON serialization of ThemeResponse Record

## 5. ErrorResponse DTO - Migrate to Record

- [x] 5.1 Create new ErrorResponse.java as a Record with error, message, status, violations fields
- [x] 5.2 Handle List<String> violations field in compact constructor
- [x] 5.3 Add @JsonProperty annotations for proper JSON mapping
- [x] 5.4 Remove old Lombok-based ErrorResponse implementation
- [x] 5.5 Test error response Record with validation failure scenarios

## 6. Update RestClientConfig and Services

- [x] 6.1 Verify RestClientConfig.java works with Spring Boot 4.1.0
- [x] 6.2 Update LightGroupService.java to use new Record types (ThemeRequest, ThemeResponse)
- [x] 6.3 Update LightGroupController.java to use Record types in method signatures
- [x] 6.4 Update GlobalExceptionHandler.java to work with ErrorResponse Record

## 7. Remove Lombok Dependency

- [x] 7.1 Remove Lombok from build.gradle dependencies
- [x] 7.2 Verify no Lombok annotations exist in any Java source files
- [x] 7.3 Remove any Lombok IDE plugins/configuration
- [x] 7.4 Test project builds without Lombok

## 8. Update Tests for Record Types

- [x] 8.1 Update RestClientConfigTest to work with Spring Boot 4.1.0
- [x] 8.2 Update LightGroupControllerTest to use ThemeRequest Record objects
- [x] 8.3 Verify MockMvc correctly deserializes JSON to ThemeRequest Records
- [x] 8.4 Verify test assertions still use AssertJ fluent API
- [x] 8.5 Test validation scenarios with Record compact constructors

## 9. Build and Verification

- [x] 9.1 Run `gradle clean build` and verify all compilation succeeds
- [x] 9.2 Run `gradle test` and verify all tests pass with new Record types
- [x] 9.3 Run `gradle bootRun` and verify application starts on port 8080
- [x] 9.4 Test API endpoint with curl/Postman using valid and invalid payloads
- [x] 9.5 Verify build artifacts in build/libs/ directory
- [x] 9.6 Check IDE indexing is complete (IntelliJ, VS Code, Eclipse)

## 10. Documentation and Final Checks

- [x] 10.1 Update API_DOCUMENTATION.md if needed (no API changes, only implementation)
- [x] 10.2 Verify .github/copilot-instructions.md mentions Record usage
- [x] 10.3 Add README notes about Gradle build system migration
- [x] 10.4 Verify git diff shows pom.xml removal and build.gradle addition
- [x] 10.5 Final smoke test: clean project clone and build from scratch
