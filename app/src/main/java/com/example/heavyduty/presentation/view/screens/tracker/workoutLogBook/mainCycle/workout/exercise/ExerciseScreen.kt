package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.ExerciseEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.toExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState

@Composable
fun ExerciseScreen(
    exerciseEvents: (ExerciseEvents) -> Unit,
    exerciseScreenUIState: ExerciseScreenUIState,
)
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
        if(exerciseScreenUIState
                .listOfCycle[exerciseScreenUIState.cycleIndex].second
                .listOfWorkout[exerciseScreenUIState.workoutIndex]
                .listOfExercise.isEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Exercise list is empty\nClick on \"+\" to add exercise "
                )
            }

        }
        else
        {
            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                itemsIndexed(
                    exerciseScreenUIState
                        .listOfCycle[exerciseScreenUIState.cycleIndex].second
                        .listOfWorkout[exerciseScreenUIState.workoutIndex]
                        .listOfExercise
                ){
                    index, item ->
                    val exerciseNum = index.plus(1).toString()
                    val exerciseComponentUIState = item.toExerciseComponentUIState()

                    ExerciseComponent(
                        exerciseModel = item,
                        exerciseNumber = exerciseNum,
                        exerciseScreenUIState = exerciseScreenUIState,
                        exerciseEvents = exerciseEvents,
                        exerciseComponentUIState = exerciseComponentUIState
                    )
                }

            }

//            Column(
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//                    .fillMaxHeight()
//                    .fillMaxWidth()
//            ) {
//
//                for((index, it) in exerciseScreenUIState
//                    .listOfCycle[exerciseScreenUIState.cycleIndex].second
//                    .listOfWorkout[exerciseScreenUIState.workoutIndex]
//                    .listOfExercise.withIndex())
//                {
//                    val exerciseNum = index.plus(1).toString()
//                    val exerciseComponentUIState = it.toExerciseComponentUIState()
//
//                    ExerciseComponent(
//                        exerciseModel = it,
//                        exerciseNumber = exerciseNum,
//                        exerciseScreenUIState = exerciseScreenUIState,
//                        exerciseEvents = exerciseEvents,
//                        exerciseComponentUIState = exerciseComponentUIState
//                    )
//                }
//            }
        }
    }
}


@Preview
@Composable
private fun ExercisePreview(){
    ExerciseScreen(
        exerciseEvents = {},
        exerciseScreenUIState = ExerciseScreenUIState(),
    )
}

