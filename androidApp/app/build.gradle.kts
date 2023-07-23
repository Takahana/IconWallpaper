import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = propertyAsInt("android.compileSdk")
    defaultConfig {
        applicationId = "tech.takahana.iconwallpaper.android"
        minSdk = propertyAsInt("android.minSdk")
        targetSdk = propertyAsInt("android.targetSdk")
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-rc01"
    }
    namespace = "tech.takahana.iconwallpaper.android"
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
    implementation(projects.repository)
    implementation(projects.repositoryImpl)
    implementation(libs.google.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.coil.compose)
    implementation(libs.ucrop)
    testImplementation(libs.junit)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
}

kapt {
    correctErrorTypes = true
}

/**
 * google-services.json の変更を無視する。
 */
val skipWorktreeWithGoogleServicesJson by tasks.register(
    "skipWorktreeWithGoogleServicesJson",
    Exec::class.java
) {
    commandLine = listOf(
        "git",
        "update-index",
        "--skip-worktree",
        "${project.projectDir.absolutePath}/google-services.json"
    )
}

/**
 * ローカルにあるデータを google-services.json にコピーする。
 */
val replaceGoogleServicesJson by tasks.register("replaceGoogleServicesJson") {
    dependsOn(skipWorktreeWithGoogleServicesJson)
    doLast {
        val localGoogleServiceJson =
            Paths.get("${project.projectDir.absolutePath}/google-services-local.json")
        val googleServiceJson = Paths.get("${project.projectDir.absolutePath}/google-services.json")
        Files.copy(localGoogleServiceJson, googleServiceJson, StandardCopyOption.REPLACE_EXISTING)
    }
}

