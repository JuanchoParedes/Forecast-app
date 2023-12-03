package com.juanparedes.forecastapp.data.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class LocationDto(
    val id: BigDecimal,
    val name: String,
    val region: String,
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    val url: String
)