package com.example.heavyduty.domain.model.tracker

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TrackerTabObjectModel(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)
