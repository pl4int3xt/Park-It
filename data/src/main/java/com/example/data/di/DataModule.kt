package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.local.ParkingSpotDatabase
import com.example.data.local.ParkingSpotRepositoryImpl
import com.example.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase{
        return Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "parking_spot.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideParkingSpotRepository(parkingSpotDatabase: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(parkingSpotDatabase.dao)
    }
}