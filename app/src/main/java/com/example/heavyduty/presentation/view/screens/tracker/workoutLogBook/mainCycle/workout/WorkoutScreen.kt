package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentEvents

@Composable
fun WorkoutScreen(
    events: (WorkoutLogbookEvents) -> Unit,
    workoutUIState: WorkoutUIState,
    navHostController: NavHostController
){
    if(workoutUIState.deleteClicked){
        Prompt(
            titleText = "Delete Exercise",
            message = "Do you want to delete\n" + workoutUIState.workoutModel!!.workoutName ,
            onConfirm = {
                events(WorkoutLogbookEvents.WorkoutDelete(
                    cycleNumber = workoutUIState.cycleIndex,
                    workoutModel = workoutUIState.workoutModel,
                ))
                events(WorkoutLogbookEvents.WorkoutDeleteClicked(
                    isClicked = false))
            },
            onCancel = {
                events(WorkoutLogbookEvents.WorkoutDeleteClicked(
                    isClicked = false))
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = ScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        LazyColumn(modifier = Modifier.fillMaxHeight(1f)) {
            itemsIndexed(workoutUIState.listOfCycle[workoutUIState.cycleIndex].cycleModel.listOfWorkout)
            {
                index, it ->
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                WorkoutComponent(
                    deleteEnable = true,
                    deleteClick = {
                        events(WorkoutLogbookEvents.WorkoutDeleteClicked(
                            workoutModel = it,
                            isClicked = true))

                    },
                    workoutNumber = it.workoutNumber,
                    workoutName = it.workoutName,
                    workoutDate = it.datePerformed,
                    progress = it.overallProgress.toString(),
                    modifier = Modifier.clickable
                    {
                        events(WorkoutLogbookEvents.WorkoutSelected(index))
                        navHostController.navigate(NavigationScreenNames.WorkoutLogbookExercise.route)
                    },
                    header = true,
                    bodyColor = Color.Black)
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutPreview(){
    WorkoutScreen(
        events = {},
        workoutUIState = WorkoutUIState(),
        navHostController = rememberNavController())
}