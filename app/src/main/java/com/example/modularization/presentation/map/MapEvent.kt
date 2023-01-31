package com.example.modularization.presentation.map

import com.example.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    data class OnMapLongCLick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()
    object OnGetCurrentLocationClicked: MapEvent()
}