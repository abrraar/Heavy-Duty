package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.addCycle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleOfflineRepository
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData.listOfDefaultCycle
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
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
    private val addCycleOfflineRepository: AddCycleOfflineRepository,
    private val workoutLogbookOfflineRepository: WorkoutLogbookOfflineRepository
): ViewModel()
{
    private val _cycleState = MutableStateFlow(ViewCycleUIState())
    val cycleState = _cycleState.asStateFlow()

    private val _workoutState = MutableStateFlow(ViewWorkoutUIState())
    val workoutState = _workoutState.asStateFlow()

    private val _exerciseState = MutableStateFlow(ViewExerciseUIState())
    val exerciseState = _exerciseState.asStateFlow()

    private val cycleList: ArrayList<Cycle> = arrayListOf()

    init {
        getAllCycle()
    }

    private fun getAllCycle(){
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest {
                cycleList.addAll(it)
            }
        }
    }

    private fun getPreviousReps(cycleModel: CycleModel): CycleModel{
        if(cycleList.size != 0){
            for(cycle in cycleList.reversed()){
                for(workout in cycle.cycleModel.listOfWorkout){
                    for(exercise in workout.listOfExercise){
                        for (defaultWorkout in cycleModel.listOfWorkout){
                            for (defaultExercise in defaultWorkout.listOfExercise){
                                if(exercise.exerciseName.compareTo(defaultExercise.exerciseName) == 0){
                                    defaultExercise.previousReps.putAll(exercise.value)
                                    defaultExercise.previousWeight = exercise.weight
                                    break
                                }
                            }
                        }
                    }
                }
            }
        }
        return cycleModel
    }
    private fun enterCycle(cycleName: String) {
        if (cycleName == "Beginner's\nCycle"){
            val beginnerCycle = getPreviousReps(listOfDefaultCycle[0])
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = beginnerCycle
                    )
                )
            }
        }
        if (cycleName == "The Ideal Principled\nCycle"){
            val idealCycle = getPreviousReps(listOfDefaultCycle[1])
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = idealCycle
                    )
                )
            }
        }

        if (cycleName == "Advanced Consolidated\nCycle"){
            val advanceCycle = getPreviousReps(listOfDefaultCycle[2])
            viewModelScope.launch {
                addCycleOfflineRepository.insertCycle(
                    cycle = Cycle(
                        cycleModel = advanceCycle
                    )
                )
            }
            Log.i("Insert", "Data inserted")
        }
    }

    private fun getCycleIndex(index: Int) {
        _exerciseState.getAndUpdate {
            it.copy(
                cycleIndexSelected = index
            )
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