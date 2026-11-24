plugins {
    id("com.android.application") // For building Android applications
    id("kotlin-android") // For Android-specific Kotlin support
    id("kotlin-kapt") // For Kotlin Annotation Processing Tool (KAPT)
    id("kotlin-parcelize") // For Kotlin's Parcelize feature
    id("dagger.hilt.android.plugin") // For Hilt dependency injection
}


android {
    namespace = "com.edsteine.showsapp"
    compileSdk = 34

    val releaseStoreFile = providers.gradleProperty("RELEASE_STORE_FILE")
        .orElse(providers.environmentVariable("RELEASE_STORE_FILE")).orNull
    val releaseStorePassword = providers.gradleProperty("RELEASE_STORE_PASSWORD")
        .orElse(providers.environmentVariable("RELEASE_STORE_PASSWORD")).orNull
    val releaseKeyAlias = providers.gradleProperty("RELEASE_KEY_ALIAS")
        .orElse(providers.environmentVariable("RELEASE_KEY_ALIAS")).orNull
    val releaseKeyPassword = providers.gradleProperty("RELEASE_KEY_PASSWORD")
        .orElse(providers.environmentVariable("RELEASE_KEY_PASSWORD")).orNull
    val hasReleaseSigning = listOf(
        releaseStoreFile,
        releaseStorePassword,
        releaseKeyAlias,
        releaseKeyPassword
    ).all { !it.isNullOrBlank() }

    signingConfigs {
        create("release") {
            if (hasReleaseSigning) {
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword
                storeFile = file(releaseStoreFile!!)
                storePassword = releaseStorePassword
            }
        }
    }
    defaultConfig {
        applicationId = "com.edsteine.showsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        testFunctionalTest = true
        testHandleProfiling = true
    }


    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("Boolean", "ENABLE_TIMBER", "true")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
            buildConfigField("Boolean", "ENABLE_TIMBER", "false")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }



    kapt {
        correctErrorTypes = true
        javacOptions {
            option("-source", "17")
            option("-target", "17")
        }
    }




    buildFeatures {
        viewBinding = true
        compose = true
        dataBinding = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-Xlint:deprecation")
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/LICENSE")
        }
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
    buildToolsVersion = "34.0.0"
    ndkVersion = "23.1.7779620"
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // Network and JSON
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // Paging and Navigation
    implementation(libs.paging.runtime)
    implementation(libs.navigation.compose)

    // Jetpack Compose
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.window.size)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.runtime)

    // Image Loading
    implementation(libs.coil.compose)
    implementation(libs.glide)
    implementation(libs.jsoup)

    // Dependency Injection with Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Room for local database
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // Logging with Timber
    implementation(libs.timber)

    // Unit Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)

    // Mocking
    testImplementation(libs.mockito.core)

    // Coroutine Support
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Hilt Testing
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)
}
