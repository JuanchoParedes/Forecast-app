package com.juanparedes.forecastapp

import com.juanparedes.forecastapp.domain.model.CurrentWeather
import com.juanparedes.forecastapp.domain.model.Day
import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.domain.model.ForecastDay
import com.juanparedes.forecastapp.domain.model.ForecastLocation
import com.juanparedes.forecastapp.domain.model.Location
import com.juanparedes.forecastapp.domain.model.WeatherCondition
import com.juanparedes.forecastapp.domain.repository.ForecastRepository
import com.juanparedes.forecastapp.domain.repository.LocationRepository
import com.juanparedes.forecastapp.domain.usecase.GetForecastByLocationUseCase
import com.juanparedes.forecastapp.domain.usecase.GetPossibleLocationsUseCase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.math.BigDecimal

class RepositoryTest {

    private val location = "New York"
    private val days = 5

    private val weatherCondition = WeatherCondition(
        text = "Text", icon = "icon", code = 123
    )

    private val forecast = Forecast(
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
        listOf(
            ForecastDay(
                day = Day(
                    averageDayTemperature = 20,
                    weatherCondition = weatherCondition
                ), date = "2023"
            )
        )
    )

    @Before
    fun setUp() {

    }

    @Test
    fun testGetPossibleLocationsUseCaseTest() {
        val mockRepository: LocationRepository = mock(LocationRepository::class.java)

        val getPossibleLocationsUseCase = GetPossibleLocationsUseCase(mockRepository)

        // Mock data

        val locations = listOf(
            Location(BigDecimal(1), "Location1", "Region1", "Country1", 40.7128, -74.0060, "URL1"),
            Location(BigDecimal(2), "Location2", "Region2", "Country2", 34.0522, -118.2437, "URL2")
        )

        `when`(mockRepository.getPossibleLocations(location)).thenReturn(Flowable.just(locations))

        val result = getPossibleLocationsUseCase.execute(location).test()

        result.assertNoErrors()
        result.assertValue { it == locations }
    }

    @Test
    fun testExecuteWithError() {
        val mockRepository: LocationRepository = mock(LocationRepository::class.java)

        val getPossibleLocationsUseCase = GetPossibleLocationsUseCase(mockRepository)

        val error = RuntimeException("Repository error")

        `when`(mockRepository.getPossibleLocations(location))
            .thenReturn(Flowable.error(error))

        try {
            getPossibleLocationsUseCase.execute(location).blockingFirst()
            fail("Expected an exception but got none.")
        } catch (e: Throwable) {
            assertEquals("Repository error", e.message)
        }
    }

    @Test
    fun testGetForecastByLocationUseCase() {
        val mockRepository: ForecastRepository = mock(ForecastRepository::class.java)

        val getForecastByLocationUseCase = GetForecastByLocationUseCase(mockRepository)

        // Mock the repository.getForecastForLocation() method
        `when`(mockRepository.getForecastForLocation(location, days)).thenReturn(
            Single.just(
                forecast
            )
        )

        val result = getForecastByLocationUseCase.execute(location, days).test()

        result.assertNoErrors()
        result.assertValue { it == forecast }
    }

    @Test
    fun testGetForecastByLocationUseCaseError() {
        val mockRepository: ForecastRepository = mock(ForecastRepository::class.java)

        val getForecastByLocationUseCase = GetForecastByLocationUseCase(mockRepository)

        val error = Throwable("Repository error")

        `when`(mockRepository.getForecastForLocation(location, days))
            .thenReturn(Single.error<Forecast>(error).subscribeOn(Schedulers.io()))

        try {
            getForecastByLocationUseCase.execute(location, days).blockingGet()
            fail("Expected an exception but got none.")
        } catch (e: Throwable) {
            assertEquals("java.lang.Throwable: Repository error", e.message)
        }
    }
}