package com.juanparedes.forecastapp.domain.model


sealed class FetchForecastState {

    object InitialState: FetchForecastState()
    object LoadingState: FetchForecastState()
    object EmptyState: FetchForecastState()
    data class SearchResults(val forecast: Forecast): FetchForecastState()
    object ErrorState: FetchForecastState()

}