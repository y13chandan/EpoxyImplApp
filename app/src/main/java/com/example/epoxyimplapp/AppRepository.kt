package com.example.epoxyimplapp

class AppRepository(private val service: ApiService) {

    suspend fun fetchData(): List<ProductCategory> {
        service.menu().menus?.let {
            return it
        }
        return emptyList()
    }

}