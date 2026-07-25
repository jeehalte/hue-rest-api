## ADDED Requirements

### Requirement: GET /v1/lights SHALL Return Application-Owned Light Data
The system SHALL expose `GET /v1/lights` and return a response model owned by this API, containing the list of lights for the user’s Hue network.

#### Scenario: Successful light listing
- **WHEN** a client calls `GET /v1/lights` and Hue Bridge data is available
- **THEN** the API returns HTTP 200 with a list of mapped light objects in the API-defined response schema

### Requirement: HueService SHALL Delegate Hue Bridge Calls to HueRestClient
The system SHALL implement `HueService` as the orchestration layer and SHALL delegate upstream Hue Bridge reads to `HueRestClient`.

#### Scenario: Service orchestrates client call
- **WHEN** `GET /v1/lights` is handled
- **THEN** controller calls `HueService`, and `HueService` retrieves raw bridge data only through `HueRestClient`

### Requirement: HueRestClient SHALL Use Spring RestClient
The system SHALL implement `HueRestClient` using Spring `RestClient` for Hue Bridge integration calls.

#### Scenario: Bridge GET call execution
- **WHEN** `HueService` requests all lights
- **THEN** `HueRestClient` executes a `GET` call to `/clip/v2/resource/light` with required headers including `Accept: application/json` and Hue application key authentication

### Requirement: Hue Authentication MUST Be Environment-Provided and Non-Persistent
The system MUST source Hue authentication details (for example, application key/token) from environment-driven configuration and MUST NOT persist those values in source code, committed files, API payloads, or logs.

#### Scenario: Secure auth configuration
- **WHEN** the application starts and configures Hue integration
- **THEN** auth values are read from environment variables (or equivalent secret-injected runtime config) and are not stored or returned by application endpoints

#### Scenario: Standardized application key variable
- **WHEN** the Hue application key is provided for runtime configuration
- **THEN** the system reads it from the `HUE_APPLICATION_KEY` environment variable

### Requirement: Upstream Client Responses MUST NOT Be Returned Directly
The system MUST NOT return raw payloads from `HueRestClient` (or any future REST client) directly to API users.

#### Scenario: Response boundary enforcement
- **WHEN** upstream Hue response includes fields not part of API contract
- **THEN** only mapped/approved API response fields are returned, and raw upstream payload is not exposed

### Requirement: Upstream Failures SHALL Be Mapped to Safe API Errors
The system SHALL translate Hue Bridge client failures into API-safe error responses without leaking secrets or raw upstream response bodies.

#### Scenario: Bridge call fails
- **WHEN** Hue Bridge request fails due to connectivity or authentication
- **THEN** API returns a controlled error response and does not expose raw bridge response content
