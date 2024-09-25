package com.example.lootlearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.presentation.ui.theme.OtpBlockBorder
import com.example.lootlearn.presentation.ui.theme.OtpBlockShadow
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.otpTextColor

@Composable
fun OtpTextField(){
    Row (modifier = Modifier
        .width(272.dp)
        .height(60.dp), Arrangement.Center ){

        OtpBlock()
        Spacer(modifier = Modifier.width(16.dp))

        OtpBlock()
        Spacer(modifier = Modifier.width(16.dp))

        OtpBlock()
        Spacer(modifier = Modifier.width(16.dp))

        OtpBlock()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpBlock() : String {

    var codeValue : String = "8"

    Box(
        modifier = Modifier
            .width(56.dp)
            .height(60.dp)
            .border(1.dp, OtpBlockBorder, RoundedCornerShape(12.dp))
            .shadow(
                20.dp,
                RoundedCornerShape(12.dp),
                spotColor = OtpBlockShadow,
                ambientColor = OtpBlockShadow
            )
            .background(Color.White),
        contentAlignment = Alignment.Center

    ){
//        Text(text = codeValue,
//            modifier = Modifier
//                .align(Alignment.Center),
//            style = TextStyle(
//                fontFamily = Poppins,
//                fontWeight = FontWeight(500),
//                fontSize = 24.sp
//            ),
//            color = otpTextColor
//        )


        TextField(
            value = codeValue,
            onValueChange = {
                it -> codeValue = it
            },
            modifier = Modifier.width(56.dp).height(60.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontFamily = Poppins,
                fontWeight = FontWeight(500),
                fontSize = 24.sp
            )
        )

    }

    return codeValue
}

@Preview(showBackground = true)
@Composable
fun BlockPreview(){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        OtpTextField()
    }
}