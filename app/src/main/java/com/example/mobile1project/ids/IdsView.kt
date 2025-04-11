package com.example.mobile1project.ids

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile1project.navigation.ScreenNavigation

@Composable
fun IdsView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { navController.navigate(ScreenNavigation.Imc.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a IMC")
        }

        Button(
            onClick = { navController.navigate(ScreenNavigation.Sum.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Suma")
        }

        Button(
            onClick = { navController.navigate(ScreenNavigation.Temp.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Temperatura")
        }
    }
}

