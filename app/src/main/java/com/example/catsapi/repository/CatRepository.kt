package com.example.catsapi.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.catsapi.data.remote.CatPageSource
import com.example.catsapi.model.Cat

class CatRepository(
    private val catPageSource: CatPageSource,
) {

    fun getPageCastLiveData(): LiveData<PagingData<Cat>> {
        val pagingConfig = PagingConfig(pageSize = 10)
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                catPageSource
            }
        ).liveData
    }
}