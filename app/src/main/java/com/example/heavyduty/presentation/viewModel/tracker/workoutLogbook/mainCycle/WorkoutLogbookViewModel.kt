package com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.WorkoutLogbookOfflineRepository
import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.cycle.CycleScreenUIState
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.WorkoutScreenUIState
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
    // Cycle Screen
    private val _cycleScreenUIState = MutableStateFlow(CycleScreenUIState())
    val cycleUIState = _cycleScreenUIState.asStateFlow()

    // Cycle list
    private var cycleList: ArrayList<Cycle> = arrayListOf()

    // Workout Screen
    private val _workoutScreenUIState = MutableStateFlow(WorkoutScreenUIState())
    val workoutUIState = _workoutScreenUIState.asStateFlow()

    // Exercise Screen
    private val _exerciseScreenUIState = MutableStateFlow(ExerciseScreenUIState())
    val exerciseScreenUIState = _exerciseScreenUIState.asStateFlow()

    private fun getCycle(){
        viewModelScope.launch {
            workoutLogbookOfflineRepository.getAllCycle().collectLatest{
                cycleList.clear()
                cycleList.addAll(it)
                updateScreenUIState(cycleList = cycleList)
            }
        }
    }

    init {
        getCycle()
    }

    private fun updateScreenUIState(cycleList: ArrayList<Cycle>){
        val mutableStateList = cycleList.toMutableStateList()
        _cycleScreenUIState.update { it.copy( listOfCycle = mutableStateList ) }
        _workoutScreenUIState.update { it.copy( listOfCycle = mutableStateList ) }
        _exerciseScreenUIState.update { it.copy( listOfCycle = mutableStateList ) }
    }

    private fun updateCycle(
        isWeight: Boolean = false,
        exerciseModel: ExerciseModel,
        intensityUnits: IntensityUnits? = null,
        cycleList: ArrayList<Cycle>,
        cycleIndex: Int,
        workoutIndex: Int,
        exerciseIndex: Int )
    {
        if(isWeight){

            val weightDif = calculateDifference(
                newRecord = exerciseModel.weight,
                oldRecord = exerciseModel.previousWeight
            )

            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].totalDifference -= cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].weightDifference
            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].totalDifference += weightDif
            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].weightDifference = weightDif
        }
        else {
            val difference = calculateDifference(
                newRecord = exerciseModel.value[intensityUnits]?.toDouble()?:0.0,
                oldRecord = exerciseModel.previousReps[intensityUnits]?.toDouble()?:0.0)

            // Delete old difference from total difference
            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].totalDifference -= cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].intensityDifference[IntensityUnits.Positive]?:0
            // Add new difference to total difference
            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].totalDifference += difference
            //Set new difference
            cycleList[cycleIndex].cycleModel.listOfWorkout[workoutIndex].listOfExercise[exerciseIndex].intensityDifference[intensityUnits!!] = difference
        }

        // Update workout and cycleList progress for current cycleList
        updateWorkoutAndCycleProgress(cycleIndex = cycleIndex, cycleList = cycleList)

        // Update next cycleList (if available)
        val cycleSize = cycleList.size - 1
        if(cycleIndex < cycleSize){
            updateNextCycle(
                cycleIndex = cycleIndex,
                cycleList = cycleList,
                cycleSize = cycleSize,
                exerciseModel = exerciseModel,
                intensityUnits = intensityUnits
            )
        }
    }

    private fun calculateDifference(newRecord: Double, oldRecord: Double): Int {
        val difference = if(newRecord == 0.0 || oldRecord == 0.0){0}else{ Math.round((newRecord - oldRecord)/((oldRecord + newRecord)/2)*100).toInt() }
        return difference
    }

    // update for workout items
    private fun updateWorkoutAndCycleProgress(cycleIndex: Int, cycleList: ArrayList<Cycle>) {
        // Variable
        val cycleProgress: Int
        var workoutAvgTotal = 0.0
        var workoutCount = 0.0
        // Set overall progress
        cycleList[cycleIndex].cycleModel.listOfWorkout.forEach{
            workout ->
            val avg: Int
            var count = 0
            var total = 0
            workout.listOfExercise.forEach{
                exercise ->
                if(exercise.totalDifference != 0){
                    total += exercise.totalDifference
                    count += 1
                }
            }
            avg = Math.round(total.toDouble()/count).toInt()
            workout.overallProgress = avg
            workoutCount += 1
            workoutAvgTotal += workout.overallProgress
        }
        cycleProgress = Math.round(workoutAvgTotal/workoutCount).toInt()
        cycleList[cycleIndex].cycleModel.overallProgress = cycleProgress

        // Update Repository
        viewModelScope.launch {
            workoutLogbookOfflineRepository.updateCycle(cycleList[cycleIndex])
        }

        updateScreenUIState(cycleList)

    }

    private fun updateNextCycle(cycleIndex: Int, cycleList: ArrayList<Cycle>, cycleSize: Int, exerciseModel: ExerciseModel, intensityUnits: IntensityUnits?) {

        var ci = cycleIndex
        while(ci != cycleSize){
            ci += 1
            for(workoutModel in cycleList[ci].cycleModel.listOfWorkout) {
                for(exercise in workoutModel.listOfExercise){
                    if(exercise.exerciseName.compareTo(exerciseModel.exerciseName) == 0){
                        // Set previous rep
                        exercise.previousReps.putAll(exerciseModel.value)
                        exercise.previousWeight = exerciseModel.weight

                        if (intensityUnits == null){
                            // Calculate and set exercise difference
                            // Weight
                            val weightDif = calculateDifference(
                                newRecord = exercise.weight,
                                oldRecord = exercise.previousWeight
                            )
                            exercise.totalDifference -= exercise.weightDifference
                            exercise.totalDifference += weightDif
                            exercise.weightDifference = weightDif
                        }
                        else {
                            val repDif = calculateDifference(
                                newRecord = exercise.value[intensityUnits]!!.toDouble(),
                                oldRecord = exercise.previousReps[intensityUnits]!!.toDouble()
                            )
                            // Update total difference
                            exercise.totalDifference -= exercise.intensityDifference[intensityUnits]?:0
                            exercise.totalDifference += repDif
                            // Update difference
                            exercise.intensityDifference[intensityUnits] = repDif
                        }
                        updateWorkoutAndCycleProgress(cycleIndex = ci, cycleList = cycleList)
                        break
                    }
                }
            }
        }
    }

    fun cycleEvents(events: CycleEvents) {
        when(events){
            is CycleEvents.CycleSelected -> {
                _workoutScreenUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex,
                        baseCycle = events.baseCycle
                    )
                }
                _exerciseScreenUIState.update {
                    it.copy(
                        cycleIndex = events.cycleIndex,
                        baseCycle = events.baseCycle
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
                    _cycleScreenUIState.update {
                        it.copy(
                            orderBy = "Ascending",
                            reverseLayout = true
                        )
                    }
                }
                else if(events.order == "Descending"){
                    _cycleScreenUIState.update {
                        it.copy(
                            orderBy = "Descending",
                            reverseLayout = false
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
                        _workoutScreenUIState.update {
                            it.copy(
                                deleteClicked = true,
                                workoutModel = events.workoutModel
                            )
                        }
                    }
                    false -> {
                        _workoutScreenUIState.update {
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

                val repsInInt = if(events.reps == "" || events.reps == "0"){ 0 } else { events.reps.toInt() }

                val cycle = cycleList

                for ((index, exercise) in cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise.withIndex()){
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){
                        when(events.intensityUnit){

                            IntensityUnits.Positive -> {
                                // Add the data given from user
                                cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Positive] = repsInInt

                                // Update the current (and next) cycle
                                updateCycle(
                                    exerciseModel = exercise,
                                    intensityUnits = IntensityUnits.Positive,
                                    cycleIndex = events.cycleNumber,
                                    workoutIndex = events.workoutNumber,
                                    exerciseIndex = index,
                                    cycleList = cycle
                                )

                            }

                            IntensityUnits.Static -> {
                                // Remove Static
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Static) || events.exerciseModel.value.containsKey(IntensityUnits.Static)) {
                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Static] = repsInInt

                                    // Update the current (and next) cycle
                                    updateCycle(
                                        exerciseModel = exercise,
                                        intensityUnits = IntensityUnits.Static,
                                        cycleIndex = events.cycleNumber,
                                        workoutIndex = events.workoutNumber,
                                        exerciseIndex = index,
                                        cycleList = cycle
                                    )
                                }
                            }

                            IntensityUnits.Negative -> {
                                // Remove Negative
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Negative) || events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Negative] = repsInInt

                                    // Update the current (and next) cycle
                                    updateCycle(
                                        exerciseModel = exercise,
                                        intensityUnits = IntensityUnits.Negative,
                                        cycleIndex = events.cycleNumber,
                                        workoutIndex = events.workoutNumber,
                                        exerciseIndex = index,
                                        cycleList = cycle
                                    )
                                }

                            }

                            IntensityUnits.Forced -> {
                                // Remove Forced
                                if (!events.exerciseModel.value.containsKey(IntensityUnits.Forced) ||  events.exerciseModel.value.containsKey(IntensityUnits.Negative)) {

                                    cycle[events.cycleNumber].cycleModel.listOfWorkout[events.workoutNumber].listOfExercise[index].value[IntensityUnits.Forced] = repsInInt

                                    // Update the current (and next) cycle
                                    updateCycle(
                                        exerciseModel = exercise,
                                        intensityUnits = IntensityUnits.Forced,
                                        cycleIndex = events.cycleNumber,
                                        workoutIndex = events.workoutNumber,
                                        exerciseIndex = index,
                                        cycleList = cycle
                                    )
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

                // Get weight data from user
                val weightInDouble: Double = if(events.weight == "" || events.weight == "0"){ 0.0 } else { events.weight.toDouble() }

                // Search for exercise
                val cycle = cycleList
                for (exercise in cycle[events.cycleIndex].cycleModel.listOfWorkout[events.workoutIndex].listOfExercise)
                {
                    if(events.exerciseModel.exerciseNumber == exercise.exerciseNumber){

                        // Adding new weight
                        cycle[events.cycleIndex].cycleModel.listOfWorkout[events.workoutIndex].listOfExercise[events.index].weight = weightInDouble

                        updateCycle(
                            isWeight = true,
                            exerciseModel = exercise,
                            cycleIndex = events.cycleIndex,
                            workoutIndex = events.workoutIndex,
                            exerciseIndex = events.index,
                            cycleList = cycle
                        )

                    }
                }
            }
        }
    }
}
