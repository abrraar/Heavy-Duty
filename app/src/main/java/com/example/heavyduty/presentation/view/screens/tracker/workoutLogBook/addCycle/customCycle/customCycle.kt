package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle.customCycle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.util.customTextField.CustomTextField


@Composable
private fun AboutComponent()
{
    Column(
        modifier = Modifier
            .height(250.dp)
            .width(330.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Cycle Name",
            color = Color.White,
            modifier = Modifier
                .width(300.dp)
                .padding(start = 5.dp, bottom = 5.dp, top = 10.dp),
            textAlign = TextAlign.Start)

        CustomTextField(
            placeholderText = "Enter cycle name",
            modifier = Modifier.height(30.dp).width(300.dp),
            singleLine = true
        )
        Text(
            text = "Cycle Description",
            color = Color.White,
            modifier = Modifier
                .width(300.dp)
                .padding(start = 5.dp, bottom = 5.dp, top = 10.dp),
            textAlign = TextAlign.Start)

        CustomTextField(
            placeholderText = "Enter cycle description",
            modifier = Modifier.height(120.dp).width(300.dp),
            textPlacementAlignment = Alignment.Top
        )

    }
}

@Preview
@Composable
private fun AboutComponentPreview(){
    AboutComponent()
}