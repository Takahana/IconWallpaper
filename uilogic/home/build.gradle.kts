import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("kapt")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    // iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "home"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.koru)
                configurations.get("kapt").dependencies.add(
                    org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency(
                        "com.futuremind", "koru-processor", libs.versions.koru.get()
                    )
                )
                implementation(libs.kotlinx.coroutines.core)
                implementation(projects.shared)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        // val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            // iosSimulatorArm64Main.dependsOn(this)
            kotlin.srcDir("${buildDir.absolutePath}/generated/source/kaptKotlin/")
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        // val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            // iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = propertyAsInt("android.compileSdk")
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = propertyAsInt("android.minSdk")
        targetSdk = propertyAsInt("android.targetSdk")
    }
}
