package moe.bitt.filebrowser.api.service

import retrofit2.Call
import retrofit2.http.*

interface FolderService {

    @POST("resources/{folderPath}/?override=false")
    fun create(
        @Path("folderPath") folderPath: String
    ): Call<String>

    @PUT("resources/{folderPath}/")
    fun edit(
        @Body body: String
    ): Call<String>

    @PATCH("resources/{folderPath}/?action=rename&override=false&rename=false")
    fun rename(
        @Query("destination") destination: String
    ): Call<String>

    @DELETE("resources/{folderPath}/")
    fun delete(): Call<String>

}