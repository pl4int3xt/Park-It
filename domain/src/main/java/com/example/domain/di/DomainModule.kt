package com.example.domain.di

import com.example.domain.repository.ParkingSpotRepository
import com.example.domain.use_case.DeleteLocationUseCase
import com.example.domain.use_case.GetLocationUseCase
import com.example.domain.use_case.InsertLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideDeleteLocationUseCase(getParkingSpotRepository: ParkingSpotRepository): DeleteLocationUseCase{
        return DeleteLocationUseCase(getParkingSpotRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocationUseCase(getParkingSpotRepository: ParkingSpotRepository): GetLocationUseCase{
        return GetLocationUseCase(getParkingSpotRepository)
    }

    @Provides
    @Singleton
    fun provideInsertLocationUseCase(getParkingSpotRepository: ParkingSpotRepository): InsertLocationUseCase {
        return InsertLocationUseCase(getParkingSpotRepository)
    }
}