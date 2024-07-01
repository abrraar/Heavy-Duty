package com.example.heavyduty.data.local.tracker.workoutLogbook.addCycle.defaultData


import com.example.heavyduty.domain.model.tracker.workoutLogbook.WorkoutModel
import com.example.heavyduty.units.getAllMuscles
import com.example.heavyduty.units.getLowerBodyMuscles
import com.example.heavyduty.units.getUpperBodyMuscles

    val IdealPrincipleWorkouts by lazy {
        arrayListOf(
        WorkoutModel(
            workoutNumber = 1,
            workoutName =  "Chest and Back",
            listOfExercise = ChestAndBack,
            datePerformed = "",
            muscle = getUpperBodyMuscles()
        ),
        WorkoutModel(
            workoutNumber = 2,
            workoutName = "Legs and Abs",
            listOfExercise = LegsAndAbs,
            datePerformed = "",
            muscle = getLowerBodyMuscles()
        ),

        WorkoutModel(
            workoutNumber = 3,
            workoutName = "Shoulders and Arms",
            listOfExercise = ShouldersAndArms,
            datePerformed = "",
            muscle = getUpperBodyMuscles()
        ),

        WorkoutModel(
            workoutNumber = 4,
            workoutName = "Legs and Abs",
            listOfExercise = LegsAndAbs,
            datePerformed = "",
            muscle = getLowerBodyMuscles()
        )
    ) }

//--------------------------------------------------------------

    val beginnerWorkout by lazy {
        arrayListOf(
            WorkoutModel(
                workoutNumber = 1,
                workoutName = "Workout Day 1",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 2,
                workoutName = "Workout Day 2",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 3,
                workoutName = "Workout Day 3",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 4,
                workoutName = "Workout Day 4",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 5,
                workoutName = "Workout Day 5",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 6,
                workoutName = "Workout Day 6",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 7,
                workoutName = "Workout Day 7",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 8,
                workoutName = "Workout Day 8",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 1,
                workoutName = "Workout Day 9",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
            WorkoutModel(
                workoutNumber = 1,
                workoutName = "Workout Day 10",
                listOfExercise = beginnerExercise,
                datePerformed = "",
                muscle = getAllMuscles()
            ),
        )
    }

//------------------------------------------------------------------------------

    val advancedWorkout by lazy {
        arrayListOf(
            WorkoutModel(
                workoutNumber = 1,
                workoutName = "Advance Workout 1",
                listOfExercise = exerciseOne,
                datePerformed = "",
                muscle = getLowerBodyMuscles()
            ),

            WorkoutModel(
                workoutNumber = 2,
                workoutName = "Advance Workout 2",
                listOfExercise = exerciseTwo,
                datePerformed = "",
                muscle = getLowerBodyMuscles()
            )
        )
    }

