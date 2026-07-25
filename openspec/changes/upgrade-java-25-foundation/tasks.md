## 1. Java 25 Build Baseline

- [x] 1.1 Update `app/build.gradle` Java compatibility/toolchain settings from 17 to 25
- [x] 1.2 Confirm Gradle wrapper and plugins remain compatible with Java 25
- [x] 1.3 Remove or update any hardcoded Java 17 references in build files

## 2. Verification on Java 25

- [x] 2.1 Run `./gradlew clean build` with Java 25 and fix compatibility issues
- [x] 2.2 Run targeted runtime check with `./gradlew bootRun` to verify app startup
- [x] 2.3 Validate existing API behavior with current endpoint tests

## 3. Documentation and Workflow Updates

- [x] 3.1 Update `.github/copilot-instructions.md` to require Java 25 baseline
- [x] 3.2 Update any developer setup/build notes that mention Java 17
- [x] 3.3 Add a short note describing this as baseline-only (no feature adoption yet)

## 4. Plan Follow-up Java 25 Feature Adoption

- [x] 4.1 Identify 2-3 Java 25 features suitable for this codebase
- [x] 4.2 Propose a sequenced follow-up change for adopting selected features
