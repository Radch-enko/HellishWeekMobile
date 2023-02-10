object Plugins {
    const val androidGradlePluginVersion = "7.2.1"
    const val kotlinGradlePluginVersion = "1.7.20"
    const val kotlinSerializationGradlePluginVersion = "1.5.31"

    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion"
    const val kotlinSerializationGradlePlugin =
        "org.jetbrains.kotlin:kotlin-serialization:$kotlinSerializationGradlePluginVersion"
    const val androidBuildGradlePlugin =
        "com.android.tools.build:gradle:$androidGradlePluginVersion"
    const val mokoResourceGeneratorPlugin = Dependencies.MOKO.resources_generator
}