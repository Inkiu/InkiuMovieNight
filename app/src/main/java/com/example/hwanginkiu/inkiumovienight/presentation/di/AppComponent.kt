package com.example.hwanginkiu.inkiumovienight.presentation.di

import android.app.Application
import com.example.hwanginkiu.inkiumovienight.presentation.common.App
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.ActivityFragmentBuilder
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.AppModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.DataModule
import com.example.hwanginkiu.inkiumovienight.presentation.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class, NetworkModule::class, DataModule::class,
    ActivityFragmentBuilder::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun dataModule(dataModule: DataModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}