## 1. API and Domain Models

- [x] 1.1 Define API response DTOs for `GET /v1/lights` (application-owned schema)
- [x] 1.2 Define internal Hue client response models for Hue Bridge payload parsing
- [x] 1.3 Add mapping logic from internal client models to API DTOs

## 2. Hue Rest Client Integration

- [x] 2.1 Add Hue configuration properties for bridge base URL and application key sourced from environment variables (`HUE_APPLICATION_KEY` for key)
- [x] 2.2 Implement `HueRestClient` using Spring `RestClient`
- [x] 2.3 Implement `GET /clip/v2/resource/light` call with required headers
- [x] 2.4 Add integration-layer error handling that does not expose raw upstream payloads
- [x] 2.5 Ensure Hue auth values are never persisted in source/committed config, responses, or logs

## 3. Service and Controller Wiring

- [x] 3.1 Implement `HueService` method to retrieve lights through `HueRestClient`
- [x] 3.2 Implement `GET /v1/lights` controller endpoint calling `HueService`
- [x] 3.3 Ensure endpoint returns only mapped API DTOs (never raw client response)

## 4. Tests and Contract Safety

- [x] 4.1 Add controller tests for success response shape and HTTP status
- [x] 4.2 Add tests for failure mapping from upstream errors to safe API errors
- [x] 4.3 Add explicit boundary test verifying raw `HueRestClient` payload fields are never exposed

## 5. Documentation and Input Finalization

- [x] 5.1 Capture sample Hue Bridge response fixture from user and align internal parser
- [x] 5.2 Update API documentation for `GET /v1/lights` response schema and error behavior
- [x] 5.3 Document the “no raw client response” rule for future REST clients
