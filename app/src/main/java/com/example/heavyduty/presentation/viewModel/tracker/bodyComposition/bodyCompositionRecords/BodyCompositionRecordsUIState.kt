package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords

import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight

data class BodyCompositionRecordsUIState(
    val deleteTitleText: Int = R.string.weight,
    val recordDelete: Boolean = false,
    val item: Any? = null,

    val recordList: List<Any> = listOf(),
    val weightList: List<Any> = listOf(),
    val heightList: List<Any> = listOf(),
    val bodyfatList: List<Any> = listOf(),
    val musclemassList: List<Any> = listOf(),

    )