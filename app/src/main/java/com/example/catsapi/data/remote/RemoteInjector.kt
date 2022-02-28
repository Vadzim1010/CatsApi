package com.example.catsapi.data.remote

import com.example.catsapi.utils.BASE_API
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteInjector {

    fun configureRetrofit(): CatApiService {
        val httpLoginInterceptor = HttpLoggingInterceptor()
        val httpNetworkInterceptor: Interceptor = AuthInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .addInterceptor(httpNetworkInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CatApiService::class.java)
    }
}