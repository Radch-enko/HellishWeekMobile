buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

group = AndroidConfigData.group

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
