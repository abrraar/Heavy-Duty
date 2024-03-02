package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.workout.addWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.theme.Red

@Composable
fun AddWorkoutPrompt(){
    Column(
        modifier = Modifier
            .width(330.dp)
            .height(520.dp)
            .background(Color.Black, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.12f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "WorkoutScreen 1", color = Color.White)
        }

        Column {
            Text(text = "WorkoutScreen Name", color = Color.White)
            TextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp),
                placeholder = { Text(text = "Enter workout name") },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ))
        }
        Spacer(modifier = Modifier.padding(10.dp))
        CustomButton(onClick = {})
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(
            modifier = Modifier
                .background(
                    color = ScreenBackgroundColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .width(300.dp)
                .height(215.dp))
        {
            items(1)
            {

            }
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Red)
            )
            {
                Text(text = "Cancel")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Green)
            )
            {
                Text(text = "Confirm")
            }
        }
    }
}

@Preview
@Composable
private fun AddWorkoutPromptPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        AddWorkoutPrompt()
    }

}