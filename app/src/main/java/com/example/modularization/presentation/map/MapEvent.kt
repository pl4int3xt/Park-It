package com.example.modularization.presentation.map

import com.example.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object OnProceedCLick: MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()
    data class OnTitleChanged(val title: String): MapEvent()
}