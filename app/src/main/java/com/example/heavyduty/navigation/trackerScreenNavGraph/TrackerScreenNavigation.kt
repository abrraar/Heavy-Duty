package com.example.heavyduty.navigation.trackerScreenNavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.heavyduty.navigation.NavigationScreenNames


@Composable
fun TrackerScreenNavigation(navHostController: NavHostController){

    NavHost(
        route = NavigationScreenNames.TrackerRoute.route,
        navController = navHostController,
        startDestination = NavigationScreenNames.BodyCompositionRoute.route )
    {
        bodyCompositionGraph(navHostController = navHostController)

        workoutLogbookGraph(
            navHostController = navHostController
        )
    }
}