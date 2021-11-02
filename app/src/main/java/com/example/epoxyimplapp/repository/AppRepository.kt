package com.example.epoxyimplapp.repository

import com.example.epoxyimplapp.ProductCategory
import com.example.epoxyimplapp.network.ApiService

class AppRepository(private val service: ApiService) {

    suspend fun fetchData(): List<ProductCategory> {
        service.menu().menus?.let {
            return it
        }
        return emptyList()
    }

}