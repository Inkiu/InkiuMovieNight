package com.example.hwanginkiu.inkiumovienight.presentation.koin

import com.example.hwanginkiu.inkiumovienight.domain.usecases.CheckFavorite
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetMovieDetails
import com.example.hwanginkiu.inkiumovienight.domain.usecases.RemoveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.domain.usecases.SaveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.presentation.MovieEntityMovieMapper
import com.example.hwanginkiu.inkiumovienight.presentation.common.ASyncTransformer
import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailVMFactory
import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailViewModel
import com.example.hwanginkiu.inkiumovienight.presentation.koin.SubModules.DETAIL
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val detailModule = module {
    factory { RemoveFavoriteMovie(ASyncTransformer(), get()) }
    factory { CheckFavorite(ASyncTransformer(), get()) }
    factory { SaveFavoriteMovie(ASyncTransformer(), get()) }
    factory { GetMovieDetails(ASyncTransformer(), get()) }
    scope(DETAIL) { MovieDetailVMFactory(get(), get(), get(), get(), MovieEntityMovieMapper()) }
}