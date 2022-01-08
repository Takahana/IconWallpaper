import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = propertyAsInt("android.compileSdk")
    defaultConfig {
        applicationId = "tech.takahana.iconwallpaper.android"
        minSdk = propertyAsInt("android.minSdk")
        targetSdk = propertyAsInt("android.minSdk")
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta04"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.androidApp.onbording)
    implementation(projects.uilogic.welcome)
    implementation(projects.uilogicImpl.welcome)
    implementation(projects.usecase.onboarding)
    implementation(projects.usecaseImpl.onboarding)
    implementation(libs.google.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.coil.compose)
}