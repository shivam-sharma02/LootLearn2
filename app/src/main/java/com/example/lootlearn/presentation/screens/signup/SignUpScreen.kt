package com.example.lootlearn.presentation.screens.signup

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardButton
import com.example.lootlearn.presentation.components.StandardTextField
import com.example.lootlearn.utils.annotatedPrivacyPolicyString


@Composable
fun SignUpScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .background(Color.White)
        .padding(20.dp, 23.dp, 20.dp, 0.dp)
    ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.backbuttonimage),
                contentDescription = "Back navigation button",
                modifier = Modifier
                    .width(62.dp)
                    .height(24.dp)
                    .align(Alignment.TopStart)
            )

        Column(modifier = Modifier
            .padding(0.dp, 80.dp, 0.dp, 0.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.signuplogo), contentDescription = "Logo of the company")

            Spacer(modifier = Modifier.height(40.dp))

            StandardTextField (
                text = "",
                hint = "Full Name",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField (
                text = "",
                hint = "Email",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))


            StandardTextField (
                text = "",
                hint = "User ID",
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField (
                text = "",
                hint = "Password",
                keyboardType = KeyboardType.Password,
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField (
                text = "",
                hint = "Password",
                keyboardType = KeyboardType.Password,
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(32.dp))

            StandardButton(buttonText = "Sign Up")

            Spacer(modifier = Modifier.height(79.dp))

            Text(text = annotatedPrivacyPolicyString(), modifier = Modifier.width(315.dp).height(44.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview(){
    SignUpScreen()
}