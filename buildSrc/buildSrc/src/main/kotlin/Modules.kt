object Modules {
    const val kmmShared = ":shared"
    const val core = "$kmmShared:core"
    const val network = "$kmmShared:network"
    const val models = "$kmmShared:models"
    const val domain = "$kmmShared:domain"
    const val resources = "$kmmShared:resources"
    const val common = "$kmmShared:common"
    const val navigation = "$kmmShared:navigation"
    const val injector = "$kmmShared:injector"

    object Features {
        private const val features = "$kmmShared:features"
        const val authorization = "$features:authorization"
        const val registration = "$features:registration"
        const val home = "$features:home"
    }
}