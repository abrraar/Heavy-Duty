package com.example.heavyduty.presentation.view.screens.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.data.local.tracker.TrackerTab
import com.example.heavyduty.domain.model.tracker.TrackerTabObjectModel
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.navigation.trackerTabNavigation
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.viewModel.tracker.TrackerUIState


@Composable
fun TrackerScreen(
    navController: NavHostController = rememberNavController()
)
{
    Column {
        CustomTabBar(navController = navController , trackerUIState = TrackerUIState())
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
               ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top)
        {
            NavHost(
                navController = navController,
                startDestination =  NavigationScreenNames.TrackerScreen.route )
            {
                trackerTabNavigation()
            }
        }
    }
}

@Composable
private fun CustomTabBar(
    navController: NavController,
    trackerUIState: TrackerUIState
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    TabRow(
        selectedTabIndex = trackerUIState.selectedItemIndex,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        TrackerTab.tabResource.forEach{ tabObject ->
            CustomTab(
                currentDestination = currentDestination,
                tabObject = tabObject,
                navController = navController)
        }
    }
}


@Composable
private fun CustomTab(
    currentDestination: NavDestination?,
    tabObject: TrackerTabObjectModel,
    navController: NavController,
){
    val selected = currentDestination?.hierarchy?.any {it.route == tabObject.route} == true
    val colorChange =  if (currentDestination?.hierarchy?.any {it.route == tabObject.route} == true)
    {MaterialTheme.colorScheme.primary}else{MaterialTheme.colorScheme.onPrimary}

    Tab(
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
        selected = selected,
        onClick = { navController.navigate(tabObject.route) },
        text = { Text(
            text = stringResource(id = tabObject.title),
            color = colorChange,
            style = MaterialTheme.typography.labelSmall)
        },
        icon = { Icon(
            painter = painterResource(id = tabObject.icon),
            tint = colorChange,
            contentDescription = stringResource(id = tabObject.title)
        )
        }
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TrackerScreenPreview(){

    HeavyDutyTheme(dynamicColor = false, darkTheme = false) {
        TrackerScreen()
    }

}