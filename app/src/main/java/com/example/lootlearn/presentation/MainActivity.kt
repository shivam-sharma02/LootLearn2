package com.example.lootlearn.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.lootlearn.presentation.components.OtpTextField
import com.example.lootlearn.presentation.screens.MainFeedScreen
import com.example.lootlearn.presentation.screens.OtpVerificationScreen
import com.example.lootlearn.presentation.screens.authChoices.AuthChoiceViewModel
import com.example.lootlearn.presentation.screens.authChoices.AuthRepository
import com.example.lootlearn.presentation.screens.authChoices.AuthViewModelFactory
import com.example.lootlearn.presentation.screens.authChoices.googlesignin.GoogleAuthUiClient
import com.example.lootlearn.presentation.ui.theme.LootLearnTheme
import com.example.lootlearn.utils.Navigation
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val repository = AuthRepository()
    val authChoiceViewModel: AuthChoiceViewModel by viewModels { AuthViewModelFactory(repository) }

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

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
                    Navigation(authChoiceViewModel, googleAuthUiClient)
//                    SignUpScreen()
//                    ForgotPasswordScreen()
//                    CongratulationScreen()
//                    OtpVerificationScreen()
//                    MainFeedScreen()
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



