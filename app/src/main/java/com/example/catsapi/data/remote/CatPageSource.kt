package com.example.catsapi.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catsapi.model.Cat
import com.example.catsapi.utils.mapApiDataListToCatList
import retrofit2.HttpException
import java.io.IOException

class CatPageSource(
    private val catApiService: CatApiService,
) : PagingSource<Int, Cat>() {
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        try {
            val response = catApiService.getCats(page, pageSize)

            return if (response.isSuccessful) {
                val cats = response.body()?.mapApiDataListToCatList()

                val nextKey = if (cats?.size!! < pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1

                LoadResult.Page(cats, prevKey, nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}