package com.example.hwanginkiu.inkiumovienight.presentation.di.modules

import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailActivity
import com.example.hwanginkiu.inkiumovienight.presentation.di.details.DetailScope
import com.example.hwanginkiu.inkiumovienight.presentation.di.details.MovieDetailModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularMoviesModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.popular.PopularScope
import com.example.hwanginkiu.inkiumovienight.presentation.populars.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityFragmentBuilder {

    @PopularScope
    @ContributesAndroidInjector(modules = [PopularMoviesModule::class])
    abstract fun bindPopularFragment(): PopularMoviesFragment

    @DetailScope
    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    abstract fun bindMovieDetailActivity(): MovieDetailActivity
}