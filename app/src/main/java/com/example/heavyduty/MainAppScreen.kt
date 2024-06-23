package com.example.heavyduty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.AppScreenNavigation
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import com.example.heavyduty.presentation.view.util.bottomBar.BottomBar

@Composable
fun MainAppScreen(navHostController: NavHostController = rememberNavController()){
    Scaffold (
        bottomBar = { BottomBar(navController = navHostController) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
                .fillMaxWidth(1f)
                .fillMaxHeight(1f))
        {
            AppScreenNavigation(navHostController)
        }
    }
}

@Preview
@Composable
private fun MainAppScreenPreview(){
    MainAppScreen()
}