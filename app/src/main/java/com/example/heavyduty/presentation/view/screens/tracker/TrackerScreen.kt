package com.example.heavyduty.presentation.view.screens.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.data.local.tracker.TrackerTab
import com.example.heavyduty.domain.model.tracker.TrackerTabObjectModel
import com.example.heavyduty.navigation.trackerScreenNavGraph.TrackerScreenNavigation
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.viewModel.tracker.TrackerViewModel

@Composable
fun TrackerScreen(
    navController: NavHostController = rememberNavController()
)
{
    Column(modifier = Modifier.padding()) {
        CustomTabBar(
            navController = navController)
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top)
        {
            TrackerScreenNavigation(navController)
        }
    }
}

@Composable
private fun CustomTabBar(
    navController: NavController,
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    // Getting current route name
    val route = navController.currentDestination?.route.toString()


    val trackerViewModel = viewModel<TrackerViewModel>()
    trackerViewModel.tabPosition(route)
    val trackerUIState = trackerViewModel.state.collectAsState()

    TabRow(
        selectedTabIndex = trackerUIState.value.selectedItemIndex,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        TrackerTab.tabResource.forEach{ trackerTabObjectModel ->

        CustomTab(
            onClick = {
                trackerViewModel.tabPosition(route)
                navController.navigate(trackerTabObjectModel.route)
            },
            currentDestination = currentDestination,
            trackerTabObjectModel = trackerTabObjectModel)

        }
    }
}


@Composable
private fun CustomTab(
    onClick: () -> Unit,
    currentDestination: NavDestination?,
    trackerTabObjectModel: TrackerTabObjectModel)
{
    val selected = currentDestination?.hierarchy?.any { it.route == trackerTabObjectModel.route } == true
    val colorChange =  if (selected) { MaterialTheme.colorScheme.primary } else { MaterialTheme.colorScheme.onPrimary }

    Tab(
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
        selected = selected,
        onClick = onClick,
        text = {
            Text(
                text = stringResource(id = trackerTabObjectModel.title),
                color = colorChange,
                style = MaterialTheme.typography.labelSmall)
        },
        icon = {
            Icon(
                painter = painterResource(id = trackerTabObjectModel.icon),
                tint = colorChange,
                contentDescription = stringResource(id = trackerTabObjectModel.title)
            )
        }
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TrackerScreenPreview(){

    HeavyDutyTheme(dynamicColor = false, darkTheme = false) {
        TrackerScreen(navController = rememberNavController())
    }

}