package com.example.mobile1project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavigation(val route: String, val label: String, val icon: ImageVector) {
    object Ids : ScreenNavigation("IdsRoute", "Inicio", Icons.Default.Home)
    object FirstPartial : ScreenNavigation("FirstPartialRoute", "Parcial 1", Icons.Default.Folder)
    object SecondPartial : ScreenNavigation("SecondPartialRoute", "Parcial 2", Icons.Default.Folder)
    object ThirdPartial : ScreenNavigation("ThirdPartialRoute", "Parcial 3", Icons.Default.Folder)

    // Estas no van en la barra, pero sí en el navController
    object Imc : ScreenNavigation("ImcRoute", "IMC", Icons.Default.Favorite)
    object Sum : ScreenNavigation("SumRoute", "Suma", Icons.Default.Add)
    object Temp : ScreenNavigation("TempRoute", "Temperatura", Icons.Default.Thermostat)
}
