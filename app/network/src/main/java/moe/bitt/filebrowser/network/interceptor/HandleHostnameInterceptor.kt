package moe.bitt.filebrowser.network.interceptor

import moe.bitt.filebrowser.common.model.api.Constants.TEMP_HOSTNAME
import okhttp3.Interceptor
import okhttp3.Response

class HandleHostnameInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        val hostname = handleHostname(request.url.host)
        val newHostname = handleHostname(request.headers[TEMP_HOSTNAME]?: "")
        var requestBuilder = request.newBuilder()
        if (hostname !== newHostname) {
            requestBuilder = requestBuilder.url(
                handleURL(
                    handleHostname(newHostname),
                    handleHostname(hostname),
                    url
                )
            )
        }
        return chain.proceed(requestBuilder.build())
    }

    private fun handleHostname(hostname: String): String {
        return hostname
            .replace("http://", "")
            .replace("https://", "")
            .replace("/", "")
    }

    private fun handleURL(newHostname: String, oldHostname: String, url: String): String {
        val URLSplited = url.split("://")
        val hostLessURL = URLSplited[1].replace(oldHostname, "$newHostname/api")
        return URLSplited[0] + "://" + hostLessURL
    }
}