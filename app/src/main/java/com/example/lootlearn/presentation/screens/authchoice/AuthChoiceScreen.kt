package com.example.lootlearn.presentation.screens.authchoice

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardSocialAuthButton
import com.example.lootlearn.presentation.screens.authchoice.googlesignin.GoogleAuthUiClient
import com.example.lootlearn.presentation.screens.authchoice.googlesignin.SignInState
import com.example.lootlearn.presentation.screens.authchoice.googlesignin.SignInViewModel
import com.example.lootlearn.presentation.ui.theme.AuthScreenPurpleText
import com.example.lootlearn.presentation.ui.theme.FacebookBackgroundColor
import com.example.lootlearn.presentation.ui.theme.GoogleTextContentColor
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.annotatedLoginSignupString
import com.example.lootlearn.utils.annotatedPrivacyPolicyString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AuthChoiceScreen(
    signupOrLogin : String = "Log in!",
    navController: NavController,
    googleAuthUiClient: GoogleAuthUiClient,
    viewModel: SignInViewModel = hiltViewModel()
){

    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
//    LaunchedEffect(key1 = state.signInError) {
//        state.signInError?.let { error ->
//            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
//        }
//    }
//
//    LaunchedEffect(key1 = state.isSignInSuccessful) {
//        if (state.isSignInSuccessful) {
//            navController.navigate(Screen.MainFeedScreen.route)
//            viewModel.resetState()
//        }
//    }

//    LaunchedEffect(key1 = state.value.isSignInSuccessful) {
//        if(state.value.isSignInSuccessful) {
//            Toast.makeText(
//                context,
//                "Sign in successful",
//                Toast.LENGTH_LONG
//            ).show()
//
//            navController.navigate(Screen.MainFeedScreen.route)
//            viewModel.resetState()
//        }
//    }

    LaunchedEffect(state.value) {
        if (state.value.isSignInSuccessful) {
            Toast.makeText(context, "Sign-in successful!", Toast.LENGTH_SHORT).show()

            navController.navigate(Screen.MainFeedScreen.route) {
//                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                navController.popBackStack()
            }

            viewModel.resetState()
        } else if (state.value.signInError != null) {
            Toast.makeText(context, "Sign-in failed: ${state.value.signInError}", Toast.LENGTH_LONG).show()
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    CoroutineScope(Dispatchers.Main).launch {
                        try {
                            val signInResult = googleAuthUiClient.signInWithIntent(data)
                            viewModel.onSignInResult(signInResult)
                        } catch (e: Exception) {
                            Log.e("AuthChoiceScreen", "SignIn Error: ${e.message}")
                            Toast.makeText(context, "Sign-in failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                } ?: run {
                    Log.e("AuthChoiceScreen", "SignIn result data is null")
                    Toast.makeText(context, "Sign-in result is null", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.e("AuthChoiceScreen", "SignIn result code: ${result.resultCode}")
            }
        }
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .statusBarsPadding()
        .padding(20.dp, 40.dp, 20.dp, 40.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.logoauth), contentDescription = "Logo", modifier = Modifier
                .width(60.dp)
                .height(60.dp))

            Spacer(modifier = Modifier.height(28.dp))

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.authheadertextimage), contentDescription = "auth page header text")

//            Text(text = annotatedLoginSignupString(signupOrLogin, onTextClick = {
//                navController.navigate(Screen.LogInScreen.route)
//            }))

            ClickableText(
                text = annotatedLoginSignupString(signupOrLogin),
                onClick = {
                        offset -> annotatedLoginSignupString(signupOrLogin).getStringAnnotations(
                    tag = "navigate",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                        navController.navigate(Screen.LogInScreen.route)
                }
                }
            )

            Spacer(modifier = Modifier.height(87.5.dp))

            StandardSocialAuthButton(logo = painterResource(id = R.drawable.googlelogo), text = "Continue with Google", backgroundColor = Color.White , textColor = GoogleTextContentColor){
                CoroutineScope(Dispatchers.Main).launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    signInIntentSender?.let {
                        launcher.launch(IntentSenderRequest.Builder(it).build())
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            StandardSocialAuthButton(logo = painterResource(id = R.drawable.facebooklogo), text = "Continue with Facebook", backgroundColor = FacebookBackgroundColor,textColor = Color.White ){}

            Spacer(modifier = Modifier.height(20.dp))

            StandardSocialAuthButton(logo = painterResource(id = R.drawable.applelogo), text = "Continue with Apple", backgroundColor = Color.Black,textColor = Color.White){}

            Spacer(modifier = Modifier.height(40.dp))

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.orseperator), contentDescription = "or separator image")

            Spacer(modifier = Modifier.height(28.dp))

            StandardSocialAuthButton(logo = painterResource(id = R.drawable.emailicon), text = "Continue with Email", backgroundColor = Color.White,textColor = AuthScreenPurpleText) {
                navController.navigate(
                    Screen.SignUpScreen.route
                )
            }

            Spacer(modifier = Modifier.height(51.5.dp))

            Text(text = annotatedPrivacyPolicyString(), modifier = Modifier
                .width(315.dp)
                .height(44.dp))
        }

    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview(){
//    AuthChoiceScreen()
}