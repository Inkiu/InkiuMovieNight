package com.example.hwanginkiu.inkiumovienight.presentation.populars

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetPopularMovies
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie

class PopularMoviesVMFactory(
        private val useCase: GetPopularMovies,
        private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase, mapper) as T
    }

}