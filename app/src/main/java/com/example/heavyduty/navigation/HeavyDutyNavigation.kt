package com.example.heavyduty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.heavyduty.MainAppScreen

@Composable
fun HeavyDutyNavigation(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        route = NavigationScreenNames.Main.route,
        startDestination = NavigationScreenNames.AuthenticationRoute.route )
    {
        authNavGraph(navHostController)
        composable(route = NavigationScreenNames.MainScreens.route){
            MainAppScreen()
        }
    }
}