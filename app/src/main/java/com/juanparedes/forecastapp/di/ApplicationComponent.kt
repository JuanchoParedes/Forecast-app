package com.juanparedes.forecastapp.di

import com.juanparedes.forecastapp.ForecastApplication
import com.juanparedes.forecastapp.view.ForecastFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: ForecastApplication)

    fun inject(fragment: ForecastFragment)
}