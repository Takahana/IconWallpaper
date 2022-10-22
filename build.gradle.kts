import de.undercouch.gradle.tasks.download.Download
import tech.takahana.iconwallpaper.gradle.propertyAsBoolean

plugins {
    id("de.undercouch.download") version "4.1.2"
}

if (propertyAsBoolean("project.applyProjectDependencyGraph")) {
    val projectDependencyGraphGradle = File("./gradle", "projectDependencyGraph.gradle")
    if (projectDependencyGraphGradle.exists()) {
        apply(from = "gradle/projectDependencyGraph.gradle")
    }
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
        classpath(libs.dagger.hilt.agp)
    }
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        filter {
            exclude("**/generated/**")
            ignoreFailures.set(true)
            reporters {
                reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
                reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("downloadProjectDependencyGraphGradle", Download::class) {
    val file = File("./gradle", "projectDependencyGraph.gradle")
    src("https://raw.githubusercontent.com/JakeWharton/SdkSearch/9616caf5775a02daafac4f66ae935b943f725888/gradle/projectDependencyGraph.gradle")
    dest(file)
}
