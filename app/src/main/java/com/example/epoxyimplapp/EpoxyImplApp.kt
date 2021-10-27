package com.example.epoxyimplapp

import android.app.Application
import android.content.Context

class EpoxyImplApp : Application() {

    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()
        appRepository = AppRepository(apiService)
    }

    companion object {

        lateinit var appRepository: AppRepository
            private set

        @JvmStatic
        @Volatile
        lateinit var appContext: Context
            private set

    }
}