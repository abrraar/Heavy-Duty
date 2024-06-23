package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleOfflineRepository
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCycleViewModel
@Inject constructor(
    private val addCycleOfflineRepository: AddCycleOfflineRepository
): ViewModel()
{
    private val _cycleState = MutableStateFlow(ViewCycleUIState())
    val cycleState = _cycleState.asStateFlow()

    private val _workoutState = MutableStateFlow(ViewWorkoutUIState())
    val workoutState = _workoutState.asStateFlow()

    private val _exerciseState = MutableStateFlow(ViewExerciseUIState())
    val exerciseState = _exerciseState.asStateFlow()

    private fun getCycleIndex(index: Int) {
        _exerciseState.getAndUpdate {
            it.copy(
                cycleIndexSelected = index
            )
        }
    }

    private fun enterCycle(cycleName: String) {
        if (cycleName == "Beginner's\nCycle"){
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = listOfDefaultCycle[0]
                    )
                )
            }
        }
        if (cycleName == "The Ideal Principled\nCycle"){
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = listOfDefaultCycle[1]
                    )
                )
            }
        }

        if (cycleName == "Advanced Consolidated\nCycle"){
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = listOfDefaultCycle[2]
                    )
                )
            }
            Log.i("Insert", "Data inserted")
        }
    }

    fun onWorkoutEvents(events: AddCycleEvents) {
        when(events){
            is AddCycleEvents.CycleSelected ->
               getCycleIndex(events.cycleIndex)

            is AddCycleEvents.UseCycleClicked -> {
                when(events.screen){
                    "cycle"->{
                        _cycleState.update {
                        it.copy(
                            useCycle = events.clicked,
                            cycleName = events.cycleName
                        )
                    }}
                    "workout"->{
                        _workoutState.update {
                            it.copy(
                                useCycle = events.clicked,
                                cycleName = events.cycleName
                            )
                        }
                    }
                    "exercise"->{
                        _exerciseState.update {
                            it.copy(
                                useCycle = events.clicked,
                                cycleName = events.cycleName
                            )
                        }
                    }
                }

            }

            is AddCycleEvents.ConfirmClicked -> {
                enterCycle(events.cycleName)
            }
        }
    }
}