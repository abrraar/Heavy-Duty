package com.example.heavyduty.presentation.viewModel.tracker.bodyComposition.main

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntryModel


data class BodyCompositionUIState(

    // Graph state,
    var pageState: Int = 4,
    var model: ChartEntryModel? = null,
    var date: AxisValueFormatter<AxisPosition.Horizontal.Bottom>? = null

)