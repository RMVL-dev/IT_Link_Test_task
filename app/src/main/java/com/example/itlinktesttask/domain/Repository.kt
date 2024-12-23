package com.example.itlinktesttask.domain

import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {

    suspend fun retrieveFile(): Response<ResponseBody>

}