package com.juanparedes.forecastapp.data.api

import com.juanparedes.forecastapp.data.dto.ForecastDto
import com.juanparedes.forecastapp.data.dto.LocationDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastAppApi {

    @GET("search.json?key")
    fun getPossibleLocations(
        @Query("key") key: String,
        @Query("q") locationName: String
    ): Flowable<List<LocationDto>>

    @GET("forecast.json")
    fun getForecastForLocation(
        @Query("key") key: String,
        @Query("q") locationName: String,
        @Query("days") days: Int
    ): Single<ForecastDto>
}