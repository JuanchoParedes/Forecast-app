package com.juanparedes.forecastapp.data.dto

import com.google.gson.annotations.SerializedName

class ForecastDto(
    @SerializedName("location")
    val forecastLocation: ForecastLocationDto,
    @SerializedName("current")
    val currentWeather: CurrentWeatherDto,
    @SerializedName("forecast")
    val forecast: ForecastDaysListDto
)

data class ForecastLocationDto(
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("tz_id")
    val timeZoneId: String,
    @SerializedName("localtime_epoch")
    val localTimeEpoch: Long,
    @SerializedName("localtime")
    val localTime: String
)

data class CurrentWeatherDto(
    @SerializedName("temp_c")
    val temperatureInCelsius: Double,
    @SerializedName("condition")
    val weatherCondition: WeatherConditionDto,
)

data class WeatherConditionDto(
    val text: String,
    val icon: String,
    val code: Int,
)

data class DayDto(
    @SerializedName("avgtemp_c")
    val averageDayTemperature: Double,
    @SerializedName("condition")
    val weatherCondition: WeatherConditionDto,
)

data class ForecastDaysListDto(
    @SerializedName("forecastday")
    val forecastDays: List<ForecastDayDto>,
)

data class ForecastDayDto(
    val date: String,
    val day: DayDto,
)