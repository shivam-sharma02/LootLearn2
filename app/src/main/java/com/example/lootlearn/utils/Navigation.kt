package com.example.lootlearn.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lootlearn.presentation.screens.AuthChoiceScreen
import com.example.lootlearn.presentation.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route )
    {
        composable (Screen.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Screen.AuthChoiceScreen.route) { 
            AuthChoiceScreen(navController = navController)
        }
    }
}