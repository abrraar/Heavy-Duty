package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData

import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.units.ExerciseList
import com.example.heavyduty.units.IntensityUnits


val ChestAndBack by lazy {
    arrayListOf(
        ExerciseModel(
            exerciseNumber = 1,
            exerciseName = ExerciseList.DUMBBELLFLYES.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0, IntensityUnits.Static to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.DUMBBELLFLYES.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 2,
            exerciseName = ExerciseList.INCLINEPRESS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.INCLINEPRESS.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 3,
            exerciseName = ExerciseList.STRAIGHTARMPULLDOWN.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.STRAIGHTARMPULLDOWN.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 4,
            exerciseName = ExerciseList.PALMSUPPULLDOWN.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.PALMSUPPULLDOWN.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 5,
            exerciseName = ExerciseList.DEADLIFTS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.DEADLIFTS.exerciseType,
        ),
    )
}


val ShouldersAndArms by lazy {
    arrayListOf(
        ExerciseModel(
            exerciseNumber = 1,
            exerciseName =  ExerciseList.DUMBBELLLATERALSRAISES.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.DUMBBELLLATERALSRAISES.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 2,
            exerciseName = ExerciseList.BENTOVERDUMBBELLLATERALS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.BENTOVERDUMBBELLLATERALS.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 3,
            exerciseName = ExerciseList.PALMSUPPULLDOWN.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.PALMSUPPULLDOWN.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 4,
            exerciseName = ExerciseList.TRICEPSPRESSDOWN.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.TRICEPSPRESSDOWN.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 5,
            exerciseName = ExerciseList.DIPS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.DIPS.exerciseType,
        )
    )
}

val LegsAndAbs by lazy {
    arrayListOf(
        ExerciseModel(
            exerciseNumber = 1,
            exerciseName = ExerciseList.LEGEXTENSTION.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.LEGEXTENSTION.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 2,
            exerciseName = ExerciseList.LEGPRESS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.LEGPRESS.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 3,
            exerciseName = ExerciseList.STANDINGCALVESRAISES.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.STANDINGCALVESRAISES.exerciseType,
        ),
        ExerciseModel(
            exerciseNumber = 4,
            exerciseName = ExerciseList.SITUPS.exerciseName,
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0,
            exerciseType = ExerciseList.SITUPS.exerciseType,
        )
    )
}



