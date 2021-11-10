package com.tutorial.socialnetworkapp.presentation.util

sealed class Screen(val route: String){
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
}
