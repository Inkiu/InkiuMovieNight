package com.example.hwanginkiu.inkiumovienight.presentation.di.popular

import com.example.hwanginkiu.inkiumovienight.presentation.populars.PopularMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}