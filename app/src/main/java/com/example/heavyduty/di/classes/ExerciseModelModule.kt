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
            exerciseNumber = 1,
            exerciseName = "",
            exerciseType = "",
            weight = 0.0,
            intensitySelected = arrayListOf(IntensityUnits.Positive),
            value = hashMapOf(IntensityUnits.Positive to 0),
            previousReps = hashMapOf(IntensityUnits.Positive to 0),
            previousWeight = 0.0
        )
    }
}