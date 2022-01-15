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
include(":iosExport")
include(":shared")
include(":uilogic:welcome")
include(":usecase:onboarding")
include(":uilogicImpl:welcome")
include(":usecaseImpl:onboarding")
include(":androidApp:core")
include(":androidApp:onbording")
include(":androidApp:home")
include(":usecase:home")
include(":usecase:home:kotlin")
include(":usecaseImpl:home")
include(":uilogic:home")
include(":uilogicImpl:home")
