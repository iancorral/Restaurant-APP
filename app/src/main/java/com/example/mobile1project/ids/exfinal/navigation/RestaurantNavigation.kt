package com.example.mobile1project.ids.exfinal.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile1project.ids.exfinal.viewmodels.RestaurantViewModel
import com.example.mobile1project.ids.exfinal.views.RestaurantDetailView
import com.example.mobile1project.ids.exfinal.views.RestaurantListView


sealed class Screen(val route: String) {
    object List : Screen("restaurant_list")
    object Detail : Screen("restaurant_detail")
}

@Composable
fun RestaurantNavGraph(
    viewModel: RestaurantViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            RestaurantListView(
                viewModel = viewModel,
                onRestaurantClick = { restaurant ->
                    viewModel.selectRestaurant(restaurant)
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(Screen.Detail.route) {
            val restaurantState = viewModel.selectedRestaurant.collectAsState()
            val restaurant = restaurantState.value

            if (restaurant != null) {
                RestaurantDetailView (
                    restaurant = restaurant,
                    onBackClick = { navController.popBackStack() }
                )
            } else {
                Text("Restaurante no encontrado")
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }
        }
    }
}