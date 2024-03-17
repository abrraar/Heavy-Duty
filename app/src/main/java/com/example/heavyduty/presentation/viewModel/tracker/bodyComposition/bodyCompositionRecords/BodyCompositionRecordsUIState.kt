package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.bodyCompositionRecords

import com.example.heavyduty.domain.model.tracker.bodyComposition.BodyFat
import com.example.heavyduty.domain.model.tracker.bodyComposition.Height
import com.example.heavyduty.domain.model.tracker.bodyComposition.MuscleMass
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight

data class BodyCompositionRecordsUIState (
    val recordList: ArrayList<Any> = arrayListOf(),
    val bodyFatList: ArrayList<BodyFat> = arrayListOf(),
    val weightList: ArrayList<Weight> = arrayListOf(),
    val heightList: ArrayList<Height> = arrayListOf(),
    val bodyfatList: ArrayList<BodyFat> = arrayListOf(),
    val musclemassList: ArrayList<MuscleMass> = arrayListOf()
    )