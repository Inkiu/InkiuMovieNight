package com.example.hwanginkiu.inkiumovienight.presentation.populars

import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie


data class PopularMoviesViewState(
        val showLoading: Boolean = true,
        val movies: List<Movie> = emptyList()
)