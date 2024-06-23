package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.addCycle


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.prompts.Prompt
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.AddCycleEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle.ViewCycleUIState


@Composable
fun ViewCycle(
    viewCycleUIState: ViewCycleUIState,
    events: (AddCycleEvents) -> Unit,
    onNavigate: (Int) -> Unit,
    navHostController: NavHostController,
){
    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(1f)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(addClickable = {})
        LazyColumn(
            userScrollEnabled = false,
            modifier = Modifier.fillMaxHeight(1f)){
            itemsIndexed(listOfDefaultCycle){
                index, it ->
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .width(330.dp)
                        .height(160.dp)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f)) {
                        Text(
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            text = it.cycleName,
                            color = Color.White)

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(1f))
                    {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .background(color = Green)
                                .clickable(onClick = {
                                    events(AddCycleEvents.UseCycleClicked("cycle",true, it.cycleName))
                                })
                                .weight(1f)
                                .height(50.dp))
                        {
                            Text(
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                text = "Use Cycle",
                                color = Color.White)
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .background(
                                    color = Color.White.copy(alpha = 0.3f)
                                )
                                .clickable(onClick = {
                                    Log.i("Cycle Index from Cycle", index.toString())
                                    events(AddCycleEvents.CycleSelected(index))
                                    onNavigate.invoke(index)
                                })
                                .weight(1f)
                                .height(50.dp))
                        {
                            Text(
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                text = "View Workouts",
                                color = Color.White)
                        }
                    }
                }
            }
        }
    }
    if (viewCycleUIState.useCycle){
        Prompt(
            titleText = "Add Cycle",
            message = "Do you want to use\n" + viewCycleUIState.cycleName,
            onConfirm = {
                events(AddCycleEvents.ConfirmClicked(viewCycleUIState.cycleName))
                navHostController.navigate(NavigationScreenNames.WorkoutLogbook.route) {
                    popUpTo(NavigationScreenNames.ViewCycle.route) {
                        inclusive = true
                    }
                }
            },
            onCancel = {
                events(AddCycleEvents.UseCycleClicked("cycle",false,""))
            }
        )
    }
}



@Preview
@Composable
private fun ViewCyclePreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ViewCycle(
            viewCycleUIState = ViewCycleUIState(),
            events = {},
            navHostController = rememberNavController(),
            onNavigate = {})
    }
}