package com.example.modularization.presentation.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MapScreenViewModel: ViewModel() {

    var state by mutableStateOf(MapState())
}