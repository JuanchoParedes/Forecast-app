package com.juanparedes.forecastapp.data.api

import com.juanparedes.forecastapp.BuildConfig


class ForecastAppApiClient(
    private val api: ForecastAppApi
) {

    fun getPossibleLocations(locationName: String) =
        api.getPossibleLocations(
            key = BuildConfig.API_KEY,
            locationName = locationName
        )

    fun getForecastForLocation(locationName: String, days: Int) =
        api.getForecastForLocation(
            key = BuildConfig.API_KEY,
            locationName = locationName,
            days = days
        )
}