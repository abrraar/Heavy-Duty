package com.example.heavyduty.data.local.util

import com.example.heavyduty.R
import com.example.heavyduty.domain.model.util.BottomBarModel
import com.example.heavyduty.navigation.NavigationScreenNames

object BottomBarRepository {
    val navigationItemList = listOf(
        BottomBarModel(
            route = NavigationScreenNames.Tracker.route,
            text = R.string.bn_tracker,
            imageRes = R.drawable.tracker_icn
        ),
        BottomBarModel(
            route = NavigationScreenNames.Exercise.route,
            text = R.string.bn_exercise,
            imageRes = R.drawable.exercise_icn
        ),
        BottomBarModel(
            route = NavigationScreenNames.Profile.route,
            text = R.string.bn_profile,
            imageRes = R.drawable.profile_icn
        )

    )
}