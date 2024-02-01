package com.example.heavyduty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heavyduty.view.screens.exercise.ExerciseScreen
import com.example.heavyduty.view.screens.profile.ProfileScreen
import com.example.heavyduty.presentation.view.screens.tracker.TrackerScreen

fun NavGraphBuilder.bottomBarNavigation(){
    navigation(
        startDestination = NavigationScreenNames.Tracker.route,
        route = NavigationScreenNames.AppScreen.route)
    {
        composable(route = NavigationScreenNames.Tracker.route){
            TrackerScreen()
        }
        composable(route = NavigationScreenNames.Exercise.route){
            ExerciseScreen()
        }
        composable(route = NavigationScreenNames.Profile.route){
            ProfileScreen()
        }
    }
}

