package com.example.lootlearn.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.components.StandardSocialAuthButton
import com.example.lootlearn.presentation.components.StandardTextField
import com.example.lootlearn.presentation.ui.theme.FacebookBackgroundColor
import com.example.lootlearn.presentation.ui.theme.GoogleTextContentColor
import com.example.lootlearn.presentation.ui.theme.GradientColors
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.gradientBrush
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = gradientBrush(GradientColors, true)
            )
            .padding(10.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.applelogo), text = "Continue with Apple", backgroundColor = Color.Black,textColor = Color.White )
//            Spacer(modifier = Modifier.height(5.dp))
//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.facebooklogo), text = "Continue with Facebook", backgroundColor = FacebookBackgroundColor,textColor = Color.White )
//            Spacer(modifier = Modifier.height(5.dp))
//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.googlelogo), text = "Continue with Google", backgroundColor = Color.White,textColor = GoogleTextContentColor )
//            Spacer(modifier = Modifier.height(5.dp))
//
//            StandardButton(buttonText = "Continue")
            LaunchedEffect(key1 = true) {

                delay(2000)
                navController.popBackStack()
                navController.navigate(Screen.AuthChoiceScreen.route)
                
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun splashPreview(){
//    SplashScreen()
}