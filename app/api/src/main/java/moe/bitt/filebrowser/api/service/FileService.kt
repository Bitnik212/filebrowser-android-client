package moe.bitt.filebrowser.api.service

import moe.bitt.filebrowser.common.model.api.FileModel
import retrofit2.Call
import retrofit2.http.*

interface FileService {

    @POST("resources/{filePath}?override=false")
    fun create(
        @Path("filePath") filePath: String
    ): Call<String>

    @PUT("resources/{filePath}")
    fun edit(
        @Body body: String
    ): Call<String>

    @PATCH("resources/{filePath}?action=rename&override=false&rename=false")
    fun rename(
        @Query("destination") destination: String
    ): Call<String>

    @GET("resources/{filePath}")
    fun read(): Call<FileModel>

    @DELETE("resources/{filePath}")
    fun delete(): Call<String>

}