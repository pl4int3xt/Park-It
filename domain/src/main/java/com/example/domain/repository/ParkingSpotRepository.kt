package com.example.domain.repository

import com.example.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {

    suspend fun insertParkingSpot(spot: ParkingSpot)
    suspend fun deleteParkingSpot(spot: ParkingSpot)
    fun getParkingSpots(): Flow<List<ParkingSpot>>
}