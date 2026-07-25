## Why

The app is currently pinned to Java 17 and cannot take advantage of Java 25 language/runtime improvements. We need a safe, incremental path that starts with build/runtime readiness before adopting newer Java features.

## What Changes

- Upgrade project build configuration from Java 17 to Java 25 baseline.
- Ensure local development and CI can compile, test, and run with Java 25.
- Add guardrails for phased adoption of Java 25 features so follow-on changes remain low-risk.
- Keep API behavior unchanged in this first step; this change focuses on platform readiness.

## Capabilities

### New Capabilities
- `java-25-platform-baseline`: Establishes Java 25 as the project baseline in build, tooling, and verification workflows.

### Modified Capabilities
- None.

## Impact

- Affected code: Gradle build configuration and related docs/instructions.
- Runtime/tooling: Java 25 required for build and execution.
- CI/developer environments: JDK setup must be updated to Java 25.
- API/contracts: No endpoint or payload changes in this phase.
