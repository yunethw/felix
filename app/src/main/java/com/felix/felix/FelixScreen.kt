package com.felix.felix

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.felix.felix.model.BookingUiState
import com.felix.felix.ui.BookingScreen
import com.felix.felix.ui.HomeScreen
import com.felix.felix.ui.ServiceDetailsScreen
import com.felix.felix.viewmodel.BookingViewModel
import com.felix.felix.viewmodel.HomeViewModel

enum class FelixScreen(val title: String) {
    Start(title = "Home Screen"),
    ServiceDetail(title = "Service Detail Screen"),
    Book(title = "Booking Screen")
}

@ExperimentalMaterial3Api
@Composable
fun FelixApp(
    homeViewModel: HomeViewModel = viewModel(),
    bookingViewModel : BookingViewModel = viewModel()
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val categoryState by homeViewModel.categoryState.collectAsState()
    val subServiceState by homeViewModel.subServiceState.collectAsState()
    val bookingState by bookingViewModel.bookingState.collectAsState()
    val selectedSubServiceState by bookingViewModel.selectedSubServiceState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = FelixScreen.Start.name,
        modifier = Modifier
    ) {
        composable(route = FelixScreen.Start.name) {
            HomeScreen(
                onServiceCardClick = {subService ->
                    bookingViewModel.selectSubService(subService!!)
                    navController.navigate(FelixScreen.ServiceDetail.name)
                },
                subServiceList = subServiceState.subServiceList,
                onCategoryCardClick = {/*TODO*/},
                categoryList = categoryState.categoryList
            )
        }
        
        composable(route = FelixScreen.ServiceDetail.name) {
            ServiceDetailsScreen(
                selectedSubServiceState = selectedSubServiceState,
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