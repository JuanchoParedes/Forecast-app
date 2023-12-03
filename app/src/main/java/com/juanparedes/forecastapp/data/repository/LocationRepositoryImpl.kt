package com.juanparedes.forecastapp.data.repository

import com.juanparedes.forecastapp.data.api.ForecastAppApiClient
import com.juanparedes.forecastapp.data.mapToDomain
import com.juanparedes.forecastapp.domain.model.Location
import com.juanparedes.forecastapp.domain.repository.LocationRepository
import io.reactivex.rxjava3.core.Flowable

class LocationRepositoryImpl(
    private val client: ForecastAppApiClient
) : LocationRepository {
    override fun getPossibleLocations(location: String): Flowable<List<Location>> =
        client.getPossibleLocations(location)
            .map { it.mapToDomain() }


}