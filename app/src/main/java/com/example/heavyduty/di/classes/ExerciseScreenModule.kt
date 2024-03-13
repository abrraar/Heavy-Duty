package com.example.heavyduty.di.classes

import com.example.heavyduty.domain.model.tracker.workoutLogbook.Cycle
import com.example.heavyduty.presentation.viewModel.tracker.workoutLogbook.mainCycle.workout.exercise.screen.ExerciseScreenUIState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ExerciseScreenModule {
    @Singleton
    @Provides
    fun providesExerciseScreen(): ExerciseScreenUIState
    {
        return ExerciseScreenUIState()
    }

}