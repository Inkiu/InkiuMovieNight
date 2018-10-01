package com.example.hwanginkiu.inkiumovienight.presentation.di.modules

import com.example.hwanginkiu.inkiumovienight.data.api.Api
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String, private val apiKey: String) {

    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
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

    @Singleton
    @Provides
    fun provideRetrofit(interceptor: ArrayList<Interceptor>): Retrofit {
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

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}