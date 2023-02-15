plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("native.cocoapods")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            export(project(Modules.resources))
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(Dependencies.Koin.Core)

                api(project(Modules.common))
                api(project(Modules.navigation))
                api(project(Modules.resources))
                api(project(Modules.injector))
                api(project(Modules.Features.authorization))
                api(project(Modules.Features.registration))
                api(project(Modules.Features.home))
                api(project(Modules.Features.notifications))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.Client.androidOKHttp)
            }
        }
    }
}