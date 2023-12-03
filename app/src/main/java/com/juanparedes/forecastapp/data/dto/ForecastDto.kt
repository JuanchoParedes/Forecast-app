package com.juanparedes.forecastapp.data.dto

import com.google.gson.annotations.SerializedName
import com.juanparedes.forecastapp.EMPTY_STRING

class ForecastDto(
    @SerializedName("location")
    val forecastLocation: ForecastLocationDto = ForecastLocationDto(),
    @SerializedName("current")
    val currentWeather: CurrentWeatherDto = CurrentWeatherDto(),
    @SerializedName("forecast")
    val forecast: ForecastDaysListDto = ForecastDaysListDto()
)

data class ForecastLocationDto(
    val name: String = EMPTY_STRING,
    val region: String = EMPTY_STRING,
    val country: String = EMPTY_STRING,
    @SerializedName("lat")
    val latitude: Double = 0.0,
    @SerializedName("lon")
    val longitude: Double = 0.0,
    @SerializedName("tz_id")
    val timeZoneId: String = EMPTY_STRING,
    @SerializedName("localtime_epoch")
    val localTimeEpoch: Long = 0,
    @SerializedName("localtime")
    val localTime: String = EMPTY_STRING
)

data class CurrentWeatherDto(
    @SerializedName("temp_c")
    val temperatureInCelsius: Double = 0.0,
    @SerializedName("condition")
    val weatherCondition: WeatherConditionDto = WeatherConditionDto(),
)

data class WeatherConditionDto(
    val text: String = EMPTY_STRING,
    val icon: String = EMPTY_STRING,
    val code: Int = -1,
)

data class DayDto(
    @SerializedName("avgtemp_c")
    val averageDayTemperature: Double = 0.0,
    @SerializedName("condition")
    val weatherCondition: WeatherConditionDto = WeatherConditionDto(),
)

data class ForecastDaysListDto(
    @SerializedName("forecastday")
    val forecastDays: List<ForecastDayDto> = emptyList(),
)

data class ForecastDayDto(
    val date: String = EMPTY_STRING,
    val day: DayDto = DayDto(),
)