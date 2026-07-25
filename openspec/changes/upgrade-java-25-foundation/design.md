## Context

The project recently migrated to Gradle and currently targets Java 17. The next initiative is to move the runtime and build baseline to Java 25, but in controlled phases. This first change focuses on baseline readiness only: build configuration, environment expectations, and verification. Functional API behavior remains unchanged.

## Goals / Non-Goals

**Goals:**
- Set Java 25 as the required toolchain level for this repository.
- Keep build and test workflows stable after the version bump.
- Define a safe path for later adoption of Java 25 language features.

**Non-Goals:**
- Refactoring business logic to use Java 25 features in this change.
- Any API contract, endpoint, or payload modifications.
- Performance tuning unrelated to version upgrade readiness.

## Decisions

1. **Incremental upgrade strategy**
   - Decision: split upgrade into a baseline change first, then feature-adoption changes.
   - Rationale: isolates platform risk from refactor risk and keeps rollback small.
   - Alternative considered: upgrade + language refactors together. Rejected due to larger blast radius.

2. **Build-first enforcement**
   - Decision: enforce Java 25 through Gradle configuration as the source of truth.
   - Rationale: ensures consistent local and CI behavior.
   - Alternative considered: documentation-only upgrade guidance. Rejected because it is not enforceable.

3. **Behavior-preserving scope**
   - Decision: keep code behavior and tests functionally equivalent in this phase.
   - Rationale: verifies that failures are due to platform changes, not feature rewrites.
   - Alternative considered: opportunistic cleanup while upgrading. Rejected to reduce uncertainty.

## Risks / Trade-offs

- **[Risk] Toolchain availability mismatch (local/CI)**
  - Mitigation: document Java 25 requirement and validate with build/test commands.

- **[Risk] Dependency compatibility with Java 25**
  - Mitigation: run full Gradle build and tests as acceptance criteria for this phase.

- **[Trade-off] Delaying Java 25 feature use**
  - Mitigation: define explicit follow-up tasks for adopting new language/runtime features.

## Migration Plan

1. Update Gradle Java compatibility/toolchain settings to Java 25.
2. Verify build lifecycle (`build`, `test`, runtime startup) under Java 25.
3. Update project guidance/docs to reflect new Java baseline.
4. Queue next change focused on targeted Java 25 feature adoption.

Rollback approach: revert Java compatibility settings to Java 17 if critical blockers appear.

## Open Questions

- Which Java 25 features should be adopted first in follow-up changes (e.g., specific language constructs or JVM/runtime features)?
- Should CI add an explicit matrix during transition (Java 25 only vs temporary dual-version checks)?
