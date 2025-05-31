package com.example.mobile1project.ids.exfinal.repository

import com.example.mobile1project.ids.exfinal.models.Restaurant
import com.example.mobile1project.ids.exfinal.network.RestaurantApiService

class RestaurantRepository(private val apiService: RestaurantApiService) {
    suspend fun fetchRestaurants(): List<Restaurant> {
        return apiService.getRestaurants()
    }
}