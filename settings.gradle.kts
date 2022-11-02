pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "HellishWeekMobile"
include(":androidApp")
include(":shared:network")
include(":shared:core")
include(":shared:domain")
include(":shared:presenter")
include(":shared:common")
include(":shared:models")
include(":shared:resources")
include(":shared")
include(":shared:injector")
