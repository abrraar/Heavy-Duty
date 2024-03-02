package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModel


data class BodyCompositionUIState(

    //ScreenState
    var isSelected: Boolean = false,

    // Graph
    var model: ChartEntryModel? = null,
    var date: AxisValueFormatter<AxisPosition.Horizontal.Bottom>? = null,

    // Graph Status
    val pageState: Int = 4,
    var value: String = "",
    var unit: String = "",
    var dateSelected: Int = 0,
    var totalRecords: Int = 0,

    //Body
    var startRecordISSelected: Boolean = false,
    var endRecordIsSelected: Boolean = false,

)