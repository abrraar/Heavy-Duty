package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.heavyduty.R
import com.example.heavyduty.domain.model.tracker.bodyComposition.Weight
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModel


data class BodyCompositionUIState(

    //ScreenState
    var isSelected: Boolean = false,

    // Graph
    val model: ChartEntryModel? = null,
    val date: AxisValueFormatter<AxisPosition.Horizontal.Bottom>? = null,


    // Graph Status
    val title: Int = R.string.weight,
    val rightButton: Boolean = true,
    val leftButton: Boolean = false,
    var value: String = "",
    var unit: String = "",
    var dateSelected: Int = 0,
    var totalRecords: Int = 0,

    //Body
    var startRecordISSelected: Boolean = false,
    var endRecordIsSelected: Boolean = false,

    )