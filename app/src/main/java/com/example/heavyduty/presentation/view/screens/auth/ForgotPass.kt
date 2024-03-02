package com.example.heavyduty.presentation.view.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@Composable
fun ForgotPass(){
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Forgot Pass")
    }
}



@Preview
@Composable
private fun ForgotPassPreview(){
    HeavyDutyTheme(dynamicColor = false) {
        ForgotPass()
    }
}