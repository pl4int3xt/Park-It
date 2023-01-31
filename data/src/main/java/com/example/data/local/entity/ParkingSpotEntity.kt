package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.ParkingSpot

@Entity
data class ParkingSpotEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot{
    return ParkingSpot(
        lat, lng, id
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity{
    return ParkingSpotEntity(
        lat, lng, id
    )
}