package com.juanparedes.forecastapp.di

import com.juanparedes.forecastapp.domain.repository.ForecastRepository
import com.juanparedes.forecastapp.domain.repository.LocationRepository
import com.juanparedes.forecastapp.domain.usecase.GetForecastByLocationUseCase
import com.juanparedes.forecastapp.domain.usecase.GetPossibleLocationsUseCase
import com.juanparedes.forecastapp.view.ForecastViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class PresentationModule {

    @Singleton
    @Provides
    fun provideGetForecastByLocationUseCase(repository: ForecastRepository): GetForecastByLocationUseCase =
        GetForecastByLocationUseCase(repository)

    @Singleton
    @Provides
    fun provideGetPossibleLocationsUseCase(repository: LocationRepository): GetPossibleLocationsUseCase =
        GetPossibleLocationsUseCase(repository)

    @Singleton
    @Provides
    fun provideForecastViewModelFactory(
        locationUseCase: GetPossibleLocationsUseCase,
        forecastUseCase: GetForecastByLocationUseCase
    ): ForecastViewModelFactory = ForecastViewModelFactory(
        locationUseCase, forecastUseCase
    )
}