package com.example.heavyduty.presentation.view.util.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.heavyduty.data.local.util.BottomBarRepository.navigationItemList
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.domain.model.util.BottomBarModel
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@Composable
fun BottomBar(navController: NavHostController)
{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row (
        modifier = Modifier
            .height(80.dp)
            .background(color = Color.Black)

            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        navigationItemList.forEach{
            item -> AddItem(data = item, currentDestination = currentDestination, navController = navController )
        }
    }
}

@Composable
private fun AddItem(
    data: BottomBarModel,
    currentDestination: NavDestination?,
    navController: NavController
){
    val selected = currentDestination?.hierarchy?.any {it.route == data.route} == true
    val colorChange = if (selected) Color.White else Color.White.copy(alpha = 0.4f)

    Box(
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate(data.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                })
    ){
        Column(
            modifier = Modifier
                .width(100.dp)
                .height(60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center ){
                Icon(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = stringResource(id = data.text),
                    tint = colorChange)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = data.text),
                    color = colorChange,
                    style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BottomNavigationMenuPreview(){
    val nav = rememberNavController()
    HeavyDutyTheme(dynamicColor = false){
        BottomBar(navController = nav)
    }


}
