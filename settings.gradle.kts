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
include(":shared:navigation")
include(":shared:features")
include(":shared:common")
include(":shared:features:authorization")
include(":shared:injector")
include(":shared:features:registration")
include(":shared:features:home")
include(":shared:features:notifications")
