package com.zeroday.buupass.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zeroday.buupass.model.Car

@Composable
fun BottomBarMain(
    navController: NavHostController,
    dealsList: List<Car>,
    navControllerInit: NavController,
    img: String
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(dealsList = dealsList, navController = navControllerInit, imag = img)
        }
        composable("settings") {
            EmptyScreen(section = "Settings")
        }
        composable("search") {
            EmptyScreen("Search")
        }
        composable("profile") {
            EmptyScreen("Profile")
        }
    }
}