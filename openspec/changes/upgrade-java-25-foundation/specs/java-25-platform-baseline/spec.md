## ADDED Requirements

### Requirement: Build System SHALL Target Java 25
The project build configuration SHALL require Java 25 for compilation and test execution.

#### Scenario: Gradle uses Java 25 compatibility
- **WHEN** the build configuration is evaluated
- **THEN** Java compatibility/toolchain settings are set to Java 25

### Requirement: Verification Workflows SHALL Succeed on Java 25
The project SHALL pass its standard build and test workflows when executed with Java 25.

#### Scenario: Build and tests pass
- **WHEN** maintainers run the standard Gradle verification workflow
- **THEN** compilation and tests complete successfully on Java 25

### Requirement: Baseline Upgrade SHALL Preserve Existing API Behavior
The Java baseline upgrade phase SHALL not change endpoint contracts or validation behavior.

#### Scenario: API behavior remains unchanged
- **WHEN** existing endpoint tests are run after the Java 25 baseline update
- **THEN** they continue to validate the same request/response behavior

### Requirement: Follow-up Feature Adoption SHALL Be Planned Separately
Java 25 feature adoption SHALL be scheduled as follow-up changes after baseline stability is established.

#### Scenario: Follow-up planning is explicit
- **WHEN** the baseline change is completed
- **THEN** tasks include explicit next-step planning for targeted Java 25 feature adoption
