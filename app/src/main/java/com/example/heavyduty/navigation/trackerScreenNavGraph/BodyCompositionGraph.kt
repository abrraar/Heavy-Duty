package com.example.heavyduty.navigation.trackerScreenNavGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.addBodyComposition.AddBodyComposition
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecord
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.main.BodyCompositionScreen
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionUIState

fun NavGraphBuilder.bodyCompositionGraph(navHostController: NavHostController){
    // Body Composition
    navigation(
        route = NavigationScreenNames.BodyCompositionRoute.route,
        startDestination = NavigationScreenNames.BodyComposition.route
    )
    {
        composable(route = NavigationScreenNames.BodyComposition.route )
        {
            BodyCompositionScreen(
                navController = navHostController,
                state = BodyCompositionUIState(),
                events = {})

        }
        composable(route = NavigationScreenNames.AddBodyCompositionRecords.route)
        {
            AddBodyComposition(addBodyCompositionUIState = AddBodyCompositionUIState())
        }

        composable(route = NavigationScreenNames.BodyCompositionRecords.route)
        {
            BodyCompositionRecord()
        }
    }
}