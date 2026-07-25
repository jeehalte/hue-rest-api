## Context

This is the initial implementation of the Hue REST API using Spring Boot 4.1. The API will serve as the backbone for managing Philips Hue light group configurations. This first phase focuses on theme management as a foundation for future feature expansion.

## Goals / Non-Goals

**Goals:**
- Establish a working Spring Boot REST API foundation
- Implement `/light-groups/themes` POST endpoint with theme and brightness parameters
- Set up external REST communication via Spring RestClient
- Create comprehensive test coverage with JUnit 5, MockMvc, and AssertJ assertions

**Non-Goals:**
- GET/PUT/DELETE endpoints for themes (POST only for this phase)
- Database persistence (in-memory or cache-based for now)
- Authentication/authorization (out of scope for MVP)
- Hue Bridge direct integration (RestClient is for extensibility)

## Decisions

**Decision 1: Use Spring RestClient over RestTemplate**
- **Rationale**: RestClient is the modern, fluent approach in Spring 6+ aligned with Boot 4.1. It offers cleaner API and better async support for future scalability.
- **Alternative Considered**: RestTemplate (legacy, deprecated path), WebClient (reactive - overkill for initial endpoint)

**Decision 2: JUnit 5 + MockMvc + AssertJ stack**
- **Rationale**: JUnit 5 provides modern parameterized testing and extension model. MockMvc gives precise HTTP-level testing. AssertJ offers fluent, readable assertions.
- **Alternative Considered**: JUnit 4 (older), raw assertions (less readable)

**Decision 3: Single endpoint POST /light-groups/themes**
- **Rationale**: Focused MVP scope. POST allows future evolution to PUT/PATCH for updates.
- **Request body**: `{ "theme": string, "brightness": number (0-100) }`
- **Response**: 200 OK with confirmation or 400 Bad Request with validation errors

**Decision 4: Project structure**
- `src/main/java/com/hue/api/` - Main API code
- `src/main/java/com/hue/api/controller/` - REST endpoints
- `src/main/java/com/hue/api/service/` - Business logic
- `src/test/java/com/hue/api/` - Test mirror structure

## Risks / Trade-offs

**[Risk] Stateless design (no database)**
→ *Mitigation*: Accepted for MVP. Database will be added in Phase 2 when persistence requirements are clearer.

**[Risk] Brightness validation (0-100 range)**
→ *Mitigation*: Implement explicit validation in request DTO and return 400 with clear error messages.

**[Risk] External RestClient integration not used initially**
→ *Mitigation*: Set up infrastructure now, enable integration tests via mock servers (WireMock or Spring Cloud Contract).

## Migration Plan

1. Initialize Spring Boot 4.1 project with dependencies
2. Create project structure and base classes
3. Implement ThemeRequest DTO with validation
4. Implement /light-groups/themes POST endpoint
5. Create MockMvc tests for endpoint (happy path + error cases)
6. Verify all tests pass and assertions use AssertJ
