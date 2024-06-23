package com.example.heavyduty.data.local.tracker

import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.TrackerTabObjectModel
import com.example.heavyduty.navigation.NavigationScreenNames


object TrackerTab
{
    val tabResource = listOf(
        TrackerTabObjectModel(
            route = NavigationScreenNames.WorkoutLogbookRoute.route,
            title = R.string.workout_logbook,
            icon = R.drawable.workout_logbook_icn
        ),
        TrackerTabObjectModel(
            route = NavigationScreenNames.BodyCompositionRoute.route,
            title = R.string.body_composition,
            icon = R.drawable.biceps_icn
        )
    )
}