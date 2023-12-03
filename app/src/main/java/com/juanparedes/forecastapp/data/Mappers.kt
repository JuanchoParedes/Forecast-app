package com.juanparedes.forecastapp.data

import com.juanparedes.forecastapp.data.dto.CurrentWeatherDto
import com.juanparedes.forecastapp.data.dto.DayDto
import com.juanparedes.forecastapp.data.dto.ForecastDayDto
import com.juanparedes.forecastapp.data.dto.ForecastDto
import com.juanparedes.forecastapp.data.dto.ForecastLocationDto
import com.juanparedes.forecastapp.data.dto.LocationDto
import com.juanparedes.forecastapp.data.dto.WeatherConditionDto
import com.juanparedes.forecastapp.domain.model.CurrentWeather
import com.juanparedes.forecastapp.domain.model.Day
import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.domain.model.ForecastDay
import com.juanparedes.forecastapp.domain.model.ForecastLocation
import com.juanparedes.forecastapp.domain.model.Location
import com.juanparedes.forecastapp.domain.model.WeatherCondition

fun LocationDto.mapToDomain(): Location =
    Location(id, name, region, country, latitude, longitude, url)

fun List<LocationDto>.mapToDomain(): List<Location> = this.map { it.mapToDomain() }

fun ForecastDto.mapToDomain() =
    Forecast(
        forecastLocation.mapToDomain(),
        currentWeather.mapToDomain(),
        forecast.forecastDays.mapForecastListToDomain()
    )

fun ForecastLocationDto.mapToDomain(): ForecastLocation =
    ForecastLocation(
        name, region, country, latitude, longitude, timeZoneId, localTimeEpoch, localTime
    )

fun WeatherConditionDto.mapToDomain() = WeatherCondition(text, icon, code)

fun CurrentWeatherDto.mapToDomain() =
    CurrentWeather(temperatureInCelsius, weatherCondition.mapToDomain())

fun DayDto.mapToDomain() = Day(
    averageDayTemperature,
    weatherCondition.mapToDomain()
)
fun ForecastDayDto.mapToDomain() = ForecastDay(
    day.mapToDomain(),
    date,
)

fun List<ForecastDayDto>.mapForecastListToDomain() = this.map { it.mapToDomain() }