package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutScreenUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.toWorkoutComponentUIState

@Composable
fun WorkoutScreen(
    events: (WorkoutEvents) -> Unit,
    workoutScreenUIState: WorkoutScreenUIState,
    navHostController: NavHostController
){
    if(workoutScreenUIState.deleteClicked){
        Prompt(
            titleText = "Delete Exercise",
            message = "Do you want to delete\n" + workoutScreenUIState.workoutModel!!.workoutName ,
            onConfirm = {
                events(WorkoutEvents.WorkoutDelete(
                    cycleNumber = workoutScreenUIState.cycleIndex,
                    workoutModel = workoutScreenUIState.workoutModel,
                ))
                events(WorkoutEvents.WorkoutDeleteClicked(
                    isClicked = false))
            },
            onCancel = {
                events(WorkoutEvents.WorkoutDeleteClicked(
                    isClicked = false))
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = ScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(
            addClickable = {}
        )
        if(workoutScreenUIState.listOfCycle[workoutScreenUIState.cycleIndex].cycleModel.listOfWorkout.isEmpty()){
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Workout list is empty\nClick on \"+\" to add workout"
                )
            }

        }
        else
        {
            LazyColumn(modifier = Modifier.fillMaxHeight(1f)) {
                itemsIndexed(items = workoutScreenUIState.listOfCycle[workoutScreenUIState.cycleIndex].cycleModel.listOfWorkout)
                {
                index, it ->
                    Spacer(modifier = Modifier.padding(bottom = 15.dp))
                    val workoutComponentUIState = it.toWorkoutComponentUIState()
                    WorkoutComponent(
                        deleteEnable = true,
                        workoutComponentUIState = workoutComponentUIState,
                        deleteClick = {
                            events(WorkoutEvents.WorkoutDeleteClicked(
                                workoutModel = it,
                                isClicked = true))
                        },
                        workoutNumber = it.workoutNumber,
                        modifier = Modifier.clickable
                        {
                            events(WorkoutEvents.WorkoutSelected(index))
                            navHostController.navigate(NavigationScreenNames.WorkoutLogbookExercise.route)
                        },
                        header = true,
                        bodyColor = Color.Black)
                }
            }
        }

    }
}

@Preview
@Composable
private fun WorkoutPreview(){
    WorkoutScreen(
        events = {},
        workoutScreenUIState = WorkoutScreenUIState(),
        navHostController = rememberNavController())
}