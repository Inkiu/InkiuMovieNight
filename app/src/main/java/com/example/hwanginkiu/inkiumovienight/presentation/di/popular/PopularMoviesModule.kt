package com.example.hwanginkiu.inkiumovienight.presentation.di.popular

import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetPopularMovies
import com.example.hwanginkiu.inkiumovienight.presentation.MovieEntityMovieMapper
import com.example.hwanginkiu.inkiumovienight.presentation.common.ASyncTransformer
import com.example.hwanginkiu.inkiumovienight.presentation.populars.PopularMoviesVMFactory
import dagger.Module
import dagger.Provides

@PopularScope
@Module
class PopularMoviesModule {

        @Provides
        fun providePopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMovies {
            return GetPopularMovies(ASyncTransformer(), moviesRepository)
        }

        @Provides
        fun providePopularMovieFMFactory(useCase: GetPopularMovies, mapper: MovieEntityMovieMapper): PopularMoviesVMFactory {
            return PopularMoviesVMFactory(useCase, mapper)
    }
}