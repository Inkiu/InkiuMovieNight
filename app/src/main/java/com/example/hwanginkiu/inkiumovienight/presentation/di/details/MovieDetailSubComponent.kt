package com.example.hwanginkiu.inkiumovienight.presentation.di.details

import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailActivity
import dagger.Component
import dagger.Subcomponent

@DetailScope
@Subcomponent(modules = [MovieDetailModule::class])
interface MovieDetailSubComponent {
    fun inject(movieDetailActivity: MovieDetailActivity)
}