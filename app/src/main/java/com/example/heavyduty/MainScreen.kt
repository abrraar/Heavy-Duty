package com.example.heavyduty


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.HeavyDutyNavigation
import com.example.heavyduty.navigation.NavigationScreenNames
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.bottomBar.BottomBar
import com.example.heavyduty.presentation.view.util.topAppBar.customTopAppBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route ?: NavigationScreenNames.AppScreen.route

    Scaffold (

        bottomBar = { BottomBar(navController = navController) }
    )
    { _ ->
        Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f))
        {
            HeavyDutyNavigation(navController = navController)
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun MainScreenPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        MainScreen()
    }
}

