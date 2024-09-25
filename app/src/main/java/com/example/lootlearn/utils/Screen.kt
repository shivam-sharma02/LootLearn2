package com.example.lootlearn.utils

sealed class Screen(val route: String) {

    object SplashScreen : Screen("splash_screen")
    object AuthChoiceScreen : Screen("auth_choice_screen")
    object LogInScreen : Screen("login_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")
    object SignUpScreen: Screen("sign_up_screen")

}