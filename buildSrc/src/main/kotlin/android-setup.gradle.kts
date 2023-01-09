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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            res.setSrcDirs(listOf("src/androidMain/res", "src/commonMain/resources"))
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}
