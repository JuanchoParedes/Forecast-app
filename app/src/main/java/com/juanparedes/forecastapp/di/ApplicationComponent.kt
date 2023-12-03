package com.juanparedes.forecastapp.di

import com.juanparedes.forecastapp.ForecastApplication
import com.juanparedes.forecastapp.view.LocationsFragment
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


}