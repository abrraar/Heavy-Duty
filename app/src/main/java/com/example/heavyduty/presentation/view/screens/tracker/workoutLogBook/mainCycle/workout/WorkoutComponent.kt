package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@Composable
fun WorkoutComponent(
    modifier: Modifier = Modifier,
    numOfText: Int = 3,
    deleteEnable: Boolean = true,
    deleteClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    workoutName: String = "Exercise Name",
    workoutDate: String = "Exercise Date",
    progress: String = "Exercise Progress",
    header: Boolean,
    workoutNumber: Int = 1,
    headerStyle: TextStyle = MaterialTheme.typography.titleSmall,
    bodyColor: Color,
    composable: (@Composable () -> Unit)? = null)
{
    Column(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .width(360.dp)
            .background(
                color = bodyColor,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        if (header){
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Row(modifier.fillMaxWidth(1f)) {
                    Column(
                        modifier.weight(1f)
                    ) {
                        Text(
                            style = headerStyle,
                            text = "Workout $workoutNumber",
                            color = Color.White,
                            modifier = Modifier.padding(start = 15.dp))
                    }
                    if(deleteEnable){
                        Column(
                            modifier.weight(1f).padding(end = 15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ){
                            Icon(
                                modifier = Modifier.clickable(onClick = deleteClick),
                                painter = painterResource(id = R.drawable.delete_icn),
                                contentDescription = null )
                        }
                    }

                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                items(numOfText){
                    CustomColumn(
                        numOfText = numOfText,
                        textStyle = textStyle,
                        text = when(it){
                        0 -> workoutName
                        1 -> workoutDate
                        2 -> progress
                        else -> "no more than 3 items"
                    })
                }
            }
        }
        else{
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(){
                    items(numOfText){
                        CustomColumn(
                            numOfText = numOfText,
                            textStyle = textStyle,
                            text = when(it){
                                0 -> workoutName
                                1 -> workoutDate
                                2 -> progress
                                else -> "no more than 3 items"
                        })
                    }
                }
            }
        }
        if (composable != null){
            composable()
        }

    }
}

@Composable
private fun CustomColumn(
    numOfText: Int,
    textStyle: TextStyle,
    text: String){
    Column(
        modifier = Modifier
            .width(
                if (numOfText == 3) {
                    120.dp
                } else {
                    360.dp
                }
            )
            .height(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            style = textStyle,
            textAlign = TextAlign.Center,
            text = text,
            color = Color.White)
    }
}


@Preview
@Composable
private fun ComponentPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        WorkoutComponent(
            deleteClick = {},
            header = true, bodyColor = Color.Black)
    }

}

@Preview
@Composable
private fun ComponentPreviewWithoutHeader(){
    WorkoutComponent(header = false, bodyColor = Color.LightGray, deleteClick = {})
}