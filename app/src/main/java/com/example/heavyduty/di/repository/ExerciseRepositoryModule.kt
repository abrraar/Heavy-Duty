package com.example.heavyduty.di.repository

import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.workout.exercise.ExerciseDAO
import com.example.heavyduty.data.local.tracker.workoutLogbook.mainCycle.workout.exercise.ExerciseOfflineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ExerciseRepositoryModule {
    @Singleton
    @Provides
    fun providesExerciseRepository(exerciseDAO: ExerciseDAO): ExerciseOfflineRepository {
        return ExerciseOfflineRepository(exerciseDAO)
    }
}