package com.example.heavyduty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.main.BodyCompositionScreen
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.WorkoutLogBookScreen
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionUIState

fun NavGraphBuilder.trackerTabNavigation(){
    navigation(
        startDestination = NavigationScreenNames.WorkoutLogbook.route,
        route = NavigationScreenNames.TrackerScreen.route)
    {
        composable(route = NavigationScreenNames.WorkoutLogbook.route)
        {
            WorkoutLogBookScreen()
        }
        composable(route = NavigationScreenNames.BodyComposition.route)
        {
            BodyCompositionScreen(
                state = BodyCompositionUIState(),
                events = {})
        }
    }
}