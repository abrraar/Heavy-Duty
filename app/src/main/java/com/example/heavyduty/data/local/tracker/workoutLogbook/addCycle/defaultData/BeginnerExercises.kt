package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData

import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.units.ExerciseList
import com.example.heavyduty.units.IntensityUnits


val beginnerExercise by lazy {
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
                exerciseName = ExerciseList.BARBELLROWS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.BARBELLROWS.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 3,
                exerciseName = ExerciseList.FLATBENCHPRESS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.FLATBENCHPRESS.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 4,
                exerciseName = ExerciseList.PRESSBEHINDTHENECK.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.PRESSBEHINDTHENECK.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 5,
                exerciseName = ExerciseList.DEADLIFTS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.DEADLIFTS.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 6,
                exerciseName = ExerciseList.STANDINGBARBELLCURLS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.STANDINGBARBELLCURLS.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 7,
                exerciseName = ExerciseList.STANDINGCALVESRAISES.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType = ExerciseList.STANDINGCALVESRAISES.exerciseType,
            ),
            ExerciseModel(
                exerciseNumber = 8,
                exerciseName = ExerciseList.SITUPS.exerciseName,
                value = hashMapOf(IntensityUnits.Positive to 0),
                previousReps = 0,
                increasedRate = 0.0,
                exerciseType =  ExerciseList.SITUPS.exerciseType,
            ),
        )
    }
