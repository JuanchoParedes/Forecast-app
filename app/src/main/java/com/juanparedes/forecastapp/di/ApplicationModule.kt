package com.juanparedes.forecastapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application {
        return application
    }
}