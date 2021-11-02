package com.example.epoxyimplapp.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.epoxyimplapp.EpoxyImplApp
import com.example.epoxyimplapp.Failed
import com.example.epoxyimplapp.Success
import com.example.epoxyimplapp.UIState
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
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