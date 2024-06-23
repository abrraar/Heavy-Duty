package com.example.heavyduty.di.repository

import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionDAO
import com.example.heavyduty.data.local.tracker.bodyComposition.addBodyComposition.AddBodyCompositionOfflineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AddBodyCompositionRepository {
    @Singleton
    @Provides
    fun providesAddBodyCompositionRepository(addBodyCompositionDAO: AddBodyCompositionDAO): AddBodyCompositionOfflineRepository {
        return AddBodyCompositionOfflineRepository(addBodyCompositionDAO)
    }
}