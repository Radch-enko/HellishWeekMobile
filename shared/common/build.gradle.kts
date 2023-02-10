plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(project(Modules.resources))
                api(Dependencies.Datetime)
            }
        }
    }
}