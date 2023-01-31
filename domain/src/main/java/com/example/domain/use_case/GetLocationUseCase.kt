package com.example.domain.use_case

import com.example.domain.model.ParkingSpot
import com.example.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    operator fun invoke(): Flow<List<ParkingSpot>> {
        return parkingSpotRepository.getParkingSpots()
    }
}