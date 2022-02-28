package com.example.catsapi

import android.app.Application
import com.example.catsapi.data.remote.CatApiService
import com.example.catsapi.data.remote.CatPageSource
import com.example.catsapi.data.remote.RemoteInjector
import com.example.catsapi.repository.CatRepository

class CatsApp : Application() {

    private lateinit var catApiService: CatApiService
    private lateinit var catPageSource: CatPageSource
    lateinit var repository: CatRepository

    override fun onCreate() {
        super.onCreate()
        catApiService = RemoteInjector().configureRetrofit()
        catPageSource = CatPageSource(catApiService)
        repository = CatRepository(catPageSource)
    }
}