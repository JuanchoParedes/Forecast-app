package com.juanparedes.forecastapp.domain.model

import java.math.BigDecimal

data class Location(
    val id: BigDecimal,
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val url: String
)