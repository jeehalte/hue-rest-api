## ADDED Requirements

### Requirement: Apply theme to light group
The system SHALL provide a REST endpoint that accepts a theme name and brightness level and applies the theme to a light group.

#### Scenario: Successfully apply theme
- **WHEN** a POST request is made to `/light-groups/themes` with valid theme name and brightness (0-100)
- **THEN** the system returns HTTP 200 OK with confirmation message

#### Scenario: Missing theme name
- **WHEN** a POST request is made to `/light-groups/themes` without a theme field
- **THEN** the system returns HTTP 400 Bad Request with error message indicating missing theme

#### Scenario: Invalid brightness value
- **WHEN** a POST request is made to `/light-groups/themes` with brightness outside 0-100 range
- **THEN** the system returns HTTP 400 Bad Request with error message indicating invalid brightness range

#### Scenario: Brightness boundary values
- **WHEN** a POST request is made with brightness exactly 0 or 100
- **THEN** the system accepts the request and returns HTTP 200 OK

### Requirement: Accept themed light group request
The system SHALL parse incoming JSON requests with theme and brightness parameters.

#### Scenario: Valid request structure
- **WHEN** a client sends POST `/light-groups/themes` with `{"theme": "cool", "brightness": 75.5}`
- **THEN** the request is parsed successfully

#### Scenario: Brightness as decimal
- **WHEN** brightness is provided as a decimal number (e.g., 75.5)
- **THEN** the system accepts and processes the decimal value
