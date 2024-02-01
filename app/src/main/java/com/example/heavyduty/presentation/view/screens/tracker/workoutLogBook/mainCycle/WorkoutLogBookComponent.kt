package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.Red


@Composable
fun Component(
    modifier: Modifier = Modifier,
    color: Color,
    icon: Boolean){

    Column(
        modifier = modifier
            .width(330.dp)
            .height(180.dp)
            .background(color = color, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if (icon){
            Row(modifier = Modifier
                .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.Absolute.Right,
                verticalAlignment = Alignment.CenterVertically)
            {
                Icon(
                    painter = painterResource(R.drawable.delete_icn),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { },
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "1st Cycle", color = Color.White)
            Text(text = "The Ideal Workout", color = Color.White)
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Text(text = "Overall Progress", color = Color.White)
            Text(text = "20%", color = Color.White)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.width(250.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(horizontalAlignment = Alignment.End)
            {
                Text(text = "Start date", color = Color.White)
            }

            Column(horizontalAlignment = Alignment.Start)
            {
                Text(text = "End date", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun ComponentPreview(){
    Component(color = Red, icon = true)
}