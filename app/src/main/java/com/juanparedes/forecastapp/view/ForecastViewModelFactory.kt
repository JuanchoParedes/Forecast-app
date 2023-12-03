package com.juanparedes.forecastapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juanparedes.forecastapp.domain.usecase.GetForecastByLocationUseCase
import com.juanparedes.forecastapp.domain.usecase.GetPossibleLocationsUseCase
import javax.inject.Inject

class ForecastViewModelFactory @Inject constructor(
    private val getPossibleLocationsUseCase: GetPossibleLocationsUseCase,
    private val getForecastByLocationUseCase: GetForecastByLocationUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(
                getPossibleLocationsUseCase,
                getForecastByLocationUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}