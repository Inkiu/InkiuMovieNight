package com.example.hwanginkiu.inkiumovienight.presentation.koin

import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetPopularMovies
import com.example.hwanginkiu.inkiumovienight.presentation.common.ASyncTransformer
import com.example.hwanginkiu.inkiumovienight.presentation.koin.MapperSign.ENTITY_MOVIE
import com.example.hwanginkiu.inkiumovienight.presentation.populars.PopularMoviesVMFactory
import org.koin.dsl.module.module

val popularModule = module {
    factory { GetPopularMovies(ASyncTransformer(), get()) }
    single { PopularMoviesVMFactory(get(), get(ENTITY_MOVIE)) }
}