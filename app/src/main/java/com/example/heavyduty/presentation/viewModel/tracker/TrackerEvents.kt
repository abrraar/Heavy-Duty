package com.example.heavyduty.presentation.viewModel.tracker

import androidx.compose.material3.TabPosition

sealed interface TrackerEvents {
    data class TabClick(val route: String): TrackerEvents
}