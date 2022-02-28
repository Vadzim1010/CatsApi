package com.example.catsapi.data.remote

import com.example.catsapi.utils.API_KEY
import com.example.catsapi.utils.HEADER_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HEADER_API_KEY, API_KEY)
            .build()
        return chain.proceed(request)
    }
}
