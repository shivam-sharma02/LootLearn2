package com.example.lootlearn.presentation.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardSocialAuthButton
import com.example.lootlearn.presentation.ui.theme.AuthScreenPurpleText
import com.example.lootlearn.presentation.ui.theme.FacebookBackgroundColor
import com.example.lootlearn.presentation.ui.theme.GoogleTextContentColor
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.annotatedLoginSignupString
import com.example.lootlearn.utils.annotatedPrivacyPolicyString


@Composable
fun AuthChoiceScreen(
    signupOrLogin : String = "Log in!",
    navController: NavController
){
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