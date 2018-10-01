package com.example.hwanginkiu.inkiumovienight.presentation.populars

import android.arch.lifecycle.MutableLiveData
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetPopularMovies
import com.example.hwanginkiu.inkiumovienight.presentation.common.BaseViewModel
import com.example.hwanginkiu.inkiumovienight.presentation.common.SingleLiveEvent
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie
import io.reactivex.rxkotlin.subscribeBy

class PopularMoviesViewModel(
        private val getPopularMovies: GetPopularMovies,
        private val movieMapper: Mapper<MovieEntity, Movie>
) :  BaseViewModel() {

    var viewState: MutableLiveData<PopularMoviesViewState> = MutableLiveData()
    var errorState: MutableLiveData<Throwable?> = MutableLiveData()

    init {
        viewState.value = PopularMoviesViewState()
    }

    fun getPopularMovies() {
        getPopularMovies.observable()
                .flatMap { movieMapper.observable(it) }
                .subscribeBy(
                        onNext = { movies ->
                            val newState = viewState.value?.copy(showLoading = false, movies = movies)
                            viewState.value = newState
                            errorState.value = null
                        },
                        onError = {
                            viewState.value = viewState.value?.copy(showLoading = false)
                            errorState.value = it
                        }

                ).composite()
    }

}