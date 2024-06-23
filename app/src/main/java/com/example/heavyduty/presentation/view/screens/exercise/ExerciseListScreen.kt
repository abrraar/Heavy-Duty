package com.example.heavyduty.presentation.view.screens.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor

@Composable
fun ExerciseListScreen(){
    Column {
        SearchBar()
        LazyColumn(modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = ScreenBackgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            items(4) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                ExerciseRowComponent()
            }
        }
    }
}

@Composable
private fun ExerciseRowComponent(){
    Row {
        Box(
            modifier = Modifier
                .height(115.dp)
                .width(185.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {

        }
        Spacer(modifier = Modifier.padding(end = 10.dp))
        Box(
            modifier = Modifier
                .height(115.dp)
                .width(135.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
        ){

        }
    }
}

@Preview
@Composable
private fun ExerciseRowComponentPreview(){
    ExerciseRowComponent()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ExerciseScreenPreview(){
    ExerciseListScreen()
}