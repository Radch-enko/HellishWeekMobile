plugins {
    id("com.android.library")
}

android {
    compileSdk = AndroidConfigData.compileSdkVersion

    defaultConfig {
        minSdk = AndroidConfigData.minSdkVersion
        targetSdk = AndroidConfigData.targetSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        compileSdk = AndroidConfigData.compileSdkVersion
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        defaultConfig {
            minSdk = AndroidConfigData.minSdkVersion
            targetSdk = AndroidConfigData.targetSdkVersion
        }
    }
}
