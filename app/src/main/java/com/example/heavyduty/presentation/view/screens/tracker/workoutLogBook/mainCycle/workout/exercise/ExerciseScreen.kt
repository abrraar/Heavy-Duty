package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState

@Composable
fun ExerciseScreen(
    events: (WorkoutLogbookEvents) -> Unit,
    exerciseScreenUIState: ExerciseScreenUIState )
{
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            addClickable = {}
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .verticalScroll(rememberScrollState())
        )
        {
            for ( it in
                exerciseScreenUIState
                .listOfCycle[exerciseScreenUIState.cycleIndex]
                .cycleModel.listOfWorkout[exerciseScreenUIState.workoutIndex]
                .listOfExercise)
            {
                ExerciseComponent(
                    exerciseModel = it,
                    exerciseScreenUIState = exerciseScreenUIState,
                    workoutLogbookEvents = events
                    )
            }
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun ExercisePreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ExerciseScreen(
            events = {},
            exerciseScreenUIState = ExerciseScreenUIState(),
        )
    }
}

