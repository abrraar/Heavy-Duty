package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.view.screens.tracker.workoutLogBook.mainCycle.workout.Component

@Composable
fun Workout(){
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = ScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar()
        LazyColumn(modifier = Modifier.fillMaxHeight(1f)) {
            items(2){
                Spacer(modifier = Modifier.padding(bottom = 15.dp))
                Component(header = true, bodyColor = Color.Black)
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutPreview(){
    Workout()
}