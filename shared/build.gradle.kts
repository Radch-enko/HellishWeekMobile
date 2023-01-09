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
//                api(project(Modules.core))
//                api(project(Modules.domain))
//                api(project(Modules.models))
//                api(project(Modules.network))
                api(project(Modules.resources))
//                api(project(Modules.injector))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.Client.androidOKHttp)
            }
        }
    }
}