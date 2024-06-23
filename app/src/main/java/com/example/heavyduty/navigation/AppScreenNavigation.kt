package com.example.heavyduty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.heavyduty.presentation.view.screens.exercise.ExerciseListScreen
import com.example.heavyduty.presentation.view.screens.profile.ProfileScreen
import com.example.heavyduty.presentation.view.screens.tracker.TrackerScreen
import com.example.heavyduty.presentation.viewModel.tracker.TrackerViewModel

@Composable
fun AppScreenNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        route = NavigationScreenNames.MainScreens.route,
        startDestination = NavigationScreenNames.Tracker.route )
    {
        composable(route = NavigationScreenNames.Tracker.route) {
            TrackerScreen()
        }
        composable(route = NavigationScreenNames.Exercise.route) {
            ExerciseListScreen()
        }
        composable(route = NavigationScreenNames.Profile.route) {
            ProfileScreen()
        }
    }
}

