package com.juanparedes.forecastapp.data.dto

import com.juanparedes.forecastapp.EMPTY_STRING

data class ErrorDto(
    val code: String = EMPTY_STRING,
    val message: String = EMPTY_STRING
) {
}