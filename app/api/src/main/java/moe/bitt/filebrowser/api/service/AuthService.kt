package moe.bitt.filebrowser.api.service

import moe.bitt.filebrowser.common.model.api.Constants
import moe.bitt.filebrowser.api.model.request.SignInRequest
import moe.bitt.filebrowser.api.model.request.SignUpRequest
import moe.bitt.filebrowser.common.model.api.Constants.TEMP_HOSTNAME
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthService {

    @POST("login")
    fun signIn(
        @Body body: SignInRequest,
        @Header(TEMP_HOSTNAME) url: String
    ): Call<String>

    @POST("signup")
    fun signUp(
        @Body body: SignUpRequest,
        @Header(TEMP_HOSTNAME) url: String
    ): Call<String>

    @POST("renew")
    fun renew(
        @Header(Constants.AUTH_HEADER) token: String,
        @Header(TEMP_HOSTNAME) url: String
    ): Call<String>

}