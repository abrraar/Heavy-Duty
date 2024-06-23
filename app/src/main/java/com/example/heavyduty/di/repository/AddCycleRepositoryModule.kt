package com.example.heavyduty.di.repository


import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleDAO
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleOfflineRepository
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AddCycleRepositoryModule {

    @Singleton
    @Provides
    fun providesAddCycleRepository(addCycleDAO: AddCycleDAO): AddCycleRepository {
        return AddCycleOfflineRepository(addCycleDAO)
    }
}