package com.example.heavyduty.navigation.trackerScreenNavGraph

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.addBodyComposition.AddBodyComposition
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.bodyCompositionRecords.BodyCompositionRecord
import com.example.heavyduty.presentation.view.screens.tracker.bodyComposition.main.BodyCompositionScreen
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.addBodyComposition.AddBodyCompositionViewModel
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionUIState
import com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main.BodyCompositionViewModel

fun NavGraphBuilder.bodyCompositionGraph(navHostController: NavHostController){
    // Body Composition
    navigation(
        route = NavigationScreenNames.BodyCompositionRoute.route,
        startDestination = NavigationScreenNames.BodyComposition.route
    )
    {
        composable(route = NavigationScreenNames.BodyComposition.route )
        {
            val bodyCompositionViewModel = hiltViewModel<BodyCompositionViewModel>()
            val bodyCompositionUIState by bodyCompositionViewModel.bodyCompositionState.collectAsState()
            BodyCompositionScreen(
                navController = navHostController,
                state = bodyCompositionUIState,
                events = bodyCompositionViewModel::onBodyCompositionEvents)

        }
        composable(route = NavigationScreenNames.AddBodyCompositionRecords.route)
        {
            val addBodyCompositionViewModel = hiltViewModel<AddBodyCompositionViewModel>()
            val addBodyCompositionUIState by addBodyCompositionViewModel.state.collectAsState()

            AddBodyComposition(
                addBodyCompositionUIState = addBodyCompositionUIState,
                addBodyCompositionEvents = addBodyCompositionViewModel::onAddBodyCompositionEvents
            )
        }

        composable(route = NavigationScreenNames.BodyCompositionRecords.route)
        {
            BodyCompositionRecord()
        }
    }
}