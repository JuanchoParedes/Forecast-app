package com.juanparedes.forecastapp.domain.usecase

import com.juanparedes.forecastapp.domain.model.Location
import com.juanparedes.forecastapp.domain.repository.LocationRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetPossibleLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    fun execute(location: String): Flowable<List<Location>> =
        repository.getPossibleLocations(location)
        
}