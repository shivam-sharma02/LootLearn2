package com.example.lootlearn.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.lootlearn.presentation.components.OtpTextField
import com.example.lootlearn.presentation.ui.theme.LootLearnTheme
import com.example.lootlearn.presentation.screens.authchoice.googlesignin.GoogleAuthUiClient
import com.example.lootlearn.utils.Navigation
import com.example.lootlearn.presentation.screens.authchoice.googlesignin.SignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    private val signInViewModel: SignInViewModel by viewModels()


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

                    Navigation(googleAuthUiClient
                    , state = signInViewModel.state.collectAsState().value)
//                    SignUpScreen()
//                    ForgotPasswordScreen()
//                    CongratulationScreen()
//                    OtpVerificationScreen()
                }

            }
        }
    }

    @Composable
    private fun handleGoogleSignIn() {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                if (result.resultCode == RESULT_OK) {
                    lifecycleScope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(
                            intent = result.data ?: return@launch
                        )
                        signInViewModel.onSignInResult(signInResult)
                    }
                }
            }
        )

        // Launch Google Sign-In flow
        LaunchedEffect(Unit) {
            val signInIntentSender = googleAuthUiClient.signIn()
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@LaunchedEffect
                ).build()
            )
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



