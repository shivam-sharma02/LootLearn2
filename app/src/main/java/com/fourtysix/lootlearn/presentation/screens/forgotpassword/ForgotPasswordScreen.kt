package com.fourtysix.lootlearn.presentation.screens.forgotpassword

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fourtysix.lootlearn.R
import com.fourtysix.lootlearn.presentation.components.StandardButton
import com.fourtysix.lootlearn.presentation.components.StandardTextField
import com.fourtysix.lootlearn.presentation.ui.theme.Poppins
import com.fourtysix.lootlearn.presentation.ui.theme.errorRed
import com.fourtysix.lootlearn.presentation.ui.theme.forgotPasswordColor
import com.fourtysix.lootlearn.presentation.ui.theme.otpTextColor
import com.fourtysix.lootlearn.requestModel.ForgotPasswordRequestModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    context: Context,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {

    val forgotPasswordLoading = viewModel.forgotPasswordLoading.observeAsState(initial = false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp, 16.dp, 20.dp, 0.dp)
            .statusBarsPadding()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween // Ensure the content is spaced between the top and bottom
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                ) {

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

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.forgotpasswordvector), contentDescription = "Forgot password vector image", modifier = Modifier
                        .width(160.63.dp)
                        .height(140.63.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Forgot Password",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(600),
                        fontSize = 24.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = forgotPasswordColor
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Enter your email for verification process, we will send 4 digits code to your email.",
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

                StandardTextField(
                    text = viewModel.emailText.value,
                    hint = "Email",
                    onValueChange = {
                        viewModel.setEmailText(it)
                    }
                )

                if (viewModel.isEmailEmpty.value) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .width(335.dp)
                            .padding(5.dp, 5.dp, 0.dp, 0.dp)
                    ) {
                        Text(
                            text = "Email is empty",
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
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp) // Space from the bottom
            ) {
                StandardButton(buttonText = "Continue", isLoading = forgotPasswordLoading.value) {
                    viewModel.checkEmptyFields()

                    if (!viewModel.isEmailEmpty.value) {
                        val requestBody = ForgotPasswordRequestModel(email = viewModel.emailText.value)
                        viewModel.forgotPassword(requestBody = requestBody, navController = navController, context = context)
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun ForgotPasswordScreenPreview(){
//    ForgotPasswordScreen()
//}