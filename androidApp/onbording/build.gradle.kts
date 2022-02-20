import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = propertyAsInt("android.compileSdk")

    defaultConfig {
        minSdk = propertyAsInt("android.minSdk")
        targetSdk = propertyAsInt("android.minSdk")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc01"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.coil.compose)
    implementation(projects.androidApp.core)
}
