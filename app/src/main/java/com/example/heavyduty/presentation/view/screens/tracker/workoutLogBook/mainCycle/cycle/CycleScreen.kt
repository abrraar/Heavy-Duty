package com.example.heavyduty.presentation.view.screens.tracker.workoutLogBook.mainCycle.cycle

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.util.searchBars.SearchBar
import com.example.heavyduty.presentation.view.theme.ScreenBackgroundColor
import com.example.heavyduty.presentation.view.theme.dimens
import com.example.heavyduty.presentation.view.util.filter.Filter
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.CycleEvents
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleScreenUIState


@Composable
fun CycleScreen(
    events: (CycleEvents) -> Unit,
    cycleScreenUIState: CycleScreenUIState,
    navHostController: NavHostController
){
    val context = LocalContext.current
    var filterClicked by remember { mutableStateOf(false) }
    var searchBar by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(MaterialTheme.dimens.graphHeight)
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = "Data not available",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground)
        }
        SearchBar(
            value = searchBar,
            onValueChange = {searchBar = it },
            filterClickable = { filterClicked = !filterClicked },
            addClickable = { navHostController.navigate(NavigationScreenNames.ViewRoute.route)
        })
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(ScreenBackgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(filterClicked){
                if(cycleScreenUIState.listOfCycle.size >= 2){
                    Filter(
                        showOrderBy = true,
                        orderByObservable = cycleScreenUIState.orderBy,
                        orderByOnSelectedCategoryChange = {
                            events(CycleEvents.OrderByCycle(it))
                        }
                    )
                }
                else
                {
                    Toast.makeText(context, "Need atleast 2 cycles to use filter", Toast.LENGTH_SHORT).show()
                }

            }
            if (cycleScreenUIState.listOfCycle.isEmpty())
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight(1f)
                ){
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "Click on \"+\" to add cycle"
                    )
                }
            }
            else
            {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    itemsIndexed(
                        items = cycleScreenUIState.listOfCycle,
                        key = { index, _ -> index.toString() }
                    )
                    {
                    index, cycleComponentUIState ->
                        val cycleNum = index + 1
                        CycleComponent(
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp)
                                .clickable {
                                    events(CycleEvents.CycleSelected(index))
                                    navHostController.navigate(
                                        NavigationScreenNames.WorkoutLogbookWorkout.route
                                    )
                                },
                            cycleNum = cycleNum,
                            events = events,
                            cycle = cycleComponentUIState.first,
                            cycleComponentUIState = cycleComponentUIState.second
                        )
                    }
                }
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WorkoutLogBookScreenPreview(){
    CycleScreen(
        events = {},
        cycleScreenUIState = CycleScreenUIState(),
        rememberNavController())
}

