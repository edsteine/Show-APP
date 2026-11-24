# Shows App project report

## Project summary

Shows App is a native Android TVMaze client using Kotlin, Jetpack Compose,
Retrofit, Room, Hilt, Paging, and Coil. It is suitable as a portfolio project.

## Verification

- Gradle wrapper is executable.
- Existing release artifact verified:
  `app/build/outputs/apk/release/app-release-unsigned.apk`.
- APK metadata verified with Android `aapt`: package
  `com.edsteine.showsapp`, version `1.0`, min SDK 24, target SDK 34.
- Gradle configuration was made deterministic with in-process Kotlin
  compilation and limited workers.
- Some local Gradle checks hang in the shared machine environment because of
  competing Gradle daemons/dependency resolution.

## Release requirements

The checked artifact is unsigned. Configure a new external release keystore,
run unit/lint checks in a clean environment, validate TVMaze/network behavior,
and build a signed bundle before publishing. Previously exposed credentials and
keystores must remain revoked and outside the repository.
