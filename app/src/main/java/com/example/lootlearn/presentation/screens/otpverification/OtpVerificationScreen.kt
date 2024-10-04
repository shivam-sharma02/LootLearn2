package com.example.lootlearn.presentation.screens.otpverification

import android.content.Context
import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.OtpTextField2
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.errorRed
import com.example.lootlearn.presentation.ui.theme.verificationText
import com.example.lootlearn.requestModel.LoginRequestModel
import com.example.lootlearn.requestModel.VerifyOTPRequestModel
import com.example.lootlearn.utils.Consts
import com.example.lootlearn.utils.SharedPreferenceHelper
import com.example.lootlearn.utils.annotatedResendString
import kotlinx.coroutines.delay

@Composable
fun OtpVerificationScreen(
    navController: NavController,
    context: Context,
    verifyOTPViewModel: VerifyOTPViewModel = hiltViewModel()
) {

    val sharedPreferenceHelper = SharedPreferenceHelper(context)
//    Log.e("FROM", sharedPreferenceHelper.getString(Consts.OTP_VERIFY_FROM))
    val verifyOTPLoading = verifyOTPViewModel.verifyOTPLoading.observeAsState(initial = false)
    var otpValue by remember { mutableStateOf("") }
    var timeLeftInSeconds by remember { mutableIntStateOf(60) } // Set initial countdown time
    var isTimerRunning by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp)
            .statusBarsPadding()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp, 0.dp, 0.dp)
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


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 132.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.verificationtext), contentDescription = "Verification text", modifier = Modifier
                    .width(272.dp)
                    .height(98.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            LaunchedEffect(isTimerRunning) {
                if (isTimerRunning) {
                    while (timeLeftInSeconds > 0) {
                        delay(1000L)
                        timeLeftInSeconds -= 1
                    }
                    isTimerRunning = false // Reset timer after completion
                } else {
                    timeLeftInSeconds = 60
                }
            }

            Text(
                text = "$timeLeftInSeconds",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600)
                ),
                color = verificationText,
                modifier = Modifier
                    .width(45.dp),
            )

            Spacer(modifier = Modifier.height(60.dp))

//            OtpTextField2()

            Surface(
                modifier = Modifier
                    .height(60.dp)
                    .background(Color.White),
                color = Color.White,
            ) {

                BasicTextField(
                    value = otpValue,
                    modifier = Modifier.height(60.dp),
                    onValueChange = {
                        if (it.length <= 4) {
                            otpValue = it
                            Log.e("OTP_VALUE", otpValue)
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    decorationBox = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            repeat(4) { index ->
                                val char = when {
                                    index >= otpValue.length -> ""
                                    else -> otpValue[index].toString()
                                }

                                val isFocused = otpValue.length == index

                                // Wrapping each text inside a Box for centering
                                Box(
                                    modifier = Modifier
                                        .width(56.dp)
                                        .height(60.dp)
                                        .border(
                                            1.dp,
                                            if (isFocused) Color.DarkGray else Color.Gray,
                                            RoundedCornerShape(12.dp)
                                        ),
                                    contentAlignment = Alignment.Center // Centers the text inside the box
                                ) {
                                    Text(
                                        text = char,
                                        style = TextStyle(
                                            fontFamily = Poppins,
                                            fontWeight = FontWeight(500),
                                            fontSize = 24.sp,
                                        ),
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                )
            }

            if (verifyOTPViewModel.isEmpty.value) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .width(335.dp)
                        .padding(25.dp, 5.dp, 0.dp, 0.dp)
                ) {
                    Text(
                        text = "Please enter valid OTP!",
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

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = annotatedResendString(), modifier = Modifier.clickable {
                    
                }
                    .width(327.dp)
                    .height(24.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(125.dp))

            StandardButton(buttonText = "Continue", isLoading = verifyOTPLoading.value) {
                verifyOTPViewModel.checkEmptyFields(otpValue)

                if (!verifyOTPViewModel.isEmpty.value) {
                    val requestBody = VerifyOTPRequestModel(email = sharedPreferenceHelper.getString(Consts.EMAIL), otp = otpValue)
                    verifyOTPViewModel.verifyOTP(requestBody = requestBody, navController = navController, context = context)
                }
            }
        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun OtpVerificationPreview(){
//    OtpVerificationScreen()
//}