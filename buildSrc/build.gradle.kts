plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

val rootDirProject = file("../")

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}

dependencies {
    implementation(Plugins.kotlinGradlePlugin)
    implementation(Plugins.androidBuildGradlePlugin)
    implementation(Plugins.kotlinSerializationGradlePlugin)
    implementation(Plugins.mokoResourceGeneratorPlugin)
}