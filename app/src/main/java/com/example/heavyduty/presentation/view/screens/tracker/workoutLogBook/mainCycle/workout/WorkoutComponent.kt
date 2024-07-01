package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutComponentUIState

@Composable
fun WorkoutComponent(
    modifier: Modifier = Modifier,
    workoutComponentUIState: WorkoutComponentUIState = WorkoutComponentUIState(),
    numOfText: Int = 3,
    deleteEnable: Boolean = true,
    deleteClick: () -> Unit,
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
            .clip(shape = RoundedCornerShape(20.dp))
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
                            modifier
                                .weight(1f)
                                .padding(end = 15.dp),
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

            Column {
                Row(
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Box(modifier = modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Exercise Name",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Box(modifier = modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Date of completion",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Box(modifier = modifier
                        .weight(1f)
                        .height(60.dp)
                        .background(color = MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Overall Progress",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                }
                Row {
                    Box(modifier = modifier
                        .weight(1f)
                        .height(80.dp)
                        .background(color = Color.Black),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = workoutComponentUIState.workoutName,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Box(modifier = modifier
                        .weight(1f)
                        .height(80.dp)
                        .background(color = Color.Black),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = workoutComponentUIState.workoutDate,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Box(modifier = modifier
                        .weight(1f)
                        .height(80.dp)
                        .background(color = Color.Black),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = workoutComponentUIState.overallProgress,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                }
            }

        }
        else{
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(140.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                userScrollEnabled = false
            ){
                items(numOfText){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.weight(0.6f),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text(
                                text = when(it){
                                0 -> workoutComponentUIState.workoutName
                                1 -> workoutComponentUIState.workoutDate
                                2 -> workoutComponentUIState.overallProgress
                                else -> "no more than 3 items"
                            })
                        }

                    }
                }
            }
        }
        if (composable != null){
            composable()
        }

    }
}



@Preview
@Composable
private fun ComponentPreview(){
    WorkoutComponent(
        deleteClick = {},
        header = true, bodyColor = Color.Black)
}

@Preview
@Composable
private fun ComponentPreviewWithoutHeader(){
    WorkoutComponent(header = false, bodyColor = Color.LightGray, deleteClick = {})
}