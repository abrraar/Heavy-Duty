package com.example.heavyduty.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.heavyduty.presentation.view.screens.auth.ForgotPass
import com.example.heavyduty.presentation.view.screens.auth.Login
import com.example.heavyduty.presentation.view.screens.auth.SignUp

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController){
    navigation(
        route = NavigationScreenNames.AuthenticationRoute.route,
        startDestination = NavigationScreenNames.Login.route
    ){
        composable(route = NavigationScreenNames.Login.route){
            Login(navHostController)
        }
        composable(route = NavigationScreenNames.SignUp.route){
            SignUp(navHostController)
        }
        composable(route = NavigationScreenNames.ForgotPassword.route){
            ForgotPass()
        }

    }
}