package com.example.lootlearn.presentation.screens.newpassword

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.components.StandardTextField
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.forgotPasswordColor
import com.example.lootlearn.presentation.ui.theme.otpTextColor

@Composable
fun NewPasswordScreen(

    viewModel: NewPasswordViewModel = hiltViewModel()

){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp, 16.dp, 20.dp, 0.dp)
            .statusBarsPadding()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
        ){
            val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.backbuttonimage),
                contentDescription = "Back navigation button",
                modifier = Modifier
                    .width(62.dp)
                    .height(24.dp)
                    .clickable {
                        backDispatcher?.onBackPressed()
                    }
            )
        }

        Column(
            modifier = Modifier
                .padding(0.dp, 71.5.dp, 0.dp, 0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.newpasswordimage), contentDescription = "New password screen image", modifier = Modifier
                .width(247.5.dp)
                .height(122.04.dp))

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "New Password",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(600),
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
                color = forgotPasswordColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Set the new password for your account so you can login and access all features.",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp
                ),
                color = otpTextColor,
                modifier = Modifier
                    .width(335.dp)
                    .height(40.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))

            StandardTextField (
                text = viewModel.newPasswordText.value,
                hint = "Enter New Password",
                onValueChange = {
                    viewModel.setNewPasswordText(it)
                },
                keyboardType = KeyboardType.Password,
                isPasswordVisible = viewModel.showNewPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowNewPassword(it)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            StandardTextField (
                text = viewModel.confirmPasswordText.value,
                hint = "Confirm Password",
                onValueChange = {
                    viewModel.setConfirmPasswordText(it)
                },
                keyboardType = KeyboardType.Password,
                isPasswordVisible = viewModel.showConfirmPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowConfirmPassword(it)
                }
            )

            Spacer(modifier = Modifier.height(132.96.dp))

            StandardButton(buttonText = "Continue")
        }



    }
}

@Preview
@Composable
fun NewPasswordPreview(){
    NewPasswordScreen()
}

