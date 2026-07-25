## ADDED Requirements

### Requirement: Java Records for data models
The system SHALL use Java Records for all DTOs and data models instead of Lombok POJOs, eliminating boilerplate and providing immutable data structures.

#### Scenario: ThemeRequest as Record
- **WHEN** creating a ThemeRequest object with theme and brightness
- **THEN** the object is an immutable Record with automatic getter methods and equals/hashCode

#### Scenario: Record validation with compact constructor
- **WHEN** attempting to create a Record with invalid brightness (outside 0-100)
- **THEN** the compact constructor throws validation exception

#### Scenario: JSON serialization of Records
- **WHEN** converting a Record to JSON (request body)
- **THEN** Jackson serializes it correctly with @JsonProperty annotations if needed

#### Scenario: JSON deserialization to Records
- **WHEN** receiving JSON request body for a Record type
- **THEN** Jackson deserializes it to the correct Record instance with validation

### Requirement: Eliminate Lombok dependency
The system SHALL remove Lombok from the dependency list since Records provide equivalent functionality without external libraries.

#### Scenario: No Lombok annotations in code
- **WHEN** examining source files
- **THEN** no `@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`, or other Lombok annotations exist

#### Scenario: Reduced code volume
- **WHEN** comparing Record implementation vs Lombok POJO
- **THEN** Record implementation is ~80% smaller in lines of code
