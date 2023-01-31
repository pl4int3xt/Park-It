package com.example.modularization.presentation.map.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.modularization.presentation.map.MapEvent
import com.example.modularization.presentation.map.MapScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val locationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val mapUiSettings = remember{ MapUiSettings(zoomControlsEnabled = false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
            }) {
                Icon(imageVector = Icons.Default.MyLocation,
                    contentDescription = "my location")
            }
        }
    ) {

        if (locationPermissionState.status.isGranted) {

        } else {
            Column {
                val textToShow = if (locationPermissionState.status.shouldShowRationale) {
                    // If the user has denied the permission but the rationale can be shown,
                    // then gently explain why the app requires this permission
                    "The camera is important for this app. Please grant the permission."
                } else {
                    // If it's the first time the user lands on this feature, or the user
                    // doesn't want to be asked again for this permission, explain that the
                    // permission is required
                    "Camera permission required for this feature to be available. " +
                            "Please grant the permission"
                }
                Text(textToShow)
                Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }

        GoogleMap(
            properties = viewModel.state.properties,
            uiSettings = mapUiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapLongCLick(it))
            },
        ){
            viewModel.state.parkingSpots.forEach { parkingSpot ->
                Marker(
                    position = LatLng(parkingSpot.lat, parkingSpot.lng),
                    title = "Parking spot (${parkingSpot.lat}, ${parkingSpot.lng})",
                    snippet = "Long CLick to delete",
                    onInfoWindowClick = {
                        viewModel.onEvent(MapEvent.OnInfoWindowLongClick(parkingSpot))
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_ORANGE
                    ),
                )
            }
        }
    }
}