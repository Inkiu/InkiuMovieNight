package com.example.hwanginkiu.inkiumovienight.presentation.common

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.example.hwanginkiu.inkiumovienight.R
import com.example.hwanginkiu.inkiumovienight.presentation.di.DaggerAppComponent
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.AppModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.DataModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.NetworkModule
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_API_KEY
import com.example.hwanginkiu.inkiumovienight.presentation.koin.KoinProperties.MOVIE_BASE_URL
import com.example.hwanginkiu.inkiumovienight.presentation.koin.appModule
import com.example.hwanginkiu.inkiumovienight.presentation.koin.detailModule
import com.example.hwanginkiu.inkiumovienight.presentation.koin.popularModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import org.koin.android.ext.android.startKoin
import javax.inject.Inject

class App: Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        startKoin(this,
                listOf(appModule, popularModule, detailModule),
                extraProperties = mapOf(
                        MOVIE_BASE_URL to getString(R.string.api_base_url),
                        MOVIE_API_KEY to getString(R.string.api_key)
                ))

//        DaggerAppComponent.builder()
//                .application(this)
//                .appModule(AppModule(applicationContext))
//                .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
//                .dataModule(DataModule())
//                .build()
//                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}