package com.example.epoxyimplapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val featuredProducts: MutableLiveData<UIState> = MutableLiveData()
    val popularDrinks: MutableLiveData<UIState> = MutableLiveData()
    val signatureDrinks: MutableLiveData<UIState> = MutableLiveData()
    val allProducts: MutableLiveData<UIState> = MutableLiveData()
    val categories: MutableLiveData<UIState> = MutableLiveData()


    fun getCategoriesData() {
        viewModelScope.launch {
            try {
                val categoriesData = EpoxyImplApp.appRepository.fetchData()
                categories.value = Success(categoriesData)
            } catch (e: Exception) {
                e.message?.let {
                    categories.value = Failed(it)
                }
            }
        }
    }

}