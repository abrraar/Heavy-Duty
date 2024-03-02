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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.WorkoutLogbookComponentUIState


@Composable
fun Component(
    modifier: Modifier = Modifier,
    cycleNumber: String = "1",
    cycleName: String = "Cycle Name",
    overallProgress: String = "10",
    startDate: String = "dd/mm/yy",
    endDate: String = "dd/mm/yy",
    workoutLogbookComponentUIState: WorkoutLogbookComponentUIState){

    Column(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .width(350.dp)
            .height(220.dp)
            .background(color = workoutLogbookComponentUIState.color, shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        Row(modifier = Modifier
            .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.Absolute.Right,
            verticalAlignment = Alignment.CenterVertically)
        {
            Icon(
                painter = painterResource(R.drawable.delete_icn),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .clickable { },
                tint = Color.White
            )

        }

        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                style = MaterialTheme.typography.titleSmall,
                text = cycleNumber,
                color = Color.White)

            Text(
                modifier = Modifier.padding(top = 5.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = cycleName,
                color = Color.White)

            Spacer(modifier = Modifier.padding(bottom = 20.dp))

            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                text = "Overall Progress",
                color = Color.White)

            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = overallProgress+ "%",
                color = Color.White)
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Column(
                modifier = Modifier.padding(start = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    text = "Start date",
                    color = Color.White)

                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    text = startDate,
                    color = Color.White)

            }

            Column(
                modifier = Modifier.padding(end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    text = "End date",
                    color = Color.White)

                Text(
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    text = endDate,
                    color = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun ComponentPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        Component(workoutLogbookComponentUIState = WorkoutLogbookComponentUIState())
    }

}