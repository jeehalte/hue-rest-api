## 1. Project Setup

- [x] 1.1 Initialize Spring Boot 4.1 project with Maven
- [x] 1.2 Configure pom.xml with Spring Boot, JUnit 5, Spring Test, and AssertJ dependencies
- [x] 1.3 Create project directory structure (controller, service, model packages)
- [x] 1.4 Verify project builds successfully with `mvn clean install`

## 2. RestClient Infrastructure

- [x] 2.1 Create RestClient configuration class with bean definition
- [x] 2.2 Set up RestClient for HTTP communication
- [x] 2.3 Create integration tests for RestClient (using mock/stub servers)

## 3. Request/Response Models

- [x] 3.1 Create ThemeRequest DTO with theme and brightness fields
- [x] 3.2 Add validation annotations to ThemeRequest (NotBlank for theme, Min/Max for brightness)
- [x] 3.3 Create ThemeResponse DTO for API responses
- [x] 3.4 Create error response model for validation failures

## 4. Light Group Themes Endpoint

- [x] 4.1 Create LightGroupController with @RestController
- [x] 4.2 Implement POST /light-groups/themes endpoint method
- [x] 4.3 Create LightGroupService business logic class
- [x] 4.4 Implement theme application logic in service

## 5. Validation and Error Handling

- [x] 5.1 Configure validation error handling with @ExceptionHandler
- [x] 5.2 Return HTTP 400 with clear error messages for invalid requests
- [x] 5.3 Test validation for missing theme field
- [x] 5.4 Test validation for brightness outside 0-100 range

## 6. Test Suite

- [x] 6.1 Create controller test class with MockMvc
- [x] 6.2 Test successful theme application (happy path, HTTP 200)
- [x] 6.3 Test missing theme validation (HTTP 400)
- [x] 6.4 Test invalid brightness validation (HTTP 400)
- [x] 6.5 Test brightness boundary values (0 and 100)
- [x] 6.6 Test decimal brightness values (e.g., 75.5)
- [x] 6.7 Verify all assertions use AssertJ fluent API

## 7. Verification and Documentation

- [x] 7.1 Run full test suite (`mvn test`) and verify all pass
- [x] 7.2 Verify RestClient bean is injectable
- [x] 7.3 Verify application starts on configured port
- [x] 7.4 Document API endpoint (request/response format, error codes)
