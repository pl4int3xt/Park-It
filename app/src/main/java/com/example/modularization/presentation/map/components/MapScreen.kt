package com.example.modularization.presentation.map.components

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.modularization.presentation.map.MapEvent
import com.example.modularization.presentation.map.MapScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
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
    val locationPermissionState =  rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

//    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

//    DisposableEffect(key1 = lifecycleOwner, effect = {
//        val observer = LifecycleEventObserver{ _, event ->
//            if (event == Lifecycle.Event.ON_START){
//                locationPermissionState.launchPermissionRequest()
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    })

    val mapUiSettings = remember{ MapUiSettings(zoomControlsEnabled = false) }

    Scaffold(
        topBar = {
            MainTopAppBar(
                navigationIcon = Icons.Default.Menu,
                onClickNavigation = { /*TODO*/ }
            ) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                locationPermissionState.launchPermissionRequest()
                if (locationPermissionState.status.isGranted){
                    Toast.makeText(
                        context,
                        "Granted",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (locationPermissionState.status.shouldShowRationale){
                    locationPermissionState.launchPermissionRequest()
                } else {
                    Toast.makeText(
                        context,
                        "Denied completely",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Icon(imageVector = Icons.Default.MyLocation,
                    contentDescription = "my location")
            }
        }
    ) {
        GoogleMap(
            properties = viewModel.state.properties,
            uiSettings = mapUiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.OnMapLongCLick(it))
            }
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