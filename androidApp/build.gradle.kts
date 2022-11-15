plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = AndroidConfigData.applicationId
    compileSdk = AndroidConfigData.compileSdkVersion
    defaultConfig {
        applicationId = AndroidConfigData.applicationId
        minSdk = AndroidConfigData.minSdkVersion
        targetSdk = AndroidConfigData.targetSdkVersion
        versionCode = AndroidConfigData.versionCode
        versionName = AndroidConfigData.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfigData.kotlinCompilerExtensionVersion
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.AndroidX.Compose.UI)
    implementation(Dependencies.AndroidX.Compose.Tooling)
    implementation(Dependencies.AndroidX.Compose.ToolingPreview)
    implementation(Dependencies.AndroidX.Compose.Foundation)
    implementation(Dependencies.AndroidX.Compose.Material)
    implementation(Dependencies.AndroidX.Compose.MaterialIcons)
    implementation(Dependencies.AndroidX.Compose.Activity)
    implementation(Dependencies.AndroidX.Compose.Navigation)
    implementation(Dependencies.AndroidX.Compose.SplashScreen)
    implementation(Dependencies.Accompanist.HorizontalPager)
    implementation(Dependencies.Accompanist.HorizontalIndicators)
}