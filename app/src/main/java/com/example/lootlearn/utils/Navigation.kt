package com.example.lootlearn.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lootlearn.presentation.screens.MainFeedScreen
import com.example.lootlearn.presentation.screens.otpverification.OtpVerificationScreen
import com.example.lootlearn.presentation.screens.authChoices.AuthChoiceScreen
import com.example.lootlearn.presentation.screens.forgotpassword.ForgotPasswordScreen
import com.example.lootlearn.presentation.screens.login.LogInScreen
import com.example.lootlearn.presentation.screens.SplashScreen
import com.example.lootlearn.presentation.screens.authChoices.AuthChoiceViewModel
import com.example.lootlearn.presentation.screens.signup.SignUpScreen

@Composable
fun Navigation(authChoiceViewModel: AuthChoiceViewModel, context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route )
    {
        composable (Screen.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screen.AuthChoiceScreen.route) { 
            AuthChoiceScreen(navController = navController, authChoiceViewModel = authChoiceViewModel)
        }
        composable(Screen.LogInScreen.route) { 
            LogInScreen(navController = navController, context = context)
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController, context = context)
        }
        composable(Screen.OtpVerificationScreen.route) {
            OtpVerificationScreen(navController = navController, context = context)
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController = navController, context = context)
        }
    }
}