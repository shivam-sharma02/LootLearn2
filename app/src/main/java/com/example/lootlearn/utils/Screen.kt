package com.example.lootlearn.utils

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object AuthChoiceScreen : Screen("auth_choice_screen")

}