package com.juanparedes.forecastapp

import com.juanparedes.forecastapp.data.dto.DayDto
import com.juanparedes.forecastapp.data.dto.ForecastDayDto
import com.juanparedes.forecastapp.data.dto.LocationDto
import com.juanparedes.forecastapp.data.dto.WeatherConditionDto
import com.juanparedes.forecastapp.data.mapToDomain
import com.juanparedes.forecastapp.domain.model.ForecastDay
import com.juanparedes.forecastapp.domain.model.Location
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class MappersTest {


    @Test
    fun `test Location_mapToDomain`() {
        val locationDto = LocationDto(
            BigDecimal(1),
            name = "Test Location",
            region = "Test Region",
            country = "Test Country",
            latitude = 10.0,
            longitude = 20.0,
            url = "https://example.com"
        )

        val mappedLocation = locationDto.mapToDomain()

        assertEquals(BigDecimal(1), mappedLocation.id)
        assertEquals("Test Location", mappedLocation.name)
        assertEquals("Test Region", mappedLocation.region)
        assertEquals("Test Country", mappedLocation.country)
        assertEquals(10.0, mappedLocation.latitude, 0.0)
        assertEquals(20.0, mappedLocation.longitude, 0.0)
        assertEquals("https://example.com", mappedLocation.url)
    }

    @Test
    fun `test LocationList_MapToDomain`() {
        val locationDtoList = listOf(
            LocationDto(
                BigDecimal.valueOf(1), "Name1", "Region1", "Country1", 10.0, 20.0, "URL1"
            ),
        )
        val locationList: List<Location> = locationDtoList.mapToDomain()

        // Verify the mapping
        assertEquals(locationDtoList.size, locationList.size)
        assertEquals(locationDtoList[0].id, locationList[0].id)
        assertEquals(locationDtoList[0].name, locationList[0].name)
        assertEquals(locationDtoList[0].region, locationList[0].region)
        assertEquals(locationDtoList[0].country, locationList[0].country)
        assertEquals(locationDtoList[0].latitude, locationList[0].latitude, 0.000)
        assertEquals(locationDtoList[0].longitude, locationList[0].longitude, 0.000)
        assertEquals(locationDtoList[0].url, locationList[0].url)
    }

    @Test
    fun `test forecastDayDto_MapToDomain`() {
        // Mock data
        val forecastDayDto = ForecastDayDto(
            date = "2023-01-15",
            day = DayDto(
                averageDayTemperature = 25.5,
                weatherCondition = WeatherConditionDto(
                    text = "Sunny",
                    icon = "01d",
                    code = 800
                )
            )
        )

        val forecastDay: ForecastDay = forecastDayDto.mapToDomain()

        // Verify the mapping
        assertEquals(forecastDayDto.date, forecastDay.date)
        assertEquals(
            forecastDayDto.day.averageDayTemperature.toInt(),
            forecastDay.averageTemperatureInCelsius
        )
        assertEquals(
            forecastDayDto.day.weatherCondition.text,
            forecastDay.day.weatherCondition.text
        )
        assertEquals(
            forecastDayDto.day.weatherCondition.icon,
            forecastDay.day.weatherCondition.icon
        )
        assertEquals(
            forecastDayDto.day.weatherCondition.code,
            forecastDay.day.weatherCondition.code
        )
    }

}