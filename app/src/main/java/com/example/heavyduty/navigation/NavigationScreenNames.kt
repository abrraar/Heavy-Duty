package com.example.heavyduty.navigation

sealed class NavigationScreenNames(val route: String) {
    data object AuthenticationScreen: NavigationScreenNames("authentication")
    data object Login: NavigationScreenNames("login")
    data object Registration: NavigationScreenNames("registration")
    data object ForgotPassword: NavigationScreenNames("forgot_password")

    data object AppScreen: NavigationScreenNames("app_screen")
    data object Tracker: NavigationScreenNames("tracker")
    data object TrackerScreen: NavigationScreenNames("tracker_screen")
    data object BodyComposition: NavigationScreenNames("body_composition")
    data object BodyCompositionRecords: NavigationScreenNames("body_composition_records")
    data object AddBodyCompositionRecords: NavigationScreenNames("add_body_composition_records")
    data object WorkoutLogbook: NavigationScreenNames("workout_logbook")

    data object Exercise: NavigationScreenNames("exercise")
    data object Profile: NavigationScreenNames("profile")


}