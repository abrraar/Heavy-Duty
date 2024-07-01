package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData

import com.example.heavyduty.domain.model.tracker.workoutLogbook.CycleModel
import java.text.DateFormat
import java.util.Calendar

val listOfDefaultCycle: List<CycleModel> by lazy {
     listOf(
//----------------- Beginner's Cycle -------------------
         CycleModel(
             cycleName = "Beginner's\nCycle",
             listOfWorkout = beginnerWorkout,
             startDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Calendar.getInstance().time),
             endDate = "",
             overallProgress = 0
         ),
//---------------- The Ideal Principle Cycle -------------------
         CycleModel(
             cycleName = "The Ideal Principled\nCycle",
             listOfWorkout = IdealPrincipleWorkouts,
             startDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Calendar.getInstance().time),
             endDate = "",
             overallProgress = 0
         ),

//---------------- Advance Consolidation Cycle -------------------
         CycleModel(
             cycleName = "Advanced Consolidated\nCycle",
             listOfWorkout = advancedWorkout,
             startDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Calendar.getInstance().time),
             endDate = "",
             overallProgress = 0
         )

     )
 }
