package com.juanparedes.forecastapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.juanparedes.forecastapp.data.dto.LocationDto
import com.juanparedes.forecastapp.data.mapToDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.math.BigDecimal
import java.util.Locale

class UtilsTest {

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormat() {
        val dateString = "2023-01-15 14:30"
        val expectedOutput = "Sunday, January 15, 2023.\nTime is 14:30"
        val result = dateString.convertLocalTimeToPrettyFormat()
        assertEquals(expectedOutput, result)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormatWithInvalidInput() {
        val invalidDateString = "InvalidDate"
        val result = invalidDateString.convertLocalTimeToPrettyFormat()
        assertEquals("", result)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormatWithMinimumDate() {
        val minDateString = "0001-01-01 00:00"
        val expectedOutput = "Saturday, January 1, 1.\nTime is 0:0"
        val result = minDateString.convertLocalTimeToPrettyFormat()
        assertEquals(expectedOutput, result)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormatShort() {
        val dateString = "2023-01-15"
        val expectedOutput = "Sunday"
        val result = dateString.convertLocalTimeToPrettyFormatShort()
        assertEquals(expectedOutput, result)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormatShortWithInvalidInput() {
        val invalidDateString = "InvalidDate"
        val result = invalidDateString.convertLocalTimeToPrettyFormatShort()
        assertEquals("", result)
    }

    @Test
    fun testConvertLocalTimeToPrettyFormatShortWithDifferentDate() {
        val dateString = "2023-05-20"
        val expectedOutput = "Saturday"
        val result = dateString.convertLocalTimeToPrettyFormatShort()
        assertEquals(expectedOutput, result)
    }
}