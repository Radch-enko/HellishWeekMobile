pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "HellishWeekMobile"
include(":androidApp")
include(":shared")
include(":shared:network")
include(":shared:core")
include(":shared:domain")
include(":shared:models")
include(":shared:resources")
include(":shared:injector")
include(":shared:navigation")
include(":shared:features")
