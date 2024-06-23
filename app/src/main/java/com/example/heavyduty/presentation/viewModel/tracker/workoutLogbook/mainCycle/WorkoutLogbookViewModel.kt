package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleScreenUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.toCycleComponentUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import com.example.heavyduty.units.IntensityUnits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutLogbookViewModel
@Inject constructor(
    private val workoutLogbookOfflineRepository: WorkoutLogbookOfflineRepository
): ViewModel()
{
    // Cycle and Cycle Component
    private val _cycleScreenUIState = MutableStateFlow(CycleScreenUIState())
    val cycleUIState = _cycleScreenUIState.asStateFlow()

    private var cycleList: ArrayList<Cycle> = arrayListOf()
    private var cycleUIList: ArrayList<Pair<Cycle, CycleComponentUIState>> = arrayListOf()

    // Workout
    private val _workoutUIState = MutableStateFlow(WorkoutUIState())
    val workoutUIState = _workoutUIState.asStateFlow()

//-----------------------------Exercise and Exercise Component--------------------------------------
    private val _exerciseScreenUIState = MutableStateFlow(ExerciseScreenUIState())
    val exerciseScreenUIState = _exerciseScreenUIState.asStateFlow()
    init {
        getCycle()
    }
    private fun getCycle(){
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest{
                it -> cycleList = it as ArrayList<Cycle>

                // Set Base cycle
                cycleUIList.clear()
                for((index, c) in cycleList.withIndex()){
                    when(index){
                        // First cycle is always the base cycle
                        0 -> {
                            val baseCycle = c.cycleModel.toCycleComponentUIState()
                            baseCycle.baseCycle = true
                            cycleUIList.add(c to baseCycle)
                        }
                        else -> {
                            cycleUIList.add(c to c.cycleModel.toCycleComponentUIState())
                        }
                    }
                }

                val snapList = cycleUIList.toMutableStateList()
               _cycleScreenUIState.update { it.copy( listOfCycle = snapList ) }
               _workoutUIState.update { it.copy( listOfCycle = snapList ) }
               _exerciseScreenUIState.update { it.copy( listOfCycle = snapList ) }
            }
        }
    }
    fun cycleEvents(events: CycleEvents) {
        when(events){
            is CycleEvents.CycleSelected -> {
                _workoutUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex
                    )
                }
                _exerciseScreenUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex
                    )
                }
            }
            is CycleEvents.DeleteCycleClicked -> {
                viewModelScope.launch {
                    workoutLogbookOfflineRepository.deleteCycle(events.cycle)
                }
                getCycle()
            }
            is CycleEvents.OrderByCycle -> {
                if(events.order == "Ascending"){
                    val cycle = cycleUIList.reversed()
                    val snapCycle = cycle.toMutableStateList()
                    _cycleScreenUIState.update {
                        it.copy(
                           listOfCycle = snapCycle,
                            orderBy = "Ascending"
                        )
                    }
                    _workoutUIState.update {
                        it.copy(
                            listOfCycle = snapCycle,
                        )
                    }
                    _exerciseScreenUIState.update {
                        it.copy(
                            listOfCycle = snapCycle
                        )
                    }
                }
                else if(events.order == "Descending"){
                    val cycle = cycleUIList
                    val snapCycle = cycleUIList.toMutableStateList()
                    _cycleScreenUIState.update {
                        it.copy(
                            listOfCycle = snapCycle,
                            orderBy = "Descending"
                        )
                    }
                    _workoutUIState.update {
                        it.copy(
                            listOfCycle = snapCycle,
                        )
                    }
                    _exerciseScreenUIState.update {
                        it.copy(
                            listOfCycle = snapCycle,
                        )
                    }
                }
            }
        }
    }
    fun workoutEvents(events: WorkoutEvents){
        when(events){
            // Update the index position of the list and get the previous reps
            is WorkoutEvents.WorkoutSelected -> {
                _exerciseScreenUIState.update {
                    it.copy(
                        workoutIndex = events.workoutIndex
                    )
                }

                // Get previous reps when a workout is selected to display all the exercises
                // Binary Search Algo
                fun findElement(list: List<ExerciseModel>, target: String): Pair<HashMap<IntensityUnits, Int>, Double>? {
                    var result:  Pair<HashMap<IntensityUnits, Int>, Double>? = null
                    for(exercise in list){
                        if(exercise.exerciseName.compareTo(target) == 0){
                            result = Pair(exercise.value, exercise.weight)
                            break
                        }
                    } // Target not found
                    return result
                }

                // Variable
                val cycle = cycleUIList

                // Updating all previous reps
                for(exerciseModel in cycle[_exerciseScreenUIState.value.cycleIndex].second.listOfWorkout[_exerciseScreenUIState.value.workoutIndex].listOfExercise){

                    var workoutIndex = _exerciseScreenUIState.value.workoutIndex
                    var cycleIndex = _exerciseScreenUIState.value.cycleIndex
                    var response:  Pair<HashMap<IntensityUnits, Int>, Double>? = null
                    val exerciseName = exerciseModel.exerciseName
                    // If there are previous workout
                    if(workoutIndex != 0){
                        while(workoutIndex > 0){
                            workoutIndex -= 1
                            response = findElement(
                                list = cycle[_exerciseScreenUIState.value.cycleIndex].second.listOfWorkout[workoutIndex].listOfExercise,
                                target = exerciseModel.exerciseName
                            )
                            if(response != null){
                                // assign the value to previous rep
                                exerciseModel.previousReps.putAll(response.first)
                                exerciseModel.previousWeight = response.second
                                Log.i("response",response.toString())
                                break

                            }
                            else if(workoutIndex == 0)
                            {
                                while(cycleIndex != 0){
                                    cycleIndex -= 1
                                    workoutIndex = cycle[cycleIndex].first.cycleModel.listOfWorkout.size - 1
                                    while(workoutIndex >= 0){
                                        response = findElement(
                                            list = cycle[cycleIndex].second.listOfWorkout[workoutIndex].listOfExercise,
                                            target = exerciseModel.exerciseName
                                        )
                                        if(response != null){
                                            // assign the value to previous rep
                                            exerciseModel.previousReps.putAll(response.first)
                                            exerciseModel.previousWeight = response.second
                                            break
                                        }
                                        workoutIndex -= 1
                                    }
                                    if (response != null){
                                        break
                                    }
                                }
                                break
                            }
                        }
                    }else{
                        while(cycleIndex != 0){
                            cycleIndex -= 1
                            workoutIndex = cycle[cycleIndex].first.cycleModel.listOfWorkout.size - 1
                            while(workoutIndex >= 0){
                                Log.i("workout index", workoutIndex.toString())
                                response = findElement(
                                    list = cycle[cycleIndex].second.listOfWorkout[workoutIndex].listOfExercise,
                                    target = exerciseModel.exerciseName
                                )
                                if(response != null){
                                    // assign the value to previous rep
                                    exerciseModel.previousReps.putAll(response.first)
                                    exerciseModel.previousWeight = response.second
                                    Log.i("response",response.toString())
                                    break

                                }
                                workoutIndex -= 1
                            }
                            if (response != null){
                                break
                            }
                        }
                    }
                }

                if(_cycleScreenUIState.value.orderBy == "Ascending")
                {
                    val ascList = cycle.reversed()
                    _exerciseScreenUIState.update { it.copy( listOfCycle = ascList.toMutableStateList() ) }
                }else{
                    _exerciseScreenUIState.update { it.copy( listOfCycle = cycle.toMutableStateList()) }
                }



                // Search within the workout list

            }
            is WorkoutEvents.WorkoutDelete -> {
                val cycle = cycleList
                for ( workout in cycle[events.cycleNumber].cycleModel.listOfWorkout){
                    if(events.workoutModel.workoutNumber == workout.workoutNumber){
                        cycle[events.cycleNumber].cycleModel.listOfWorkout.remove(events.workoutModel)
                        break
                    }
                }
                viewModelScope.launch {
                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                }
                getCycle()
            }
            is WorkoutEvents.WorkoutDeleteClicked -> {
                when(events.isClicked){
                    true -> {
                        _workoutUIState.update {
                            it.copy(
                                deleteClicked = true,
                                workoutModel = events.workoutModel
                            )
                        }
                    }
                    false -> {
                        _workoutUIState.update {
                            it.copy(
                                deleteClicked = false
                            )
                        }
                    }
                }

            }
        }
    }
    fun exerciseEvents(events: ExerciseEvents){
        when(events){
            is ExerciseEvents.AddReps -> {

                var repsInInt = 0
                repsInInt = if(events.reps == "" || events.reps == "0"){ 0 } else { events.reps.toInt() }

                val cycle = cycleList

                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){
                            IntensityUnits.Positive -> {
                                cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Positive] = repsInInt
                                viewModelScope.launch {
                                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                }
                            }

                            IntensityUnits.Static -> {
                                // Remove Static
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Static) || events.exerciseModel.value.containsKey(IntensityUnits.Static)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Static] = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }
                            }

                            IntensityUnits.Negative -> {
                                // Remove Negative
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Negative) || events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Negative] = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Forced) ||  events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Forced] = repsInInt
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.PreExhaust -> {}
                        }
                    }
                }
            }
            is ExerciseEvents.AddIntensityComponent -> {
                val cycle = cycleList
                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){
                            IntensityUnits.Positive -> {
                                if (!events.exerciseModel.intensitySelected.contains(IntensityUnits.Positive)){
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.add(IntensityUnits.Positive)
                                }
                                viewModelScope.launch {
                                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                }
                            }

                            IntensityUnits.Static -> {
                                // Remove Static
                                if (!events.exerciseModel.intensitySelected.contains(IntensityUnits.Static)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.add(IntensityUnits.Static)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.Negative -> {
                                // Remove Negative
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Negative) || events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.add(IntensityUnits.Negative)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (!events.exerciseModel.intensitySelected.contains(IntensityUnits.Forced)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.add(IntensityUnits.Forced)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }
                                }

                            }

                            IntensityUnits.PreExhaust -> {}
                        }
                    }
                }
            }
            is ExerciseEvents.DeleteIntensityComponent -> {
                val cycle = cycleList
                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){
                            IntensityUnits.Positive -> {
                                cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.remove(
                                    IntensityUnits.Positive
                                )
                                viewModelScope.launch {
                                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                }
                                Log.i("delete intensity list",  cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.toString())
                            }

                            IntensityUnits.Static -> {
                                cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.remove(IntensityUnits.Static)
                                viewModelScope.launch {
                                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                }
                            }

                            IntensityUnits.Negative -> {

                                if (events.exerciseModel.intensitySelected.contains(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.remove(IntensityUnits.Negative)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (events.exerciseModel.intensitySelected.contains(IntensityUnits.Forced)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].intensitySelected.remove(IntensityUnits.Forced)
                                    viewModelScope.launch {
                                        workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                                    }

                                }

                            }

                            IntensityUnits.PreExhaust -> {}
                        }
                    }
                }
            }
            is ExerciseEvents.DeleteExercise -> {
                val cycle = cycleList
                for ( exercise in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.remove(events.exerciseModel)
                        break
                    }
                }
                viewModelScope.launch {
                    workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleNumber])
                }
            }
            is ExerciseEvents.AddWeight -> {
                var weightInDouble = 0.0
                weightInDouble = if(events.weight == "" || events.weight == "0"){ 0.0 } else { events.weight.toDouble() }
                val cycle = cycleList
                for (exercise in cycle[events.cycleIndex].cycleModel.listOfWorkout[events.workoutIndex].listOfExercise){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        cycle[events.cycleIndex].cycleModel.listOfWorkout[events.workoutIndex].listOfExercise[events.index].weight = weightInDouble
                        Log.i("exercise model", cycle[events.cycleIndex].cycleModel.listOfWorkout[events.workoutIndex].listOfExercise[events.index].exerciseName)
                        viewModelScope.launch {
                            workoutLogbookOfflineRepository.updateCycle(cycle[events.cycleIndex])
                        }
                    }
                }
            }
        }
    }

}
