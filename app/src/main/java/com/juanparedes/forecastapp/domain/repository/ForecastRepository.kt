package com.juanparedes.forecastapp.domain.repository

import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.domain.model.Location
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface LocationRepository {
    fun getPossibleLocations(location: String): Flowable<List<Location>>
}

interface ForecastRepository {
    fun getForecastForLocation(location: String, days: Int): Single<Forecast>
}