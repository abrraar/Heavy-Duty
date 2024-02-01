package com.example.heavyduty.domain.model.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomBarModel (
    val route: String,
    @StringRes val text: Int,
    @DrawableRes val imageRes: Int
)
