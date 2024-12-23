package com.example.itlinktesttask.domain

import com.example.itlinktesttask.network.ItLinkService
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api:ItLinkService
):Repository {

    override suspend fun retrieveFile(): Response<ResponseBody> {
        return api.downloadFile()
    }

}