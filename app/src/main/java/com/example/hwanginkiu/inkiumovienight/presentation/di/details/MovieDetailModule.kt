package com.example.hwanginkiu.inkiumovienight.presentation.di.details

import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.usecases.CheckFavorite
import com.example.hwanginkiu.inkiumovienight.domain.usecases.GetMovieDetails
import com.example.hwanginkiu.inkiumovienight.domain.usecases.RemoveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.domain.usecases.SaveFavoriteMovie
import com.example.hwanginkiu.inkiumovienight.presentation.MovieEntityMovieMapper
import com.example.hwanginkiu.inkiumovienight.presentation.common.ASyncTransformer
import com.example.hwanginkiu.inkiumovienight.presentation.details.MovieDetailVMFactory
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    fun provideRemoveFavoriteMovie(moviesCache: MoviesCache): RemoveFavoriteMovie {
        return RemoveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideCheckFavoriteMovie(moviesCache: MoviesCache): CheckFavorite {
        return CheckFavorite(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideSaveFavoriteMovie(moviesCache: MoviesCache): SaveFavoriteMovie {
        return SaveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideMovieDetail(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(getMovieDetails: GetMovieDetails,
                                     saveFavoriteMovie: SaveFavoriteMovie,
                                     removeFavoriteMovie: RemoveFavoriteMovie,
                                     checkFavoriteStatus: CheckFavorite,
                                     mapper: MovieEntityMovieMapper): MovieDetailVMFactory {
        return MovieDetailVMFactory(getMovieDetails, saveFavoriteMovie, removeFavoriteMovie, checkFavoriteStatus, mapper)
    }


}