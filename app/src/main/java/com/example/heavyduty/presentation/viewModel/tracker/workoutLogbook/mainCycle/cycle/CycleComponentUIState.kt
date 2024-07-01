package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle

import androidx.compose.ui.graphics.Color
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.presentation.view.theme.Green
import com.example.heavyduty.presentation.view.theme.Red

data class CycleComponentUIState(
    var baseCycle: Boolean = false,
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    var overallProgress: String = "",
    val color: Color = Color.Gray,
    val listOfWorkout: ArrayList<WorkoutModel> = arrayListOf()
)

fun CycleComponentUIState.toCycleModel(): CycleModel {
   return CycleModel(
        cycleName = name,
        startDate = startDate,
        endDate = endDate,
        overallProgress = overallProgress.toInt(),
        listOfWorkout = listOfWorkout
    )
}
fun CycleModel.toCycleComponentUIState(): CycleComponentUIState {
    val color =
        if (overallProgress > 0) { Green }
        else if (overallProgress == 0) { Color.Gray }
        else { Red }

    return CycleComponentUIState(
        name = cycleName,
        startDate = startDate,
        endDate = endDate,
        overallProgress = overallProgress.toString(),
        listOfWorkout = listOfWorkout,
        color = color
    )
}
