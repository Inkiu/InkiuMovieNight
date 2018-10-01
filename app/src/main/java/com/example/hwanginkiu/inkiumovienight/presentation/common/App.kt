package com.example.hwanginkiu.inkiumovienight.presentation.common

import android.app.Application
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.di.DaggerMainComponent
import com.example.hwanginkiu.inkiumovienight.presentation.di.MainComponent
import com.example.hwanginkiu.inkiumovienight.presentation.di.details.MovieDetailModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.details.MovieDetailSubComponent
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.AppModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.DataModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.NetworkModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularMoviesModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularSubComponent

class App: Application() {

    lateinit var mainComponent: MainComponent
    private var popularMoviesComponent: PopularSubComponent? = null
    private var detailMovieComponent: MovieDetailSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
                .appModule(AppModule(applicationContext))
                .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
                .dataModule(DataModule())
                .build()
    }

    fun createPopularComponent(): PopularSubComponent {
        popularMoviesComponent = mainComponent.plus(PopularMoviesModule())
        return popularMoviesComponent!!
    }

    fun releasePopularComponent() {
        popularMoviesComponent = null
    }

    fun createDetailComponent(): MovieDetailSubComponent {
        detailMovieComponent = mainComponent.plus(MovieDetailModule())
        return detailMovieComponent!!
    }
}