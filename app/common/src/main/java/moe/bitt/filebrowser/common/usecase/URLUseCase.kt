package moe.bitt.filebrowser.common.usecase

class URLUseCase(val url: String) {

    val hostname: String
        get() = getHostName()

    private fun getHostName(): String {
        return url
            .replace("http://", "")
            .replace("https://", "")
            .split("/")[0]
    }
}