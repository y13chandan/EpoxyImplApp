package com.example.epoxyimplapp.network

import com.example.epoxyimplapp.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("bff/ordering/menu")
    suspend fun menu(): ApiResponse

}