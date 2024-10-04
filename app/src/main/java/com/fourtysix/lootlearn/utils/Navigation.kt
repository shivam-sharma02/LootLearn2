package com.fourtysix.lootlearn.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lootlearn.presentation.screens.BookingScreen
import com.example.lootlearn.presentation.screens.ChatScreen
import com.example.lootlearn.presentation.screens.NewItemScreen
import com.example.lootlearn.presentation.screens.WalletScreen
import com.fourtysix.lootlearn.presentation.screens.MainFeedScreen
import com.fourtysix.lootlearn.presentation.screens.CongratulationScreen
import com.fourtysix.lootlearn.presentation.screens.otpverification.OtpVerificationScreen
import com.fourtysix.lootlearn.presentation.screens.authChoices.AuthChoiceScreen
import com.fourtysix.lootlearn.presentation.screens.forgotpassword.ForgotPasswordScreen
import com.fourtysix.lootlearn.presentation.screens.login.LogInScreen
import com.fourtysix.lootlearn.presentation.screens.SplashScreen
import com.fourtysix.lootlearn.presentation.screens.authChoices.AuthChoiceViewModel
import com.fourtysix.lootlearn.presentation.screens.authChoices.googlesignin.GoogleAuthUiClient
import com.fourtysix.lootlearn.presentation.screens.newpassword.NewPasswordScreen
import com.fourtysix.lootlearn.presentation.screens.signup.SignUpScreen

@Composable
fun Navigation(authChoiceViewModel: AuthChoiceViewModel, googleAuthUiClient: GoogleAuthUiClient, context: Context){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route )
    {
        composable (Screen.SplashScreen.route){
            SplashScreen(navController, context = context)
        }
        composable(Screen.AuthChoiceScreen.route) { 
            AuthChoiceScreen(navController = navController, googleAuthUiClient = googleAuthUiClient, authChoiceViewModel = authChoiceViewModel)
        }
        composable(Screen.LogInScreen.route) { 
            LogInScreen(navController = navController, context = context)
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController = navController, context = context)
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
        composable(Screen.NewPasswordScreen.route) {
            NewPasswordScreen(navController = navController, context = context)
        }
        composable(Screen.CongratulationScreen.route) {
            CongratulationScreen(navController = navController, context = context)
        }
        composable(Screen.BookingScreen.route) {
            BookingScreen(navController = navController)
        }
        composable(Screen.NewItemScreen.route) {
            NewItemScreen(navController = navController)
        }
        composable(Screen.WalletScreen.route) {
            WalletScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
    }
}