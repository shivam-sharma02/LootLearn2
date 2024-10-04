package com.fourtysix.lootlearn.presentation.screens

import android.content.Context
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.fourtysix.lootlearn.R
import com.fourtysix.lootlearn.presentation.components.StandardButton
import com.fourtysix.lootlearn.presentation.ui.theme.Poppins
import com.fourtysix.lootlearn.presentation.ui.theme.forgotPasswordColor
import com.fourtysix.lootlearn.presentation.ui.theme.otpTextColor
import com.fourtysix.lootlearn.utils.Screen


@Composable
fun CongratulationScreen(
    navController: NavController,
    context: Context,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 183.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.congratulationimage
                ), contentDescription = "Congratulation image"
            )

            Spacer(modifier = Modifier.height(34.36.dp))

            Text(
                text = "Congratulations !",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(600),
                    fontSize = 28.sp
                ),
                textAlign = TextAlign.Center,
                color = forgotPasswordColor
            )
            Spacer(modifier = Modifier.height(17.18.dp))

            Text(
                text = "Your password has been updated successfully",
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

            Spacer(modifier = Modifier.weight(1f))

            StandardButton(buttonText = "Log In", isLoading = false) {
                navController.navigate(Screen.LogInScreen.route){
                    popUpTo(0) { inclusive = true }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

//@Preview
//@Composable
//fun CongratulationsPreview() {
//    CongratulationScreen()
//}