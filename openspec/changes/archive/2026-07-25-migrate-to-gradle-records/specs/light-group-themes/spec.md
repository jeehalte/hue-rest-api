## MODIFIED Requirements

### Requirement: Apply theme to light group
The system SHALL provide a REST endpoint that accepts theme name and brightness level as immutable Records and applies the theme to a light group.

#### Scenario: Successfully apply theme
- **WHEN** a POST request is made to `/light-groups/themes` with valid ThemeRequest Record
- **THEN** the system returns HTTP 200 OK with ThemeResponse Record containing confirmation

#### Scenario: Missing theme name
- **WHEN** a POST request is made without a theme field
- **THEN** the system returns HTTP 400 Bad Request with ErrorResponse Record

#### Scenario: Invalid brightness value
- **WHEN** a POST request is made with brightness outside 0-100 range
- **THEN** the system returns HTTP 400 Bad Request with ErrorResponse Record

#### Scenario: Brightness boundary values
- **WHEN** a POST request is made with brightness exactly 0 or 100
- **THEN** the system accepts the request and returns HTTP 200 OK

### Requirement: Accept themed light group request as Record
The system SHALL parse incoming JSON requests into ThemeRequest Record with validation via compact constructor.

#### Scenario: Valid request structure deserialized to Record
- **WHEN** a client sends POST `/light-groups/themes` with `{"theme": "cool", "brightness": 75.5}`
- **THEN** the request is deserialized to ThemeRequest Record successfully

#### Scenario: Record validation in compact constructor
- **WHEN** attempting to deserialize invalid data (e.g., brightness 150)
- **THEN** the compact constructor validation fires and returns HTTP 400

#### Scenario: Brightness as decimal in Record
- **WHEN** brightness is provided as a decimal number (e.g., 75.5)
- **THEN** the Record field accepts and preserves the decimal value
