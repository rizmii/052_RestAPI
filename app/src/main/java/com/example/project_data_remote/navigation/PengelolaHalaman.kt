package com.example.project_data_remote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.project_data_remote.ui.home.screen.DestinasiHome
import com.example.project_data_remote.ui.home.screen.HomeScreen
import com.example.project_data_remote.ui.kontak.screen.DestinasiEntry

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = {
                    // Define the action when the detail is clicked
                }
            )
        }

        composable(DestinasiEntry.route) {
            EntryKontakScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
