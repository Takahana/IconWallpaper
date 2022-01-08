package tech.takahana.iconwallpaper.gradle

import org.gradle.api.Project

fun Project.propertyAsBoolean(propertyName: String): Boolean {
    return findProperty(propertyName).toString().toBoolean()
}