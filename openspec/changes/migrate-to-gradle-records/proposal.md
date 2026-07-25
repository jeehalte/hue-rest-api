## Why

The current Maven-based build system can be modernized to Gradle for faster builds and better dependency management. Additionally, upgrading Spring Boot to 4.1.0 and leveraging Java Records for DTOs will improve code clarity, reduce boilerplate, and align with modern Java best practices.

## What Changes

- Migrate build system from Maven (pom.xml) to Gradle (build.gradle)
- Update Gradle to version 9.6.1
- Upgrade Spring Boot to version 4.1.0 (latest stable)
- Refactor all DTOs and data models from POJOs with Lombok to immutable Java Records
- Update test configuration for Gradle-based builds
- Remove Maven-specific plugins and replace with Gradle equivalents

## Capabilities

### New Capabilities
- `gradle-build-system`: Gradle 9.6.1 build configuration with proper dependency management and plugins
- `java-records-models`: Immutable data models using Java Records instead of Lombok POJOs

### Modified Capabilities
- `spring-boot-rest-api-base`: Update Spring Boot version to 4.1.0 and Gradle build configuration
- `light-group-themes`: Refactor request/response models to use Java Records

## Impact

- **Build System**: Complete transition from Maven to Gradle (faster incremental builds, better caching)
- **Dependencies**: Spring Boot 3.3.2 → 4.1.0, Gradle 7.x → 9.6.1
- **Code**: All DTOs (ThemeRequest, ThemeResponse, ErrorResponse) converted from Lombok POJOs to Records
- **Breaking Change**: `pom.xml` removed, `build.gradle` becomes single source of build configuration
- **Testing**: Gradle Test framework integration instead of Maven Surefire
- **IDE Integration**: IntelliJ, Eclipse, and VS Code may require re-indexing after migration
