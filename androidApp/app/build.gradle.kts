plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "tech.takahana.iconwallpaper.android"
        minSdk = 23
        targetSdk = 31
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