package com.example.domain.model

data class ParkingSpot(
    val title: String,
    val lat: Double,
    val lng: Double,
    val id: Int? = null
)