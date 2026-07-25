# Hue REST API - Copilot Instructions

## Code Style & Framework
- Use Spring Boot 4.1+ best practices
- All DTOs should use Lombok (@Data, @AllArgsConstructor, @NoArgsConstructor)
- All REST endpoints should have comprehensive validation
- Use `@RestControllerAdvice` for global exception handling
- Use `@Configuration` classes for Spring bean configuration
- Dependency injection via @Autowired for Spring components

## Testing
- Framework: JUnit 5 (Jupiter) + MockMvc + AssertJ
- Controller tests use `@WebMvcTest` 
- Service tests use `@SpringBootTest` or mocked dependencies
- All assertions use AssertJ fluent API (not raw assertions)
- Test method naming: `test<Feature><Scenario><Expected>()`
- Mock external services with @MockBean
- Minimum 80% code coverage for new code

## Validation Rules
- Brightness values: 0.0-100.0 (decimal accepted, boundary values valid)
- Theme names: Required, non-blank strings
- All validation errors return HTTP 400 with detailed violation messages
- Use Jakarta validation annotations (@NotBlank, @DecimalMin, @DecimalMax)
- Error responses include field-level violation details

## API Endpoints
- Base path: `/light-groups`
- **Current Endpoints:**
  - POST `/light-groups/themes` - Apply theme to light group
    - Request: `{"theme": "string", "brightness": 0-100}`
    - Response: HTTP 200 with applied status, or HTTP 400 with validation errors
- Request/Response: application/json
- See API_DOCUMENTATION.md for full specification

## Project Structure
- `src/main/java/com/hue/api/`
  - `controller/` - REST endpoints and exception handlers
  - `service/` - Business logic
  - `model/` - DTOs and domain models
  - `config/` - Spring configuration (RestClient, etc.)
- `src/test/java/com/hue/api/` - Mirror structure for tests
- `app/pom.xml` - Maven configuration with dependencies

## Git Commits
- Use conventional commits: `feat:`, `fix:`, `docs:`, `test:`, `refactor:`
- Include brief description of changes in first line
- Include implementation details and reasoning in commit body
- Example: 
  ```
  feat: Add GET endpoint for light group themes
  
  - Implement GET /light-groups/themes/{id} endpoint
  - Add service method for retrieving theme configuration
  - Include comprehensive test coverage for happy path and error cases
  ```

## Dependencies & Versions
- Spring Boot: 3.3.2 (with parent pom)
- Java: 17+
- JUnit: 5.x (via spring-boot-starter-test)
- AssertJ: Latest (via spring-boot-starter-test)
- Mockito: Latest (via spring-boot-starter-test)
- Validation API: Jakarta (jakarta.validation)
- Lombok: For reducing boilerplate

## Error Handling
- Return HTTP 400 for validation errors
- Return HTTP 500 for unexpected server errors
- Always include error response with:
  - `error`: Error type/category
  - `message`: User-friendly message
  - `status`: HTTP status code
  - `violations`: Field-level validation errors (if applicable)
- Log errors with appropriate severity (WARN for expected, ERROR for unexpected)

## Documentation
- Keep API_DOCUMENTATION.md in sync with endpoint changes
- Include request/response examples in documentation
- Document all validation rules and error codes
- Add JavaDoc comments for public methods and classes

## Future Enhancements (Out of Scope - MVP)
- Database persistence (JPA/Hibernate)
- Authentication/Authorization
- GET/PUT/DELETE endpoints
- Hue Bridge direct integration
- Rate limiting and throttling
- Caching layer
