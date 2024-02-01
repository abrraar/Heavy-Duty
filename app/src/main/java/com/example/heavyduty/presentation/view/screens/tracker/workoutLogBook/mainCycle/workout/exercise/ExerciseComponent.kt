package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.exercise


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heavyduty.data.local.tracker.workoutLogbook.IntensityComponent.intensityHeaderList
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.theme.MainHIT

@Composable
fun ExerciseComponent(){
    Column(
        modifier = Modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            )
            .height(370.dp)
            .width(330.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier
            .background(
                color = MainHIT,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .fillMaxWidth(1f)
            .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start)
        {
            Text(
                text = "Exercise 1",
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Text(
            text = "Incline Press",
            color = Color.White,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
        CustomButton(onClick = {})
        Spacer(modifier = Modifier.padding(bottom = 15.dp))
        Body()

    }
}

@Composable
private fun IntensityComponent() {
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(250.dp)
            .background(
                color = Color.Green,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(bottom = 3.dp))
        Column(
            modifier = Modifier
                .height(60.dp)
                .width(245.dp)
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Pre Exhaust", color = Color.White)
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text = "Add", color = Color.White)
        }

    }
}


@Composable
private fun Body(){
    Column(
        modifier = Modifier
            .width(300.dp)
            .background(color = Color.Black, shape = RoundedCornerShape(20.dp))
    ) {
        LazyRow(modifier = Modifier.fillMaxWidth(1f))
        {
            itemsIndexed(intensityHeaderList)
            {
                index, item ->
                when(index){
                    0 ->  CustomHeaderBox(text = item, corner = "start")
                    3 ->  CustomHeaderBox(text = item, corner = "end")
                    else ->  CustomHeaderBox(text = item, corner = "")
                }
            }
        }
        CustomRow("", "","")
    }
}

@Composable
private fun CustomRow(
    intensityComponent: String,
    previousValue: String,
    increaseRate: String
)
{
    LazyRow {
       items(4) {
           item ->
           Box(modifier = Modifier
               .background(
                   color = when (item) {
                       0 -> MainHIT
                       else -> Color.DarkGray
                   }
               )
               .height(50.dp)
               .width(75.dp),
               contentAlignment = Alignment.Center)
           {
               when(item){
                   0 -> Text(
                       text = intensityComponent,
                       color = Color.White,
                       textAlign = TextAlign.Center,
                       fontSize = 10.sp,
                       modifier = Modifier.padding(10.dp))
                   1 -> TextField(
                       value = "",
                       onValueChange = {},
                       colors = TextFieldDefaults.colors(
                           disabledTextColor = Color.Transparent,
                           focusedContainerColor = Color.LightGray,
                           unfocusedContainerColor = Color.LightGray,
                           disabledContainerColor = Color.LightGray,
                           focusedIndicatorColor = Color.Transparent,
                           unfocusedIndicatorColor = Color.Transparent,
                           disabledIndicatorColor = Color.Transparent,
                       ),
                       shape = RoundedCornerShape(0.dp),
                       singleLine = true,
                       placeholder = { Text(text = "..........")},
                       textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                       keyboardOptions = KeyboardOptions(
                           keyboardType = KeyboardType.Number,
                           imeAction = ImeAction.Done
                       )
                   )
                   2 -> Text(
                       text = previousValue,
                       color = Color.White,
                       textAlign = TextAlign.Center,
                       fontSize = 10.sp,
                       modifier = Modifier.padding(10.dp))
                   3 -> Text(
                       text = increaseRate,
                       color = Color.White,
                       textAlign = TextAlign.Center,
                       fontSize = 10.sp,
                       modifier = Modifier.padding(10.dp))
               }
           }
       }
    }
}

@Composable
private fun CustomHeaderBox(
    text: Int,
    corner: String)
{
    Column(
        modifier = Modifier
            .height(50.dp)
            .width(75.dp)
            .background(
                color = MainHIT,
                shape = when (corner) {
                    "start" -> RoundedCornerShape(topStart = 20.dp)
                    "end" -> RoundedCornerShape(topEnd = 20.dp)
                    else -> {
                        RoundedCornerShape(0.dp)
                    }
                }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = text),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}


@Preview
@Composable
private fun IntensityComponentPreview(){
    IntensityComponent()
}


@Preview
@Composable
private fun BodyPreview(){
    Body()
}

@Preview
@Composable
private fun CustomRowPreview(){
    CustomRow("Positive Reps", "4", "10%")
}


@Preview
@Composable
private fun ExerciseComponentPreview(){
    ExerciseComponent()
}