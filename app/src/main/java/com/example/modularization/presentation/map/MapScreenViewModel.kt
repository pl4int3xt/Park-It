package com.example.modularization.presentation.map

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ParkingSpot
import com.example.domain.use_case.DeleteLocationUseCase
import com.example.domain.use_case.GetLocationUseCase
import com.example.domain.use_case.InsertLocationUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val insertLocationUseCase: InsertLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val getLocationUseCase: GetLocationUseCase
): ViewModel() {

    var
    var dialogState by mutableStateOf(false)
    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            getLocationUseCase().collectLatest { spots ->
                state = state.copy(parkingSpots = spots)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state = state.copy(
                        lastKnownLocation = task.result,
                        properties = MapProperties(
                            isMyLocationEnabled = true
                        ),
                    )
                    Log.e("--------------------------", "getDeviceLocation: ${state.lastKnownLocation?.latitude}", )
                } else {
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }

    fun onEvent(mapEvent: MapEvent){
        when(mapEvent){
            is MapEvent.OnMapLongCLick -> {
                viewModelScope.launch {
                    insertLocationUseCase(
                        ParkingSpot(
                            lat = mapEvent.latLng.latitude,
                            lng = mapEvent.latLng.longitude
                        )
                    )
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    deleteLocationUseCase(mapEvent.spot)
                }
            }
            is MapEvent.OnGetCurrentLocationClicked -> {

            }
        }
    }
}