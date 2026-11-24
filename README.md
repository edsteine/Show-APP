# Shows App

Shows App is a native Android catalog for discovering television shows through
the public TVMaze API. Users can browse results, search the catalog, open show
details, and save favorites locally.

## Features

- TVMaze search and show listing.
- Show details with network-loaded artwork.
- Favorites and local persistence.
- Kotlin, Jetpack Compose, Retrofit, Room, Hilt, Paging, and Coil.
- Separate debug and release build types with optional external signing.

## Architecture

The app is organized around presentation, domain, and data responsibilities.
Compose renders the UI, ViewModels coordinate state, Retrofit provides the
remote API, Room stores local data, and Hilt supplies dependencies. Android
resources and Kotlin sources live under `app/src`.

## Run locally

Requirements: Android Studio, JDK 17, Android SDK 34, and an API 24+ device or
emulator.

```bash
./gradlew test
./gradlew lint
./gradlew assembleDebug
```

The app requires internet access for TVMaze data and images.

## Release signing

Keep release credentials outside the repository and provide these Gradle
properties or environment variables:

```text
RELEASE_STORE_FILE=/absolute/path/to/release.jks
RELEASE_STORE_PASSWORD=...
RELEASE_KEY_ALIAS=...
RELEASE_KEY_PASSWORD=...
```

Then run `./gradlew bundleRelease`. The repository must never contain a
keystore or password. See `REPORT.md` for verification and remaining release
checks.
