object Dependencies {

    object AndroidX {

        object Compose {
            const val UI = "androidx.compose.ui:ui:${Versions.AndroidX.Compose.ui}"
            const val Tooling =
                "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose.tooling}"
            const val ToolingPreview =
                "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.Compose.toolingPreview}"
            const val Foundation =
                "androidx.compose.foundation:foundation:${Versions.AndroidX.Compose.foundation}"
            const val Material =
                "androidx.compose.material:material:${Versions.AndroidX.Compose.material}"
            const val Activity =
                "androidx.activity:activity-compose:${Versions.AndroidX.Compose.activity}"
        }
    }

    object Coroutines {
        const val Core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.CoroutinesVersion}"
        const val Android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.CoroutinesVersion}"
    }

    object Serialization {
        const val Json =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinSerializationVersion}"

    }

    object Ktor {
        object Client {
            const val Core =
                "io.ktor:ktor-client-core:${Versions.ktorVersion}"
            val contentNegotiation =
                "io.ktor:ktor-client-content-negotiation:${Versions.ktorVersion}"
            val commonLogging =
                "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
            val androidOKHttp =
                "io.ktor:ktor-client-okhttp:${Versions.ktorVersion}"
            val ios = "io.ktor:ktor-client-ios:${Versions.ktorVersion}"
            val commonSerialization =
                "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktorVersion}"
        }
    }


    object Koin {
        const val Core = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val Android =
            "io.insert-koin:koin-android:${Versions.koinVersion}"
    }

    object MOKO {
        const val resources_generator =
            "dev.icerock.moko:resources-generator:${Versions.mokoVersion}"
        const val resources =
            "dev.icerock.moko:resources:${Versions.mokoVersion}"
        const val resources_compose =
            "dev.icerock.moko:resources-compose:${Versions.mokoVersion}"
    }
}