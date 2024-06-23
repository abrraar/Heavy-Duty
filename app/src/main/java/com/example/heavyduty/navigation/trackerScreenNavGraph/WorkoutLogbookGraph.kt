package com.example.heavyduty.navigation.trackerScreenNavGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle.ViewCycle
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle.ViewExercise
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle.ViewWorkout
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.cycle.CycleScreen
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.WorkoutScreen
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise.ExerciseScreen
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.AddCycleViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookViewModel


fun NavGraphBuilder.workoutLogbookGraph(
    navHostController: NavHostController){

    // WorkoutLogbook
    navigation(
        route = NavigationScreenNames.WorkoutLogbookRoute.route,
        startDestination = NavigationScreenNames.WorkoutLogbook.route
    )
    {
        composable(route = NavigationScreenNames.WorkoutLogbook.route )
        {
            val workoutLogbookViewModel = it.sharedViewModel<WorkoutLogbookViewModel>(navController = navHostController)
            val cycleUIState by workoutLogbookViewModel.cycleUIState.collectAsState()
            CycleScreen(
                events = workoutLogbookViewModel::cycleEvents,
                cycleScreenUIState = cycleUIState,
                navHostController = navHostController)
        }

        composable(route = NavigationScreenNames.WorkoutLogbookWorkout.route)
        {
            val workoutLogbookViewModel = it.sharedViewModel<WorkoutLogbookViewModel>(navController = navHostController)
            val workoutUIState by workoutLogbookViewModel.workoutUIState.collectAsState()
            WorkoutScreen(
                events = workoutLogbookViewModel::workoutEvents,
                workoutUIState = workoutUIState,
                navHostController = navHostController)
        }
        composable(route = NavigationScreenNames.WorkoutLogbookExercise.route)
        {

            val workoutLogbookViewModel = it.sharedViewModel<WorkoutLogbookViewModel>(navController = navHostController)
            val exerciseScreenUIState by workoutLogbookViewModel.exerciseScreenUIState.collectAsState()

            ExerciseScreen(
                exerciseEvents = workoutLogbookViewModel::exerciseEvents,
                exerciseScreenUIState = exerciseScreenUIState
            )
        }

        // View Cycle Workout and Exercise
        navigation(
            route =  NavigationScreenNames.ViewRoute.route,
            startDestination = NavigationScreenNames.ViewCycle.route
        )
        {
            composable(route = NavigationScreenNames.ViewCycle.route)
            {
                val addCycleViewModel = it.sharedViewModel<AddCycleViewModel>(navController = navHostController)
                val viewCycleUIState by addCycleViewModel.cycleState.collectAsState()

                ViewCycle(
                    viewCycleUIState = viewCycleUIState,
                    events = addCycleViewModel::onWorkoutEvents,
                    onNavigate = {
                        routeCycle -> navHostController.navigate(route = "${NavigationScreenNames.ViewWorkout.route}?route=$routeCycle")
                    },
                    navHostController = navHostController)
            }
            composable(
                route = "${NavigationScreenNames.ViewWorkout.route}?route={routeCycle}",
                arguments = listOf(navArgument("routeCycle"){type = NavType.IntType})
            )
            {
                val routeCycle = it.arguments!!.getInt("routeCycle")
                val addCycleViewModel = it.sharedViewModel<AddCycleViewModel>(navController = navHostController)
                val viewWorkoutUIState by addCycleViewModel.workoutState.collectAsState()

                ViewWorkout(
                    viewWorkoutUIState = viewWorkoutUIState,
                    events = addCycleViewModel::onWorkoutEvents,
                    routeCycle = routeCycle,
                    onNavigate = {
                        routeWorkout ->
                        navHostController.navigate(route = "${NavigationScreenNames.ViewExercise.route}?route=$routeWorkout")
                    },
                    navHostController = navHostController)
            }
            composable(
                route = "${NavigationScreenNames.ViewExercise.route}?route={routeWorkout}",
                arguments = listOf(navArgument("routeWorkout"){type = NavType.IntType}))
            {
                val routeWorkout = it.arguments!!.getInt("routeWorkout")
                val addCycleViewModel = it.sharedViewModel<AddCycleViewModel>(navController = navHostController)
                val exerciseUIState by addCycleViewModel.exerciseState.collectAsState()

                ViewExercise(
                    events = addCycleViewModel::onWorkoutEvents,
                    viewExerciseUIState = exerciseUIState,
                    routeWorkout = routeWorkout,
                    navHostController = navHostController)
            }
        }
    }
}

@Composable
inline fun < reified T : ViewModel > NavBackStackEntry.sharedViewModel(navController: NavController): T
{
    val navGraph = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraph)
    }
    return hiltViewModel(parentEntry)
}