package com.example.lootlearn.presentation.screens.login

import android.content.Context
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.lootlearn.presentation.ui.theme.switchTrackColor
import com.example.lootlearn.requestModel.LoginRequestModel
import com.example.lootlearn.requestModel.SignupRequestModel
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.annotatedPrivacyPolicyString

@Composable
fun LogInScreen(
    navController: NavController,
    context: Context,
    viewModel: LogInViewModel = hiltViewModel()
){

    val loginLoading = viewModel.loginLoading.observeAsState(initial = false)

    var isEnable by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
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
                .align(Alignment.TopStart).clickable {
                    backDispatcher?.onBackPressed()
                }
        )

        Column(
            modifier = Modifier
                .padding(0.dp, 125.dp, 0.dp, 0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(imageVector = ImageVector.vectorResource(id = R.drawable.signuplogo), contentDescription = "Logo of the company")

            Spacer(modifier = Modifier.height(60.dp))

            StandardTextField (
                text = viewModel.useridText.value,
                hint = "Email/User ID",
                onValueChange = {
                    viewModel.setUserIdText(it)
                    viewModel.checkEmptyFields()
                }
            )

            if (viewModel.isUseridEmpty.value) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .width(335.dp)
                        .padding(5.dp, 5.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "Email/User ID is empty",
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

            Spacer(modifier = Modifier.height(20.dp))

            StandardTextField(
                text = viewModel.passwordText.value,
                hint = "Password",
                isPasswordVisible = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                },
                onValueChange = {
                    viewModel.setPasswordText(it)
                    viewModel.checkEmptyFields()
                },
                keyboardType = KeyboardType.Password
            )

            if (viewModel.isPasswordEmpty.value) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .width(335.dp)
                        .padding(5.dp, 5.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "Password is empty",
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

            Spacer(modifier = Modifier.height(60.dp))


            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Switch(
                    checked = isEnable,
                    onCheckedChange = {
                    isEnable = it
                },
                    colors =  SwitchDefaults.colors(
//                        checkedThumbColor = Color.Blue,
                        uncheckedThumbColor = Color.White,
//                        checkedTrackColor = Color.Blue,
                        uncheckedTrackColor = switchTrackColor,
                        disabledCheckedBorderColor = Color.Gray,
                        checkedBorderColor = Color.Gray
                    ),
                    modifier = Modifier
                        .scale(0.6f,0.6f)
                    ,

                )

                Spacer(modifier = Modifier.width(0.dp))

                Text(text = "Remember me",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp
                    )
                )

                Spacer(modifier = Modifier.width(28.dp))

                Text(text = "Forgot Password ?",
                    modifier = Modifier.clickable { navController.navigate(Screen.ForgotPasswordScreen.route) },
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp
                    ),
                    maxLines = 1

                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            StandardButton(buttonText = "Log In", isLoading = loginLoading.value) {
                viewModel.checkEmptyFields()

                if (viewModel.useridText.value.isNotEmpty() && viewModel.passwordText.value.isNotEmpty()) {
                    val requestBody = LoginRequestModel(email_or_userid = viewModel.useridText.value, password = viewModel.passwordText.value)
                    viewModel.login(requestBody = requestBody, navController = navController, context = context)
                }
            }

            Spacer(modifier = Modifier.height(82.dp))

            Text(text = annotatedPrivacyPolicyString(), modifier = Modifier.width(315.dp).height(44.dp))

        }

    }

}

@Preview
@Composable
fun LoginPreview(){
//    LogInScreen()
}