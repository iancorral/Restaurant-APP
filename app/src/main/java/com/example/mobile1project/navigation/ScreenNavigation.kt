package com.example.mobile1project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNavigation(val route: String, val label: String, val icon: ImageVector? = null) {
    object Ids : ScreenNavigation("IdsRoute", "Inicio", Icons.Default.Home)
    object FirstPartial : ScreenNavigation("FirstPartialRoute", "Parcial 1", Icons.Default.Folder)
    object SecondPartial : ScreenNavigation("SecondPartialRoute", "Parcial 2", Icons.Default.Folder)
    object ThirdPartial : ScreenNavigation("ThirdPartialRoute", "Parcial 3", Icons.Default.Folder)

    // Rutas no visibles en la barra (sin icono)
    object Imc : ScreenNavigation("ImcRoute", "IMC")
    object Sum : ScreenNavigation("SumRoute", "Suma")
    object Temp : ScreenNavigation("TempRoute", "Temperatura")
    object StudentList : ScreenNavigation("StudentListRoute", "Lista de Estudiantes")
    object LocationList : ScreenNavigation("LocationListRoute", "Locations")
    object ExamenList : ScreenNavigation("ExamenListRoute", "Students")
}
