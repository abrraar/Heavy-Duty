package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData

import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.units.ExerciseList
import com.example.heavyduty.units.IntensityUnits

val exerciseOne by lazy {
        arrayListOf(
            ExerciseModel(
                exerciseNumber = 1,
                exerciseName = ExerciseList.SQUATS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.SQUATS.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 2,
                exerciseName = ExerciseList.PALMSUPPULLDOWN.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.PALMSUPPULLDOWN.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 3,
                exerciseName = ExerciseList.DIPS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.DIPS.exerciseType,
            )
        )
    }

    val exerciseTwo by lazy { arrayListOf(
        ExerciseModel(
            exerciseNumber = 1,
            exerciseName = ExerciseList.DEADLIFTS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.DEADLIFTS.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 2,
            exerciseName = ExerciseList.PRESSBEHINDTHENECK.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType =  ExerciseList.PRESSBEHINDTHENECK.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 3,
            exerciseName =  ExerciseList.STANDINGCALVESRAISES.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.STANDINGCALVESRAISES.exerciseType,
        )
    ) }
