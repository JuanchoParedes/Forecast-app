package com.juanparedes.forecastapp

import android.app.Application
import com.juanparedes.forecastapp.di.ApplicationComponent
import com.juanparedes.forecastapp.di.ApplicationModule
import com.juanparedes.forecastapp.di.ComponentProvider
import com.juanparedes.forecastapp.di.DaggerApplicationComponent

class ForecastApplication: Application(), ComponentProvider {

    private lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        component.inject(this)
    }

    override fun getComponent(): ApplicationComponent = component
}