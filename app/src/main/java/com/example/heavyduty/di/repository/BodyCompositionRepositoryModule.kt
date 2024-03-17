package com.example.heavyduty.di.repository

import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionDAO
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionOfflineRepository
import com.example.heavyduty.data.local.tracker.bodyComposition.main.BodyCompositionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BodyCompositionRepositoryModule {
    @Singleton
    @Provides
    fun providesBodyCompositionRepository(bodyCompositionDAO: BodyCompositionDAO): BodyCompositionOfflineRepository{
        return BodyCompositionOfflineRepository(bodyCompositionDAO)
    }
}