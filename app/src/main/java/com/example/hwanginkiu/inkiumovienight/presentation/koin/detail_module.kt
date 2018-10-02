package com.example.hwanginkiu.inkiumovienight.presentation.koin

import com.example.hwanginkiu.inkiumovienight.domain.usecases.CheckFavorite
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetMovieDetails
import com.example.hwanginkiu.inkiumovienight.domain.usecases.RemoveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.domain.usecases.SaveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.presentation.common.ASyncTransformer
import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailVMFactory
import com.example.hwanginkiu.inkiumovienight.presentation.koin.MapperSign.ENTITY_MOVIE
import com.example.hwanginkiu.inkiumovienight.presentation.koin.SubModules.DETAIL
import org.koin.dsl.module.module

val detailModule = module {
    factory { RemoveFavoriteMovie(ASyncTransformer(), get()) }
    factory { CheckFavorite(ASyncTransformer(), get()) }
    factory { SaveFavoriteMovie(ASyncTransformer(), get()) }
    factory { GetMovieDetails(ASyncTransformer(), get()) }
    scope(DETAIL) { MovieDetailVMFactory(get(), get(), get(), get(), get(ENTITY_MOVIE)) }
}