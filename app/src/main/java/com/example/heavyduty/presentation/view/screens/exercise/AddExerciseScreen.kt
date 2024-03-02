package com.example.heavyduty.presentation.view.screens.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.util.customButton.CustomButton
import com.example.heavyduty.presentation.view.util.customCard.CustomCard
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor

@Composable
fun AddExerciseScreen(){
    Column(modifier = Modifier
        .fillMaxHeight(1f)
        .fillMaxWidth(1f)
        .background(color = ScreenBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top)
    {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        CustomCard(header = stringResource(id = R.string.about_exercise)) {
            Text(
                text = stringResource(id = R.string.exercise_name),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp))
            CustomTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp),
                placeholderText = stringResource(id = R.string.ent_exercise_name))
            CustomButton(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(40.dp)
                    .width(300.dp),
                text = stringResource(id = R.string.muscle_involvement),
                onClick = {})
            Text(
                text = stringResource(id = R.string.exercise_movement),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp))
            Text(
                text = "Compound",
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp))
            Spacer(modifier = Modifier.padding(bottom = 5.dp))
        }
        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        CustomCard(header = stringResource(id = R.string.more_about_exercise)) {
            Text(
                text = stringResource(id = R.string.exercise_instruction),
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp))
            CustomTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp),
                textPlacementAlignment = Alignment.Top,
                placeholderText = stringResource(id = R.string.ent_exercise_instruction),)
            Text(
                text = stringResource(id = R.string.exercise_description),
                color = Color.White,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
            CustomTextField(
                modifier = Modifier
                    .width(300.dp)
                    .height(120.dp),
                textPlacementAlignment = Alignment.Top,
                placeholderText = stringResource(id = R.string.ent_exercise_description))
            Spacer(modifier = Modifier.padding(bottom = 15.dp))
        }
    }
}

@Preview
@Composable
private fun AddExerciseScreenPreview(){
    AddExerciseScreen()
}