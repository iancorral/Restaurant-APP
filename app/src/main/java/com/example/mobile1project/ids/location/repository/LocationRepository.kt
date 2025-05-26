package com.example.mobile1project.ids.location.repository

import com.example.mobile1project.ids.location.models.Location
import com.example.mobile1project.ids.location.network.LocationApiService

class LocationRepository(private val apiService: LocationApiService) {
    suspend fun fetchLocations(): List<Location> {
        return apiService.getLocations()
    }
}
