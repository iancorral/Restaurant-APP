package com.example.mobile1project.ids.exfinal.maps

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun IntegratedMap(
    latitude: String,
    longitude: String,
    modifier: Modifier = Modifier,
    zoom: Float = 15f,
    markerTitle: String = "Ubicación"
) {
    val context = LocalContext.current
    val tag = "IntegratedMap"

    val latLng = remember(latitude, longitude) {
        try {
            val lat = latitude.toDouble()
            val lng = longitude.toDouble()
            if (lat !in -90.0..90.0 || lng !in -180.0..180.0) {
                throw IllegalArgumentException("Coordenadas fuera de rango")
            }
            LatLng(lat, lng).also {
                Log.d(tag, "Coordenadas válidas: $it")
            }
        } catch (e: Exception) {
            Log.e(tag, "Error en coordenadas: Lat=$latitude, Lng=$longitude", e)
            LatLng(19.4326, -99.1332) // CDMX por defecto
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, zoom)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 20f,
                minZoomPreference = 10f,
                isMyLocationEnabled = false
            )
        )
    }

    GoogleMap(
        modifier = modifier
            .background(Color.LightGray),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        Marker(
            state = MarkerState(position = latLng),
            title = markerTitle,
            snippet = "Ubicación del restaurante"
        )
    }

    LaunchedEffect(latLng) {
        Log.d(tag, "Mapa centrado en: $latLng")
    }
}


