package com.juanparedes.forecastapp

import com.juanparedes.forecastapp.domain.model.CurrentWeather
import com.juanparedes.forecastapp.domain.model.Day
import com.juanparedes.forecastapp.domain.model.FetchForecastState
import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.domain.model.ForecastDay
import com.juanparedes.forecastapp.domain.model.ForecastLocation
import com.juanparedes.forecastapp.domain.model.WeatherCondition
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewsTest {
    @Test
    fun testFetchForecastState() {
        // Test each state of FetchForecastState

        // InitialState
        val initialState: FetchForecastState = FetchForecastState.InitialState
        assertEquals(FetchForecastState.InitialState, initialState)

        // LoadingState
        val loadingState: FetchForecastState = FetchForecastState.LoadingState
        assertEquals(FetchForecastState.LoadingState, loadingState)

        // EmptyState
        val emptyState: FetchForecastState = FetchForecastState.EmptyState
        assertEquals(FetchForecastState.EmptyState, emptyState)

        // SearchResults
        val weatherCondition = WeatherCondition(
            text = "Text", icon = "icon", code = 123
        )

        val day = Day(
            averageDayTemperature = 20,
            weatherCondition = weatherCondition
        )
        val forecastDay = ForecastDay(
            day = day, date = "2023"
        )
        val forecast = Forecast(
            ForecastLocation(
                name = "City",
                region = "Region",
                country = "Country",
                latitude = 10.0,
                longitude = 10.0,
                timeZoneId = "timezoneId",
                localTimeEpoch = 12321L,
                localTime = "10:00"
            ),
            CurrentWeather(
                temperatureInCelsius = 25,
                weatherCondition = weatherCondition
            ),
            listOf(forecastDay)
        )
        val searchResultsState: FetchForecastState = FetchForecastState.SearchResults(forecast)
        assertEquals(FetchForecastState.SearchResults(forecast), searchResultsState)

        // ErrorState
        val errorState: FetchForecastState = FetchForecastState.ErrorState
        assertEquals(FetchForecastState.ErrorState, errorState)
    }
}