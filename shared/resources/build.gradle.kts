plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("dev.icerock.mobile.multiplatform-resources")
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
        framework {
            baseName = "resources"
            export(Dependencies.MOKO.resources)
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                api(Dependencies.MOKO.resources)
            }
        }
        val androidMain by getting {
            dependencies {
                api(Dependencies.MOKO.resources_compose)
                implementation(Dependencies.AndroidX.Compose.UI)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.blesscompany.hellishweek.resources"
    multiplatformResourcesClassName = "Resources"
}