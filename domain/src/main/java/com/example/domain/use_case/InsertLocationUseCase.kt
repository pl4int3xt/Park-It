package com.example.domain.use_case

import com.example.domain.model.ParkingSpot
import com.example.domain.repository.ParkingSpotRepository
import javax.inject.Inject

class InsertLocationUseCase @Inject constructor(
    private val parkingSpotRepository: ParkingSpotRepository
){
    suspend operator fun invoke(spot: ParkingSpot){
        parkingSpotRepository.insertParkingSpot(spot)
    }
}