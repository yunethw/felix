package com.felix.felix

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.felix.felix.ui.BookingScreen
import com.felix.felix.ui.HomeScreen
import com.felix.felix.ui.ServiceDetailsScreen

enum class FelixScreen(val title: String) {
    Start(title = "Home Screen"),
    ServiceDetail(title = "Service Detail Screen"),
    Book(title = "Booking Screen")
}

@ExperimentalMaterial3Api
@Composable
fun FelixApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = FelixScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = FelixScreen.Start.name) {
            HomeScreen(
                onServiceCardClick = {
                    navController.navigate(FelixScreen.ServiceDetail.name)
                }
            )
        }
        
        composable(route = FelixScreen.ServiceDetail.name) {
            ServiceDetailsScreen(
                serviceTitle = "Air Conditioner Repair",
                servicePrice = "3000",
                description = "Lorem ipsum blah blah shinali",
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onBookButtonClick = {
                    navController.navigate(FelixScreen.Book.name)
                }
            )
        }
        
        composable(route = FelixScreen.Book.name) {
            BookingScreen(
                serviceTitle = "Air Conditioner Repair",
                price = "3000",
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onCancelButtonClick = {
                    navController.popBackStack(FelixScreen.Start.name, inclusive = false)
                },
                onConfirmButtonClick = {}
            )
        }
    }
}