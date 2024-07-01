package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise.ExerciseComponent
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.AddCycleEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.ViewExerciseUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.component.ExerciseComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState

@Composable
fun ViewExercise(
    viewExerciseUIState: ViewExerciseUIState,
    routeWorkout: Int,
    events: (AddCycleEvents) -> Unit,
    navHostController: NavHostController
){

    Log.i("index exercise", viewExerciseUIState.cycleIndexSelected.toString())
    Log.i("Workout route", routeWorkout.toString())
    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier
            .clickable {
                events(AddCycleEvents.UseCycleClicked("exercise", true, listOfDefaultCycle[viewExerciseUIState.cycleIndexSelected].cycleName))
            }
            .fillMaxWidth(1f)
            .height(60.dp)
            .background(color = Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                text = "Use this Cycle",
                color = Color.White)
        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            items(listOfDefaultCycle[viewExerciseUIState.cycleIndexSelected].listOfWorkout[routeWorkout].listOfExercise)
            {
                Spacer(modifier = Modifier.padding(top = 7.dp))
                ExerciseComponent(
                    exerciseModel = it,
                    enableDeleteBtn = false,
                    exerciseEvents = {},
                    exerciseScreenUIState = ExerciseScreenUIState(),
                    exerciseNameStyle = MaterialTheme.typography.headlineSmall,
                    exerciseComponentUIState = ExerciseComponentUIState(),
                    showDetails = false)
                Spacer(modifier = Modifier.padding(bottom = 7.dp))
            }
        }
    }
    if (viewExerciseUIState.useCycle){
        Prompt(
            titleText = "Add Cycle",
            message = "Do you want to use\n" + viewExerciseUIState.cycleName,
            onConfirm = {
                events(AddCycleEvents.ConfirmClicked(listOfDefaultCycle[viewExerciseUIState.cycleIndexSelected].cycleName))
                navHostController.navigate(NavigationScreenNames.WorkoutLogbook.route) {
                    popUpTo(NavigationScreenNames.ViewCycle.route) {
                        inclusive = true
                    }
                }
            },
            onCancel = {
                events(AddCycleEvents.UseCycleClicked("exercise", false, ""))
            }
        )
    }
}

@Preview
@Composable
private fun ViewExercisePreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ViewExercise(
            events = {},
            viewExerciseUIState = ViewExerciseUIState(),
            routeWorkout = 1,
            navHostController = rememberNavController()
        )
    }
}