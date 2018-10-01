package com.example.hwanginkiu.inkiumovienight.presentation.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.usecases.CheckFavorite
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetMovieDetails
import com.example.hwanginkiu.inkiumovienight.domain.usecases.RemoveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.domain.usecases.SaveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie

class MovieDetailVMFactory(
        private val getMovieDetails: GetMovieDetails,
        private val saveFavoriteMovie: SaveFavoriteMovie,
        private val removeFavoriteMovie: RemoveFavoriteMovie,
        private val checkFavorite: CheckFavorite,
        private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    var movieId = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(
                getMovieDetails,
                saveFavoriteMovie,
                removeFavoriteMovie,
                checkFavorite,
                mapper,
                movieId) as T
    }
}
