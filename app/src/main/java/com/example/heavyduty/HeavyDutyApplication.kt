package com.example.heavyduty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HeavyDutyApplication: Application()
{
    override fun onCreate() {
        super.onCreate()

    }
}