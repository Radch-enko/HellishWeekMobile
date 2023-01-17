plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Koin.Core)
                implementation(project(Modules.common))
            }
        }
    }
}