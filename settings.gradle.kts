enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "IconWallpaper"
include(":androidApp:app")
include(":androidApp:core")
include(":androidApp:onbording")
include(":androidApp:home")
include(":iosExport")
include(":shared")
include(":uilogic:welcome")
include(":uilogic:home")
include(":uilogicImpl:welcome")
include(":uilogicImpl:home")
include(":usecase:onboarding")
include(":usecase:home")
include(":usecaseImpl:onboarding")
include(":usecaseImpl:home")
include(":usecaseFake:home")
include(":repository")
include(":repositoryImpl")
include(":repositoryFake")
