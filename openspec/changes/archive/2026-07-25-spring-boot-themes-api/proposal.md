## Why

Hue REST API needs a working implementation to expose theme management capabilities for the Philips Hue ecosystem. Starting with light group theme management allows us to build a solid foundation with Spring Boot that can be extended for additional resources in the future.

## What Changes

- Create a new Spring Boot 4.1 REST API project with base structure and dependencies
- Implement `/light-groups/themes` POST endpoint that accepts theme and brightness configuration
- Integrate Spring RestClient for external REST communication
- Establish testing infrastructure with JUnit 5, MockMvc, and AssertJ

## Capabilities

### New Capabilities
- `light-group-themes`: POST endpoint to create/apply themes to light groups with theme name and brightness level
- `spring-boot-rest-api-base`: Core Spring Boot REST API infrastructure with RestClient setup

### Modified Capabilities

## Impact

- New Spring Boot project structure and configuration
- Dependencies: Spring Boot 4.1, JUnit 5, Spring Test (MockMvc), AssertJ
- API: Single initial endpoint at `/light-groups/themes`
- External integration: RestClient for communicating with external Hue services
