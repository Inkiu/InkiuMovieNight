package com.example.hwanginkiu.inkiumovienight.presentation.common

import android.app.Application
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_API_KEY
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_BASE_URL
import com.example.hwanginkiu.inkiumovienight.presentation.koin.detailModule
import com.example.hwanginkiu.inkiumovienight.presentation.koin.globalAppModules
import com.example.hwanginkiu.inkiumovienight.presentation.koin.popularModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
                globalAppModules + popularModule + detailModule,
                extraProperties = mapOf(
                        MOVIE_BASE_URL to getString(R.string.api_base_url),
                        MOVIE_API_KEY to getString(R.string.api_key)
                ))
    }
}