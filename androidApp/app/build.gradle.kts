import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
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
        kotlinCompilerExtensionVersion = "1.1.0-rc01"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.androidApp.core)
    implementation(projects.androidApp.onbording)
    implementation(projects.androidApp.home)
    implementation(projects.uilogic.welcome)
    implementation(projects.uilogic.home)
    implementation(projects.uilogicImpl.welcome)
    implementation(projects.uilogicImpl.home)
    implementation(projects.usecase.onboarding)
    implementation(projects.usecase.home)
    implementation(projects.usecaseImpl.onboarding)
    implementation(projects.usecaseImpl.home)
    implementation(libs.google.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.coil.compose)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}


kapt {
    correctErrorTypes = true
}
