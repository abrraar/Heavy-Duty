package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.cycle

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
import com.example.heavyduty.presentation.view.theme.BrightGreen
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.Red
import com.example.heavyduty.presentation.view.theme.baseCycle
import com.example.heavyduty.presentation.view.theme.dimens
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.CycleEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState

@Composable
fun CycleComponent(
    modifier: Modifier = Modifier,
    cycleNum: Int = 0,
    cycle: Cycle,
    events: (CycleEvents) -> Unit,
    cycleComponentUIState: CycleComponentUIState,
){
    var deleteCycle by remember { mutableStateOf(false) }
    if (deleteCycle){
        Prompt(
            titleText = "Delete Cycle",
            message = "Do you want to delete ${cycleComponentUIState.name} ?",
            onCancel = {
                deleteCycle = false
            },
            onConfirm = {
                deleteCycle = false
                events(CycleEvents.DeleteCycleClicked(cycle))
            }
        )
    }

    Column(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .width(MaterialTheme.dimens.cardWidth)
            .height(MaterialTheme.dimens.cardHeight)
            .background(
                color = if(cycleComponentUIState.baseCycle){ baseCycle } else { cycleComponentUIState.color },
                shape = RoundedCornerShape(20.dp)),
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
                    .clickable(onClick = { deleteCycle = true }),
                tint = Color.White
            )

        }

        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                style = MaterialTheme.typography.titleSmall,
                text = when(cycleNum){
                    1 -> "1st Cycle"
                    2 -> "2nd Cycle"
                    3 -> "3rd Cycle"
                    else -> "${cycleNum}th Cycle"
                    },
                color = Color.White)

            Text(
                modifier = Modifier.padding(top = 5.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = cycleComponentUIState.name,
                color = Color.White)

            Spacer(modifier = Modifier.padding(bottom = 20.dp))

            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                text = if(cycleComponentUIState.baseCycle){"Base Cycle"}else{
                    if(cycleComponentUIState.overallProgress == "0.0"){"cycle on progress"}else{"Overall Progress"}
                },
                color = Color.White)

            Text(
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                text = if(cycleComponentUIState.baseCycle){"${cycleComponentUIState.overallProgress}%"}
                else{ "${cycleComponentUIState.overallProgress}%"},
                color = if(cycleComponentUIState.baseCycle){
                    Color.White
                }
                else{
                    when(cycleComponentUIState.color) {
                        Green -> BrightGreen
                        Red -> Color.Red
                        else -> Color.White
                    }
                }
            )
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
                    text = cycleComponentUIState.startDate,
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
                    text = cycleComponentUIState.endDate,
                    color = Color.White)
            }
        }
    }
}

@Preview
@Composable
private fun ComponentPreview(){
    CycleComponent(
        events = {},
        cycleComponentUIState = CycleComponentUIState(),
        cycle = Cycle(
            cycleModel = CycleModel(
                cycleName = "",
                listOfWorkout = arrayListOf(),
                startDate = "",
                endDate = "",
                overallProgress = 0
            )
        )
    )
}