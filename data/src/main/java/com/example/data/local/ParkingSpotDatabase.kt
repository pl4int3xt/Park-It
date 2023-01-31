package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.entity.ParkingSpotEntity

@Database(
    entities = [ParkingSpotEntity::class],
    version = 1
)
abstract class ParkingSpotDatabase: RoomDatabase() {
    abstract val dao: ParkingSpotDao
}