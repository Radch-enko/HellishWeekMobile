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
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.AndroidX.Compose.UI)
    implementation(Dependencies.AndroidX.Compose.Tooling)
    implementation(Dependencies.AndroidX.Compose.ToolingPreview)
    implementation(Dependencies.AndroidX.Compose.Foundation)
    implementation(Dependencies.AndroidX.Compose.Material)
    implementation(Dependencies.AndroidX.Compose.Material3)
    implementation(Dependencies.AndroidX.Compose.MaterialIcons)
    implementation(Dependencies.AndroidX.Compose.Activity)
    implementation(Dependencies.AndroidX.Compose.Navigation)
    implementation(Dependencies.AndroidX.Compose.SplashScreen)
    implementation(Dependencies.Accompanist.HorizontalPager)
    implementation(Dependencies.Accompanist.HorizontalIndicators)
    implementation(Dependencies.Accompanist.AnimatedNavigation)
    implementation(Dependencies.Accompanist.SystemUI)

    implementation(Dependencies.Koin.Core)
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compose)
    implementation(Dependencies.Timber)
    implementation(Dependencies.AndroidX.Compose.Dialogs.DialogsCore)
    implementation(Dependencies.AndroidX.Compose.Dialogs.DateTime)
}