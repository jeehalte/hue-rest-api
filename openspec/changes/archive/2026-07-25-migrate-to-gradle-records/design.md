## Context

The project currently uses Maven 3.x with Spring Boot 3.3.2 and Lombok-based POJOs for data models. Gradle has become the industry standard for modern Java projects with superior performance, caching, and build speed. Java Records (available since Java 16, finalized in Java 21) provide immutable data models with zero boilerplate.

## Goals / Non-Goals

**Goals:**
- Migrate from Maven pom.xml to Gradle build.gradle
- Update Gradle to version 9.6.1 with latest plugins
- Upgrade Spring Boot to version 4.1.0
- Refactor all DTOs (ThemeRequest, ThemeResponse, ErrorResponse) to use Java Records
- Maintain 100% test coverage and all existing functionality
- Ensure smooth IDE integration with IntelliJ, Eclipse, and VS Code

**Non-Goals:**
- Changing the Java version (remains 17+)
- Restructuring project directories
- Updating endpoints or API contracts
- Adding new features or endpoints

## Decisions

**Decision 1: Gradle over Maven**
- **Rationale**: Gradle offers 2-3x faster incremental builds, superior caching, and better dependency resolution. Industry standard for modern projects.
- **Alternative Considered**: Keep Maven (familiar to team but slower) vs Maven 4.0 (not addressing core issues)

**Decision 2: Java Records for Data Models**
- **Rationale**: Records eliminate Lombok dependency, provide immutable data by default, are part of Java language standard since Java 16 (finalized 21), reduce code volume by 80%.
- **Alternative Considered**: Keep Lombok POJOs (backward compat but more boilerplate), try Project Valhalla (not stable yet)

**Decision 3: Gradle 9.6.1 Version**
- **Rationale**: Latest stable version with best performance, dependency insights, and version catalog support.
- **Alternative Considered**: Gradle 8.x (stable but older), Gradle 10.0 (not yet released)

**Decision 4: Phased Migration Strategy**
- Convert build system first (Maven → Gradle) with zero code changes
- Then refactor data models to Records in parallel
- Run tests after each phase to ensure stability

## Risks / Trade-offs

**[Risk] IDE Indexing Delay**
→ *Mitigation*: Clear IDE caches (.idea/, .vscode/, .gradle/) and re-index. Takes ~2 minutes.

**[Risk] Gradle Learning Curve for Team**
→ *Mitigation*: Gradle is widely adopted; provide documentation and pair programming for first tasks.

**[Risk] Records Cannot Have Complex Initialization**
→ *Mitigation*: Keep validation logic in service layer. Records use compact constructors for basic validation.

**[Risk] Spring Boot 4.1.0 Compatibility**
→ *Mitigation*: Verify no breaking changes in dependencies. Run full test suite to catch issues early.

**[Risk] Build Cache Invalidation**
→ *Mitigation*: Use `gradle clean` sparingly; Gradle's build cache is smart about invalidation.

## Migration Plan

1. **Phase 1: Gradle Build System (Day 1)**
   - Create build.gradle with Spring Boot plugin and all dependencies
   - Configure test tasks (JUnit 5, MockMvc, AssertJ)
   - Verify `gradle build` and `gradle test` work
   - Remove pom.xml

2. **Phase 2: Java Records Refactoring (Day 2-3)**
   - Refactor ThemeRequest → Record (with validation)
   - Refactor ThemeResponse → Record
   - Refactor ErrorResponse → Record
   - Remove Lombok dependency from build.gradle
   - Run tests after each conversion

3. **Phase 3: Testing & Verification (Day 3)**
   - Run full test suite: `gradle test`
   - Build application: `gradle build`
   - Verify application starts: `gradle bootRun`
   - IDE re-indexing and verification

## Open Questions

- Should we use Gradle version catalog for dependency management? (Recommended: Yes)
- Do we need Gradle wrapper for CI/CD consistency? (Recommended: Yes, add gradlew)
- Should Records use compact constructors for validation? (Yes, where possible)
