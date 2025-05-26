package com.example.mobile1project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mobile1project.ids.IdsView
import com.example.mobile1project.ids.imc.views.IMCScreen
import com.example.mobile1project.firstpartial.FirstPartialView
import com.example.mobile1project.ids.examen.views.StudentsListView
import com.example.mobile1project.ids.sum.views.SumView
import com.example.mobile1project.ids.temp.views.TempScreen
import com.example.mobile1project.secondpartial.SecondPartialView
import com.example.mobile1project.thirdpartial.ThirdPartialView
import com.example.mobile1project.ids.student.views.StudentListView
import com.example.mobile1project.ids.student.viewmodels.StudentViewModel
import com.example.mobile1project.ids.location.views.LocationListScreen

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon!!, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.Imc.route) { IMCScreen() }
            composable(ScreenNavigation.Sum.route) { SumView() }
            composable(ScreenNavigation.Temp.route) { TempScreen() }
            composable(ScreenNavigation.StudentList.route) { StudentListView(viewModel = StudentViewModel()) }
            composable(ScreenNavigation.LocationList.route) { LocationListScreen() }
            composable(ScreenNavigation.ExamenList.route) { StudentsListView()}
        }
    }
}
