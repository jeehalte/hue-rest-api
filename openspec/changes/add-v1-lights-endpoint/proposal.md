## Why

The API needs a read endpoint to list Hue lights from the local Hue network, starting with `GET /v1/lights`. We also need to enforce a strict boundary so upstream REST client payloads are never returned directly to API consumers.

## What Changes

- Add a new `GET /v1/lights` endpoint that returns application-owned response data for all lights.
- Add `HueService` orchestration to fetch lights through a dedicated `HueRestClient`.
- Implement `HueRestClient` with Spring `RestClient` to call Hue Bridge `GET /clip/v2/resource/light`.
- Add explicit response mapping so raw Hue Bridge responses are not exposed to users.
- Add tests covering successful light listing and response-shape boundary guarantees.

## Capabilities

### New Capabilities
- `v1-lights-read-endpoint`: Provides `GET /v1/lights` backed by `HueService` + `HueRestClient` with strict response transformation boundaries.

### Modified Capabilities
- None.

## Impact

- Affected code: controller, service, client, DTO/model mapping, and tests.
- External dependency: Hue Bridge REST API over HTTPS with `hue-application-key` authentication header.
- API surface: introduces a new read endpoint at `/v1/lights`.
- Security/contract impact: enforces “never return raw client responses” as a hard requirement for this and future REST clients.
