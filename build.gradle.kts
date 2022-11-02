buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.kotlinGradlePlugin)
        classpath(Plugins.AndroidBuildGradlePlugin)
        classpath(Plugins.kotlinSerializationGradlePlugin)
        classpath(Plugins.MokoResourceGenerator)
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
