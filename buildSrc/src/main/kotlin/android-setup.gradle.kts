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
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
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
