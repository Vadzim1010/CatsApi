package com.example.catsapi.data.remote

import com.example.catsapi.data.model.ApiData
import com.example.catsapi.utils.GET_CATS_API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET(GET_CATS_API)
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<List<ApiData>>
}