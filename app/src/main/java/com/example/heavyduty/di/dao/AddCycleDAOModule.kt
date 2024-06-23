package com.example.heavyduty.di.dao

import com.example.heavyduty.data.local.HeavyDutyDataBase
import com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.AddCycleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AddCycleDAOModule {
    @Singleton
    @Provides
    fun providesAddCycleDAO(heavyDutyDatabase: HeavyDutyDataBase): AddCycleDAO {
        return heavyDutyDatabase.addCycleDAO()
    }

}