package com.example.data.local

import com.example.data.local.entity.ParkingSpotEntity
import com.example.domain.model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(
        lat, lng, id
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        lat, lng, id
    )
}