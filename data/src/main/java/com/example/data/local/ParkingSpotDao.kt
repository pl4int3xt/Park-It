package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.ParkingSpotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spotEntity: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spotEntity: ParkingSpotEntity)

    @Query("SELECT * FROM parkingspotentity")
    fun getParkingSpots(): Flow<List<ParkingSpotEntity>>
}