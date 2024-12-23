package com.example.itlinktesttask.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming


interface ItLinkService {

    @Streaming
    @GET("test/images.txt")
    suspend fun downloadFile(): Response<ResponseBody>
}