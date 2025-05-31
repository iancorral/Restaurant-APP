package com.example.mobile1project.ids.exfinal.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.ids.exfinal.models.Restaurant
import com.example.mobile1project.ids.exfinal.network.RestaurantApiService
import com.example.mobile1project.ids.exfinal.repository.RestaurantRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class RestaurantViewModel : ViewModel() {

    private val apiService = RestaurantApiService.create()
    private val repository = RestaurantRepository(apiService)

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _selectedRestaurant = MutableStateFlow<Restaurant?>(null)
    val selectedRestaurant: StateFlow<Restaurant?> = _selectedRestaurant.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                _restaurants.value = repository.fetchRestaurants()
            } catch (e: UnknownHostException) {
                _errorMessage.value = "Sin conexión a Internet"
            } catch (e: HttpException) {
                _errorMessage.value = when (e.code()) {
                    404 -> "Error 404: Recurso no encontrado"
                    500 -> "Error 500: Error del servidor"
                    else -> "Error de red: ${e.code()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Error de entrada/salida: ${e.message}"
            } catch (e: Exception) {
                _errorMessage.value = "Error inesperado: ${e.localizedMessage}"
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun refreshRestaurants() {
        loadRestaurants()
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun selectRestaurant(restaurant: Restaurant) {
        _selectedRestaurant.value = restaurant
    }

    fun clearSelectedRestaurant() {
        _selectedRestaurant.value = null
    }
}