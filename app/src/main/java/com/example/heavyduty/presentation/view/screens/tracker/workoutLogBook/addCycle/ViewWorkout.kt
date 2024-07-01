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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.WorkoutComponent
import com.example.heavyduty.presentation.view.theme.Black
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.AddCycleEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.ViewWorkoutUIState

@Composable
fun ViewWorkout(
    viewWorkoutUIState: ViewWorkoutUIState,
    events: (AddCycleEvents) -> Unit,
    routeCycle: Int,
    onNavigate: (Int) -> Unit,
    navHostController: NavHostController)
{

    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(modifier = Modifier
            .clickable {
                events(AddCycleEvents.UseCycleClicked("workout",true, listOfDefaultCycle[routeCycle].cycleName ))
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
            itemsIndexed(listOfDefaultCycle[routeCycle].listOfWorkout)
            {
                index, it ->

                Log.i("Index Workout", routeCycle.toString())

                Spacer(modifier = Modifier.padding(top = 20.dp))
                WorkoutComponent(
                    deleteClick = {},
                    numOfText = 1,
                    header = true,
                    deleteEnable = false,
                    workoutNumber = it.workoutNumber,
                    bodyColor = Black) {
                    Row(
                        modifier = Modifier
                            .height(50.dp)
                            .clickable(onClick = {
                                onNavigate.invoke(index)
                            })
                            .background(
                                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                                color = Color.White.copy(alpha = 0.3f)
                            )
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center)
                    {
                        Text(
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            text = "Click to view Exercise",
                            color = Color.White)
                    }
                }
            }
        }

    }
    if (viewWorkoutUIState.useCycle){
        Prompt(
            titleText = "Add Cycle",
            message = "Do you want to use\n" + viewWorkoutUIState.cycleName,
            onConfirm = {
                events(AddCycleEvents.ConfirmClicked(listOfDefaultCycle[routeCycle].cycleName))
                navHostController.navigate(NavigationScreenNames.WorkoutLogbook.route) {
                    popUpTo(NavigationScreenNames.ViewCycle.route) {
                        inclusive = true
                    }
                }


            },
            onCancel = {
                events(AddCycleEvents.UseCycleClicked("workout", false, ""))
            }
        )
    }

}

@Preview
@Composable
private fun ViewWorkoutPreview(){
    ViewWorkout(
        events = {},
        viewWorkoutUIState = ViewWorkoutUIState(),
        routeCycle = 1,
        onNavigate = {},
        navHostController = rememberNavController(),)
}