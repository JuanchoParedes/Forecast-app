package com.juanparedes.forecastapp.domain.model

class Forecast(
    val forecastLocation: ForecastLocation,
    val currentWeather: CurrentWeather,
    val forecastDays: List<ForecastDay>
)

data class ForecastLocation(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timeZoneId: String,
    val localTimeEpoch: Long,
    val localTime: String
)

data class CurrentWeather(
    val temperatureInCelsius: Double,
    val weatherCondition: WeatherCondition,
)

data class WeatherCondition(
    val text: String,
    val icon: String,
    val code: Int,
)

data class Day(
    val averageDayTemperature: Double,
    val weatherCondition: WeatherCondition,
)

data class ForecastDay(
    val day: Day,
    val date: String,
    val averageTemperatureInCelsius: Double = day.averageDayTemperature
)