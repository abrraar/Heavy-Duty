package com.example.heavyduty.navigation.trackerScreenNavGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.AddCycleViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentViewModel

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