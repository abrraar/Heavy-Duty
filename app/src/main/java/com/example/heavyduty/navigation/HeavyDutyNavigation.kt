package com.example.heavyduty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun HeavyDutyNavigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavigationScreenNames.AppScreen.route )
    {
        bottomBarNavigation()
    }
}