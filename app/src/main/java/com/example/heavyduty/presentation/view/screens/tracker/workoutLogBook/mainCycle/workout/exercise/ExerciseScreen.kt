package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentViewModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import dagger.Module
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Named

@Composable
fun ExerciseScreen(
    workoutLogbookViewModel: WorkoutLogbookViewModel,
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
                    workoutLogbookViewModel = workoutLogbookViewModel,
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
            workoutLogbookViewModel = hiltViewModel(),
            exerciseScreenUIState = ExerciseScreenUIState (emptyList()),
        )
    }
}

