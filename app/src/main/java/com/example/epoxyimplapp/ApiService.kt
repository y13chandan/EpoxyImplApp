package com.example.epoxyimplapp

import retrofit2.http.GET

interface ApiService {

    @GET("bff/ordering/menu")
    suspend fun menu(): ApiResponse

}