package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpotEntity(
    val title: String,
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)