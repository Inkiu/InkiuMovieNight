package com.example.hwanginkiu.inkiumovienight.presentation.di.modules

import android.content.Context
import com.example.hwanginkiu.inkiumovienight.presentation.common.ImageLoader
import com.example.hwanginkiu.inkiumovienight.presentation.common.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (context: Context) {

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideImageLoader(context: Context): ImageLoader {
        return PicassoImageLoader(Picasso.with(context))
    }
}