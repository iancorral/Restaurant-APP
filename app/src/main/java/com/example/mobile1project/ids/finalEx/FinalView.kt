package com.example.mobile1project.ids.exfinal.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobile1project.ids.exfinal.viewmodels.RestaurantViewModel
import com.example.mobile1project.navigation.ScreenNavigation

@Composable
fun FinalView(navController: NavController) {
    val viewModel: RestaurantViewModel = viewModel() // Obtén el ViewModel

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                navController.navigate(ScreenNavigation.RestaurantList.route)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Cargar Restaurantes")
        }
    }
}