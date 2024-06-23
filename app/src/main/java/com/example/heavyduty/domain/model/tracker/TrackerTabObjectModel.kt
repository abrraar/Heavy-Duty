package com.example.heavyduty.domain.model.tracker

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.heavyduty.navigation.NavigationScreenNames

data class TrackerTabObjectModel(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)
