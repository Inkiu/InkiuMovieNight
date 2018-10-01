package com.example.hwanginkiu.inkiumovienight.presentation.details

import android.arch.lifecycle.MutableLiveData
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.usecases.CheckFavorite
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetMovieDetails
import com.example.hwanginkiu.inkiumovienight.domain.usecases.RemoveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.domain.usecases.SaveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.presentation.common.BaseViewModel
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith

class MovieDetailViewModel(
        private val getMovieDetails: GetMovieDetails,
        private val saveFavoriteMovie: SaveFavoriteMovie,
        private val removeFavoriteMovie: RemoveFavoriteMovie,
        private val checkFavorite: CheckFavorite,
        private val mapper: Mapper<MovieEntity, Movie>,
        private val movieId: Int
) : BaseViewModel() {

    lateinit var movieEntity: MovieEntity
    var viewState = MutableLiveData<MovieDetailsViewState>()
    var favoriteState = MutableLiveData<Boolean>()
    var errorState = MutableLiveData<Throwable?>()

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
        favoriteState.value = false
    }

    fun getMovieDetails() {
        getMovieDetails.getById(movieId)
                .map { optional ->
                    optional.value?.let {
                        movieEntity = it
                        mapper.mapFrom(it)
                    } ?: throw Throwable("Something went wrong :( ")
                }
                .zipWith(checkFavorite.check(movieId)) { movie: Movie, isFavorite: Boolean ->
                    movie.copy(isFavorite = isFavorite)
                }
                .subscribeBy(
                        onNext = { onMovieDetailReceived(it) },
                        onError = { errorState.value = it; it.printStackTrace() }
                ).composite()
    }

    fun favoriteButtonClicked() {
        checkFavorite.check(movieId)
                .flatMap {
                    if (it) removeFavoriteMovie.remove(movieEntity)
                    else saveFavoriteMovie.save(movieEntity)
                }
                .subscribeBy(
                        onNext = { favoriteState.value = it },
                        onError = { errorState.value = it }
                ).composite()
    }

    private fun onMovieDetailReceived(movie: Movie) {
        val newViewState = viewState.value?.copy(
                isLoading = false,
                title = movie.originalTitle,
                videos = movie.details?.videos ?: emptyList(),
                homepage = movie.details?.homepage ?: "",
                overview = movie.details?.overview ?: "",
                releaseDate = movie.releaseDate,
                votesAverage = movie.voteAverage,
                backdropUrl = movie.backdropPath ?: "",
                genres = movie.details?.genres ?: emptyList())

        viewState.value = newViewState
        favoriteState.value = movie.isFavorite
    }


}