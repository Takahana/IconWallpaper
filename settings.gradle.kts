enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "IconWallpaper"
include(":androidApp")
include(":shared")
include(":uilogic:welcome")
include(":usecase:onboarding")
include(":uilogicImpl:welcome")
include(":usecaseImpl:onboarding")
include(":iosExport")
