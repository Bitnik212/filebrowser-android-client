object AndroidConfig {
    const val COMPILE_SDK_VERSION = 31
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 31
    const val BUILD_TOOLS_VERSION = "30.0.0"

    val VERSION_CODE: Int = Environment.BUILD_NUMBER.getenv()?.toIntOrNull() ?: 1
    const val VERSION_NAME = "0.1.0"

    const val APP_ID = "moe.bitt.filebrowser"

    const val RELEASE_DEBUGGABLE = false

}



