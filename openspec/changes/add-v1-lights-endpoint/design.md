## Context

We need a new endpoint `GET /v1/lights` that lists all lights discovered from a Hue Bridge network. The application already uses Spring Boot and should integrate with Hue Bridge through a dedicated client layer that uses Spring `RestClient`. A key constraint is response-boundary safety: upstream REST client responses must never be returned directly to API consumers.

## Goals / Non-Goals

**Goals:**
- Add a controller endpoint `GET /v1/lights`.
- Route endpoint calls through `HueService`, which delegates to `HueRestClient`.
- Implement `HueRestClient` with Spring `RestClient` for `GET /clip/v2/resource/light`.
- Transform upstream responses into application-owned DTOs before returning to users.
- Keep headers/auth details in configuration (not controller/service call sites).

**Non-Goals:**
- Returning raw Hue Bridge JSON payloads to API consumers.
- Exposing sensitive Hue credentials in response payloads.
- Building write/update Hue endpoints in this change.

## Decisions

1. **Layered flow: Controller → Service → Rest Client**
   - Decision: endpoint logic remains thin and orchestration belongs in `HueService`; remote integration belongs in `HueRestClient`.
   - Rationale: preserves separation of concerns and testability boundaries.
   - Alternative rejected: direct controller-to-client calls (would couple web layer to integration concerns).

2. **Use Spring `RestClient` for Hue calls**
   - Decision: `HueRestClient` uses `RestClient` with configured base URL and required headers.
   - Rationale: consistent with current framework and simple typed request/response flow.
   - Alternative rejected: ad hoc HTTP library usage, which would diverge from framework patterns.

3. **Strict response mapping boundary**
   - Decision: `HueRestClient` response models are internal and mapped to API DTOs in service layer.
   - Rationale: enforces contract control and prevents accidental leakage of upstream payloads/fields.
   - Alternative rejected: passing-through client response payloads.

4. **External configuration for bridge settings**
   - Decision: hue bridge URL and app key come from config properties/environment variables.
   - Rationale: avoids hardcoding secrets and supports environment-specific deployment.
   - Alternative rejected: hardcoded URL/key in Java classes.

## Risks / Trade-offs

- **[Risk] Upstream response shape may differ from assumptions** → Mitigation: request sample payload and add mapping tests with representative fixture.
- **[Risk] Bridge connectivity/auth failures** → Mitigation: translate client errors to safe API error responses without exposing bridge internals.
- **[Trade-off] Extra mapping code** → Mitigation: accept small boilerplate to preserve long-term contract safety.

## Migration Plan

1. Add API DTOs for `GET /v1/lights` response shape.
2. Add `HueRestClient` request/response models and `RestClient`-based implementation.
3. Add `HueService` orchestration and mapping from client models to API DTOs.
4. Add `GET /v1/lights` controller endpoint.
5. Add tests:
   - controller/service behavior
   - mapping safety rule (no raw response pass-through)
   - failure-path handling
6. Validate with build and endpoint tests.

Rollback approach: remove endpoint wiring and client integration in one revert if integration risk emerges.

## Open Questions

- Please provide a representative Hue Bridge response payload for `/clip/v2/resource/light` so mapping can be finalized precisely.
- Should unknown/light-type-specific fields be dropped or preserved in a separate metadata structure?
