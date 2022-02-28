package com.example.catsapi.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.catsapi.model.Cat
import com.example.catsapi.repository.CatRepository

class CatViewModel(
    private val repository: CatRepository,
) : ViewModel() {

    fun fetchCatGoImagesLiveData(): LiveData<PagingData<Cat>> {
        return repository.getPageCastLiveData()
            .map { it ->
                it.map {
                    Cat(it.id,
                        it.imageUrl)
                }
            }
            .cachedIn(viewModelScope)
    }
}

class CatViewModelFactory(private val repository: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatViewModel(repository) as T
    }
}