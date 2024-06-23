package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData

import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.units.ExerciseList
import com.example.heavyduty.units.IntensityUnits

val exerciseOne by lazy {
        arrayListOf(
            ExerciseModel(
                exerciseNumber = 1,
                exerciseName = ExerciseList.SQUATS.exerciseName,
                exerciseType = ExerciseList.SQUATS.exerciseType,
                weight = 0.0,
                intensitySelected = arrayListOf(IntensityUnits.Positive),
                value = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
                previousReps = hashMapOf(
                    IntensityUnits.Positive to 0,
                    IntensityUnits.Static to 0,
                    IntensityUnits.Negative to 0,
                    IntensityUnits.Forced to 0,
                    IntensityUnits.PreExhaust to 0
                ),
                previousWeight = 0.0
            ),
            ExerciseModel(
                exerciseNumber = 2,
                exerciseName = ExerciseList.PALMSUPPULLDOWN.exerciseName,
                exerciseType = ExerciseList.PALMSUPPULLDOWN.exerciseType,
                weight = 0.0,
                intensitySelected = arrayListOf(IntensityUnits.Positive),
                value = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
                previousReps = hashMapOf(
                    IntensityUnits.Positive to 0,
                    IntensityUnits.Static to 0,
                    IntensityUnits.Negative to 0,
                    IntensityUnits.Forced to 0,
                    IntensityUnits.PreExhaust to 0
                ),
                previousWeight = 0.0
            ),
            ExerciseModel(
                exerciseNumber = 3,
                exerciseName = ExerciseList.DIPS.exerciseName,
                exerciseType = ExerciseList.DIPS.exerciseType,
                weight = 0.0,
                intensitySelected = arrayListOf(IntensityUnits.Positive),
                value = hashMapOf(
                    IntensityUnits.Positive to 0,
                    IntensityUnits.Static to 0,
                    IntensityUnits.Negative to 0,
                    IntensityUnits.Forced to 0,
                    IntensityUnits.PreExhaust to 0
                ),
                previousReps = hashMapOf(
                    IntensityUnits.Positive to 0,
                    IntensityUnits.Static to 0,
                    IntensityUnits.Negative to 0,
                    IntensityUnits.Forced to 0,
                    IntensityUnits.PreExhaust to 0
                ),
                previousWeight = 0.0,
            )
        )
    }

    val exerciseTwo by lazy { arrayListOf(
        ExerciseModel(
            exerciseNumber = 1,
            exerciseName = ExerciseList.DEADLIFTS.exerciseName,
            exerciseType = ExerciseList.DEADLIFTS.exerciseType,
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousReps = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousWeight = 0.0
        ),
        ExerciseModel(
            exerciseNumber = 2,
            exerciseName = ExerciseList.PRESSBEHINDTHENECK.exerciseName,
            exerciseType =  ExerciseList.PRESSBEHINDTHENECK.exerciseType,
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousReps = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousWeight = 0.0
        ),
        ExerciseModel(
            exerciseNumber = 3,
            exerciseName =  ExerciseList.STANDINGCALVESRAISES.exerciseName,
            exerciseType = ExerciseList.STANDINGCALVESRAISES.exerciseType,
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousReps = hashMapOf(
                IntensityUnits.Positive to 0,
                IntensityUnits.Static to 0,
                IntensityUnits.Negative to 0,
                IntensityUnits.Forced to 0,
                IntensityUnits.PreExhaust to 0
            ),
            previousWeight = 0.0
        )
    ) }
