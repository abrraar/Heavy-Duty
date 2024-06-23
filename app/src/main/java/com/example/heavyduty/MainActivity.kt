package com.example.heavyduty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val config = LocalConfiguration.current
            HeavyDutyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = false,
    widthDp = 840,
    heightDp = 480)
@Composable
fun MainScreenPreview_LargeDevice() {
    HeavyDutyTheme(dynamicColor = false) {
        MainScreen()
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL
)
@Composable
fun MainScreenPreview_NormalDevice() {
    HeavyDutyTheme(dynamicColor = false) {
        MainScreen()
    }
}


