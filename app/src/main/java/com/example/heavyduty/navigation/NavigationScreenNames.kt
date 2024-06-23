package com.example.heavyduty.navigation

sealed class NavigationScreenNames(val route: String) {

    data object Main: NavigationScreenNames("main")
    data object AppRoute: NavigationScreenNames("app_screen")
    data object MainScreens: NavigationScreenNames("main_screens")

    data object AuthenticationRoute: NavigationScreenNames("authentication")
    data object Login: NavigationScreenNames("login")
    data object SignUp: NavigationScreenNames("sign_up")
    data object ForgotPassword: NavigationScreenNames("forgot_password")

    data object Tracker: NavigationScreenNames("tracker")
    data object Exercise: NavigationScreenNames("exercise")
    data object Profile: NavigationScreenNames("profile")

    data object TrackerRoute: NavigationScreenNames("tracker_route")
    data object BodyCompositionRoute: NavigationScreenNames("body_composition_route")
    data object WorkoutLogbookRoute: NavigationScreenNames("workout_logbook_route")

    data object BodyComposition: NavigationScreenNames("body_composition")
    data object BodyCompositionRecords: NavigationScreenNames("body_composition_records")
    data object AddBodyCompositionRecords: NavigationScreenNames("add_body_composition_records")

    data object WorkoutLogbook: NavigationScreenNames("workout_logbook")
    data object WorkoutLogbookScreens: NavigationScreenNames("workout_logbook_workout&exercise")
    data object WorkoutLogbookWorkout: NavigationScreenNames("workout_logbook_workout")
    data object WorkoutLogbookExercise: NavigationScreenNames("workout_logbook_exercise")

//-------------------------- Add new Cycle ---------------------------------
    data object ViewRoute: NavigationScreenNames("view_route")
    data object ViewCycle: NavigationScreenNames("view_cycle")
    data object ViewWorkout: NavigationScreenNames("view_workout")
    data object ViewExercise: NavigationScreenNames("view_exercise")

}