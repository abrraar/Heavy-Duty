package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.cycle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
import com.example.heavyduty.presentation.view.util.searchBars.RecycleBinSearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState

@Composable
fun WorkoutLogBookRecycleBin(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RecycleBinSearchBar()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(ScreenBackgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            items(2){
                Spacer(modifier = Modifier.padding(bottom = 20.dp))
                CycleComponent(
                    events = {},
                    cycleComponentUIState = CycleComponentUIState(),
                    cycle = Cycle(
                        cycleModel = CycleModel(
                            cycleName = "",
                            listOfWorkout = arrayListOf(),
                            startDate = "",
                            endDate = "",
                            overallProgress = 0
                        )
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WorkoutLogBookPreviewPreview(){
    WorkoutLogBookRecycleBin()
}

