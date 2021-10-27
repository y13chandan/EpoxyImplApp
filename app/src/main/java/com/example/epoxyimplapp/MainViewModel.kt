package com.example.epoxyimplapp

import androidx.core.graphics.PathUtils.flatten
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MainViewModel: ViewModel() {
    val featuredProducts = liveData(Dispatchers.IO) {
        emit(resultOf {
            val products = EpoxyImplApp.appRepository.fetchData().flatMap { flatten(it) }
            products.shuffled().take(20)
        })
    }
}