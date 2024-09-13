package com.example.lootlearn.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lootlearn.presentation.components.OtpBlock
import com.example.lootlearn.presentation.components.OtpTextField
import com.example.lootlearn.presentation.screens.AuthChoiceScreen
import com.example.lootlearn.presentation.screens.SplashScreen
import com.example.lootlearn.presentation.screens.signup.SignUpScreen
import com.example.lootlearn.presentation.ui.theme.LootLearnTheme
import com.example.lootlearn.utils.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LootLearnTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    Navigation()
//                    SignUpScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LootLearnTheme {
//        SplashScreen()
    }
}

@Composable
fun Whitebackground(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        OtpTextField()
    }
}



