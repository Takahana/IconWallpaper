import tech.takahana.iconwallpaper.gradle.propertyAsInt

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
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
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.mockk)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core)
            }
        }
        val androidTest by getting {
            dependencies {
                dependsOn(commonTest)
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
    defaultConfig {
        minSdk = propertyAsInt("android.minSdk")
        targetSdk = propertyAsInt("android.targetSdk")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    with(sourceSets["main"]) {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        java.srcDirs("src/androidMain/kotlin", "src/commonMain/kotlin")
        res.srcDirs("src/androidMain/res")
    }
    with(sourceSets["test"]) {
        java.srcDirs("src/androidTest/kotlin", "src/commonTest/kotlin")
        res.srcDirs("src/androidTest/res")
    }
}
dependencies {
    implementation("androidx.test.ext:junit-ktx:1.1.4")
}
