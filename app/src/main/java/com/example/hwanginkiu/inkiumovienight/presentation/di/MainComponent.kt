package com.example.hwanginkiu.inkiumovienight.presentation.di

import com.example.hwanginkiu.inkiumovienight.presentation.di.details.MovieDetailModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.details.MovieDetailSubComponent
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.AppModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.DataModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.NetworkModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularMoviesModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AppModule::class, NetworkModule::class, DataModule::class]
)
interface MainComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
    fun plus(detailModule: MovieDetailModule): MovieDetailSubComponent
}