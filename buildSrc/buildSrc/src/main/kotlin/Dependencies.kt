object Dependencies {

    object AndroidX {

        object Compose {
            const val ui = "1.2.1"
            const val tooling = "1.2.1"
            const val toolingPreview = "1.2.1"
            const val foundation = "1.2.1"
            const val material = "1.2.1"
            const val activity = "1.5.1"
            const val navigationCompose = "2.5.3"
            const val splashScreen = "1.0.0"

            const val UI = "androidx.compose.ui:ui:$ui"
            const val Tooling =
                "androidx.compose.ui:ui-tooling:$tooling"
            const val ToolingPreview =
                "androidx.compose.ui:ui-tooling-preview:$toolingPreview"
            const val Foundation =
                "androidx.compose.foundation:foundation:$foundation"
            const val Material =
                "androidx.compose.material:material:$material"
            const val MaterialIcons =
                "androidx.compose.material:material-icons-extended:$ui"
            const val Activity =
                "androidx.activity:activity-compose:$activity"
            const val Navigation =
                "androidx.navigation:navigation-compose:$navigationCompose"

            const val SplashScreen = "androidx.core:core-splashscreen:$splashScreen"

            object Dialogs {
                private const val version = "1.0.3"
                const val DialogsCore = "com.maxkeppeler.sheets-compose-dialogs:core:$version"
                const val Calendar = "com.maxkeppeler.sheets-compose-dialogs:calendar:$version"
                const val DateTime = "com.maxkeppeler.sheets-compose-dialogs:date-time:$version"
            }
        }
    }

    object Accompanist {
        const val horizontalPager = "0.25.1"
        const val version = "0.29.0-alpha"
        const val HorizontalPager =
            "com.google.accompanist:accompanist-pager:$horizontalPager"
        const val HorizontalIndicators =
            "com.google.accompanist:accompanist-pager-indicators:$horizontalPager"
        const val AnimatedNavigation =
            "com.google.accompanist:accompanist-navigation-animation:$version"
        const val SystemUI = "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object Coroutines {
        const val coroutinesVersion = "1.6.3"
        const val Core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val Android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Serialization {
        const val kotlinSerializationVersion = "1.3.3"

        const val Json =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion"

    }

    object Ktor {
        const val ktorVersion = "2.0.3"

        object Client {
            const val Core =
                "io.ktor:ktor-client-core:$ktorVersion"
            val contentNegotiation =
                "io.ktor:ktor-client-content-negotiation:$ktorVersion"
            val commonLogging =
                "io.ktor:ktor-client-logging:$ktorVersion"
            val androidOKHttp =
                "io.ktor:ktor-client-okhttp:$ktorVersion"
            val ios = "io.ktor:ktor-client-ios:$ktorVersion"
            val commonSerialization =
                "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
        }
    }


    object Koin {
        private const val koinVersion = "3.2.0"
        const val Core = "io.insert-koin:koin-core:$koinVersion"
        const val Android =
            "io.insert-koin:koin-android:$koinVersion"
        const val Compose = "io.insert-koin:koin-androidx-compose:$koinVersion"
    }

    object MOKO {
        const val mokoVersion = "0.20.1"
        const val resources_generator =
            "dev.icerock.moko:resources-generator:$mokoVersion"
        const val resources =
            "dev.icerock.moko:resources:$mokoVersion"
        const val resources_compose =
            "dev.icerock.moko:resources-compose:$mokoVersion"
    }

    const val Timber = "com.jakewharton.timber:timber:5.0.1"
    const val Datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
}