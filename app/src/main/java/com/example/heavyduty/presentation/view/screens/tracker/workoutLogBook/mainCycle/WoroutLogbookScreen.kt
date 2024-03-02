package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.theme.Red
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookUIState

@Composable
fun WorkoutLogBookScreen(
    events: (WorkoutLogbookEvents) -> Unit,
    workoutLogbookComponentUIState: WorkoutLogbookComponentUIState,
    workoutLogbookUIState: WorkoutLogbookUIState,
    navHostController: NavHostController
){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(250.dp)
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Data not available",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground)
        }
        SearchBar(
            filterClickable = {},
            addClickable = { navHostController.navigate(NavigationScreenNames.ViewRoute.route)
        })

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(ScreenBackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (workoutLogbookUIState.listOfCycle.isEmpty())
            {
                Text(
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Click on \"+\" to add cycle"
                )
            }
            else
            {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    itemsIndexed(workoutLogbookUIState.listOfCycle)
                    {
                    index, it ->
                        val cycleNum = index + 1
                        Component(
                            cycleNumber =
                            when (cycleNum) {
                                1 -> { cycleNum.toString()+"st Cycle" }
                                2 -> { cycleNum.toString()+"nd Cycle" }
                                3 -> { cycleNum.toString()+"rd Cycle" }
                                else -> { cycleNum.toString()+"th Cycle" }
                            },
                            cycleName = it.cycleModel.cycleName,
                            overallProgress = it.cycleModel.overallProgress.toString(),
                            startDate = it.cycleModel.startDate,
                            endDate = it.cycleModel.endDate,
                            workoutLogbookComponentUIState = workoutLogbookComponentUIState,
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp)
                                .clickable {
                                    events(WorkoutLogbookEvents.CycleSelected(index))
                                    navHostController.navigate(
                                        NavigationScreenNames.WorkoutLogbookWorkout.route
                                    )
                                }
                        )
                    }
                }
            }
        }


    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WorkoutLogBookScreenPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        WorkoutLogBookScreen(
            events = {},
            workoutLogbookComponentUIState = WorkoutLogbookComponentUIState(),
            workoutLogbookUIState = WorkoutLogbookUIState(),
            rememberNavController())
    }

}

