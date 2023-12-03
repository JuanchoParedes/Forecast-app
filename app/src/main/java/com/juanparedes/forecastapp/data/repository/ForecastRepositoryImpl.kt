package com.juanparedes.forecastapp.data.repository

import com.juanparedes.forecastapp.data.api.ForecastAppApiClient
import com.juanparedes.forecastapp.data.mapToDomain
import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.domain.repository.ForecastRepository
import io.reactivex.rxjava3.core.Single

class ForecastRepositoryImpl(
    private val client: ForecastAppApiClient
) : ForecastRepository {

    override fun getForecastForLocation(location: String, days: Int): Single<Forecast> =
        client.getForecastForLocation(location, days)
            .map { it.mapToDomain() }
}