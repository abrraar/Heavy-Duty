package com.example.heavyduty.presentation.viewModel.tracker



import androidx.lifecycle.ViewModel
import com.example.heavyduty.navigation.NavigationScreenNames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class TrackerViewModel
constructor(): ViewModel()
{
    private val _state = MutableStateFlow(TrackerUIState())
    val state = _state.asStateFlow()

    private val workoutNavScreenList = listOf(
        NavigationScreenNames.WorkoutLogbook.route,
        NavigationScreenNames.WorkoutLogbookRoute.route,
        NavigationScreenNames.WorkoutLogbookWorkout.route,
        NavigationScreenNames.WorkoutLogbookExercise.route,
        "view_workout?route={routeCycle}",
        NavigationScreenNames.ViewCycle.route,
        "view_exercise?route={routeWorkout}",
        NavigationScreenNames.ViewRoute.route
    )
    private val bodyCompNavScreenList = listOf(
        NavigationScreenNames.BodyComposition.route,
        NavigationScreenNames.BodyCompositionRoute.route,
        NavigationScreenNames.BodyCompositionRecords.route,
        NavigationScreenNames.AddBodyCompositionRecords.route
    )

    fun tabPosition(route: String){
        for (workoutRoute in bodyCompNavScreenList){
            if(route == workoutRoute){
                _state.update {
                    it.copy(
                        selectedItemIndex = 1)
                }
            }
        }

        for (bodyCompRoute in workoutNavScreenList){
            if(route == bodyCompRoute){
                _state.update {
                    it.copy(
                        selectedItemIndex = 0
                    )
                }
            }
        }
    }

}