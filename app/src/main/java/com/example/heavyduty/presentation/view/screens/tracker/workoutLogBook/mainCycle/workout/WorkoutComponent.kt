package com.example.heavyduty.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.theme.MainHIT

@Composable
fun Component(
    header: Boolean,
    bodyColor: Color){
    Column(
        modifier = Modifier
            .height(150.dp)
            .width(330.dp)
            .background(
                color = bodyColor,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        if (header){
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = MainHIT,
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Workout 1",
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp))
            }
            Row {
                CustomColumn(text = "Suwi")
                CustomColumn(text = "Suwi")
                CustomColumn(text = "Suw")
            }
        }
        else{
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomColumn(text = "Suwi")
                CustomColumn(text = "Suwi")
                CustomColumn(text = "Suw")
            }
        }

    }
}

@Composable
private fun CustomColumn(text: String){
    Column(
        modifier = Modifier
            .width(110.dp)
            .height(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, color = Color.White)
    }
}


@Preview
@Composable
private fun ComponentPreview(){
    Component(header = true, bodyColor = Color.Black)
}

@Preview
@Composable
private fun ComponentPreviewWithoutHeader(){
    Component(header = false, bodyColor = Color.LightGray)
}