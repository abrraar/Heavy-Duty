package com.example.heavyduty


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.heavyduty.navigation.HeavyDutyNavigation
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme


@Composable
fun MainScreen(navHostController: NavHostController = rememberNavController()){
    Column(
        modifier = Modifier
            .padding()
            .fillMaxWidth(1f)
            .fillMaxHeight(1f))
    {
        HeavyDutyNavigation(navHostController = navHostController)
    }
}

@Preview(apiLevel = 33)
@Composable
private fun MainScreenPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        MainScreen()
    }
}

