package com.example.modularization.presentation.map

import com.example.domain.model.ParkingSpot
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(MapStyle.json)
    ),
    val parkingSpots: List<ParkingSpot> = emptyList()
)