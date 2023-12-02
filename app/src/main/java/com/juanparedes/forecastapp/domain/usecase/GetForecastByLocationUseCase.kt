package com.juanparedes.forecastapp.domain.usecase

import com.juanparedes.forecastapp.DEFAULT_FORECAST_DAYS
import com.juanparedes.forecastapp.domain.repository.ForecastRepository

class GetForecastByLocationUseCase(
    private val repository: ForecastRepository
) {

    fun execute(location: String, days: Int = DEFAULT_FORECAST_DAYS) =
        repository.getForecastForLocation(location, days)
}