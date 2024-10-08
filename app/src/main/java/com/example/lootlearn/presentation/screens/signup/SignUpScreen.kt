package com.example.lootlearn.presentation.screens.signup

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.components.StandardTextField
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.errorRed
import com.example.lootlearn.utils.annotatedPasswordHintText
import com.example.lootlearn.utils.annotatedPrivacyPolicyString


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
){
    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .background(Color.White)
        .padding(20.dp, 16.dp, 20.dp, 0.dp)
    ){

        val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

        Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.backbuttonimage),
                contentDescription = "Back navigation button",
                modifier = Modifier
                    .width(62.dp)
                    .height(24.dp)
                    .align(Alignment.TopStart)
                    .clickable {
                        backDispatcher?.onBackPressed()
                    }
            )

        Column(modifier = Modifier
            .padding(0.dp, 75.dp, 0.dp, 0.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var signUpandPrivacyTextSpace by remember { mutableStateOf(29.dp) }

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.signuplogo), contentDescription = "Logo of the company")

            Spacer(modifier = Modifier.height(40.dp))

            StandardTextField (
                text = viewModel.fullNameText.value,
                hint = "Full Name",
                onValueChange = {
                    viewModel.setFullNameText(it)
                }
            )

            Spacer(modifier = Modifier.height(12.dp))


            StandardTextField (
                text = viewModel.emailText.value,
                hint = "Email",
                onValueChange = {
                    viewModel.setEmailText(it)
                }
            )


            if (viewModel.checkEmailDatabase(viewModel.emailText.value)){
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.width(335.dp).padding(5.dp,5.dp,0.dp,0.dp)
            ) {
                Text(
                    text = "Email is already taken",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400)
                    ),
                    color = errorRed,
                    modifier = Modifier,
                )
            }
        }


            Spacer(modifier = Modifier.height(12.dp))


            StandardTextField (
                text = viewModel.useridText.value,
                hint = "User ID",
                onValueChange = {
                    viewModel.setUserIdText(it)
                }
            )

            if (viewModel.checkUserIDDatabase(viewModel.useridText.value)){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.width(335.dp).padding(5.dp,5.dp,0.dp,0.dp)
                ) {
                    Text(
                        text = "The User ID is already taken, try a new one.",
                        style = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        ),
                        color = errorRed,
                        modifier = Modifier,
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            var isFieldFocused by remember { mutableStateOf(false) }

            StandardTextField (
                text = viewModel.passwordText.value,
                hint = "Password",
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                },
                modifier = Modifier.onFocusChanged {focusState ->
                    isFieldFocused = focusState.isFocused
                }
            )

            if(isFieldFocused){
                signUpandPrivacyTextSpace = 3.dp
            }else {
                signUpandPrivacyTextSpace = 29.dp
            }

            if (isFieldFocused && !viewModel.isValidPassword(viewModel.passwordText.value)){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.width(335.dp).padding(5.dp,5.dp,0.dp,0.dp)
                ) {
                    Text(
                        text = annotatedPasswordHintText(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField (
                text = viewModel.confirmPasswordText.value,
                hint = "Confirm Password",
                keyboardType = KeyboardType.Password,
                onValueChange = {
                    viewModel.setConfirmPasswordText(it)
                },
                isPasswordVisible = viewModel.showConfirmPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowConfirmPassword(it)
                }
            )

            if (viewModel.checkPasswordMatch(viewModel.passwordText.value, viewModel.confirmPasswordText.value)){
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.width(335.dp).padding(5.dp,5.dp,0.dp,0.dp)
                ) {
                    Text(
                        text = "The password you entered does not match.",
                        style = TextStyle(
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400)
                        ),
                        color = errorRed,
                        modifier = Modifier,
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            StandardButton(buttonText = "Sign Up")

            Spacer(modifier = Modifier.height(signUpandPrivacyTextSpace))

            if (!viewModel.checkEmailDatabase(viewModel.emailText.value) && !viewModel.checkUserIDDatabase(viewModel.useridText.value) ){
                Text(text = annotatedPrivacyPolicyString(), modifier = Modifier.width(315.dp).height(44.dp))
            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpPreview(){
//    SignUpScreen()
//}