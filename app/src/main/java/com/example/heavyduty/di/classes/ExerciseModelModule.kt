package com.example.heavyduty.di.classes

import com.example.heavyduty.domain.model.tracker.workoutLogbook.ExerciseModel
import com.example.heavyduty.units.IntensityUnits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ExerciseModelModule
{
    @Singleton
    @Provides
    fun providesExerciseModel(): ExerciseModel {
        return ExerciseModel(
            exerciseName = "",
            exerciseNumber = 1,
            exerciseType = "",
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = 0,
            increasedRate = 0.0
        )
    }
}