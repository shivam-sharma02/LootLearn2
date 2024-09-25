package com.example.lootlearn.presentation.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.OtpTextField
import com.example.lootlearn.presentation.components.OtpTextField2
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.verificationText
import com.example.lootlearn.utils.annotatedResendString

@Composable
fun OtpVerificationScreen(){

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 16.dp)
        .statusBarsPadding()
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp, 0.dp, 0.dp)
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
                .fillMaxSize()
                .padding(0.dp, 132.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.verificationtext), contentDescription = "Verification text" , modifier = Modifier
                .width(272.dp)
                .height(98.dp))

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "1:30",
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

            OtpTextField2()

            Spacer(modifier = Modifier.height(48.dp))

            Text(text = annotatedResendString(), modifier = Modifier
                .width(327.dp)
                .height(24.dp),
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(125.dp))

            StandardButton(buttonText = "Continue")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun OtpVerificationPreview(){
    OtpVerificationScreen()
}