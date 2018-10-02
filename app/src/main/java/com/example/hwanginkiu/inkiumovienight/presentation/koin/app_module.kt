package com.example.hwanginkiu.inkiumovienight.presentation.koin

import android.arch.persistence.room.Room
import android.content.Context
import com.example.hwanginkiu.inkiumovienight.data.api.Api
import com.example.hwanginkiu.inkiumovienight.data.db.FavoriteMovieDBCache
import com.example.hwanginkiu.inkiumovienight.data.db.MoviesDatabase
import com.example.hwanginkiu.inkiumovienight.data.entities.MovieData
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieDataMovieEntityMapper
import com.example.hwanginkiu.inkiumovienight.data.mappers.MovieEntityMovieDataMapper
import com.example.hwanginkiu.inkiumovienight.data.repositries.MoviesRepositoryImpl
import com.example.hwanginkiu.inkiumovienight.data.repositries.RemoteMoviesDataStore
import com.example.hwanginkiu.inkiumovienight.domain.MoviesCache
import com.example.hwanginkiu.inkiumovienight.domain.MoviesRepository
import com.example.hwanginkiu.inkiumovienight.domain.common.Mapper
import com.example.hwanginkiu.inkiumovienight.domain.entities.MovieEntity
import com.example.hwanginkiu.inkiumovienight.presentation.MovieEntityMovieMapper
import com.example.hwanginkiu.inkiumovienight.presentation.common.ImageLoader
import com.example.hwanginkiu.inkiumovienight.presentation.common.PicassoImageLoader
import com.example.hwanginkiu.inkiumovienight.presentation.entities.Movie
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_API_KEY
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_BASE_URL
import com.example.hwanginkiu.inkiumovienight.presentation.koin.MapperSign.DATA_ENTITY
import com.example.hwanginkiu.inkiumovienight.presentation.koin.MapperSign.ENTITY_DATA
import com.example.hwanginkiu.inkiumovienight.presentation.koin.MapperSign.ENTITY_MOVIE
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { PicassoImageLoader(Picasso.with(get())) as ImageLoader }

    single {
        createApi(
                createRetrofit(getProperty(MOVIE_BASE_URL), createInterceptors(getProperty(MOVIE_API_KEY)))
        )
    }

    single { createMovieDatabase(get()) }
    single { createMovieRepository(get())}
    single { createFavoriteMovieCache(get(), get(ENTITY_DATA), get(DATA_ENTITY)) }

    // mappers
    single(ENTITY_DATA) { MovieEntityMovieDataMapper() as Mapper<MovieEntity, MovieData> }
    single(DATA_ENTITY) { MovieDataMovieEntityMapper() as Mapper<MovieData, MovieEntity> }
    single(ENTITY_MOVIE) { MovieEntityMovieMapper() as Mapper<MovieEntity, Movie> }
}

private fun createApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}

private fun createRetrofit(baseUrl: String, interceptor: ArrayList<Interceptor>): Retrofit {
    val clientBuilder = OkHttpClient.Builder()
    if (!interceptor.isEmpty()) {
        interceptor.forEach { clientBuilder.addInterceptor(it) }
    }

    return Retrofit.Builder()
            .client(clientBuilder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
}

private fun createInterceptors(apiKey: String): ArrayList<Interceptor> {
    val interceptor = arrayListOf<Interceptor>()
    val keyInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    interceptor.add(keyInterceptor)
    interceptor.add(logger)

    return interceptor
}

private fun createMovieDatabase(context: Context): MoviesDatabase {
    return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "movies_db"
    ).build()
}

private fun createMovieRepository(api: Api): MoviesRepository {
    return MoviesRepositoryImpl(RemoteMoviesDataStore(api))
}

private fun createFavoriteMovieCache(moviesDatabase: MoviesDatabase,
                                     entityMovieDataMapper: Mapper<MovieEntity, MovieData>,
                                     movieDataMovieEntityMapper: Mapper<MovieData, MovieEntity>): MoviesCache {
    return FavoriteMovieDBCache(moviesDatabase, entityMovieDataMapper, movieDataMovieEntityMapper)
}

