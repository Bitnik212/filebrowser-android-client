object Module {

    const val APP = ":app:"

    const val Common = "$APP:common"

    const val Network = "$APP:network"

    const val API = "$APP:api"

    const val DATABASE = "$APP:database"

    val ALL = listOf<String>(Common, API)

    val ALL_FEATURES = listOf(Common, API, DATABASE)

}
