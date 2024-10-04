package com.fourtysix.lootlearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fourtysix.lootlearn.presentation.ui.theme.Poppins

@Composable
fun OtpTextField2() {
    Surface(
        modifier = Modifier
            .height(60.dp)
            .background(Color.White),
        color = Color.White,
    ) {
        var otpValue by remember { mutableStateOf("") }

        BasicTextField(
            value = otpValue,
            modifier = Modifier.height(60.dp),
            onValueChange = {
                if (it.length <= 4) {
                    otpValue = it
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            decorationBox = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp,0.dp,0.dp,0.dp)
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
}


@Preview
@Composable
fun PreviewOtp(){
    OtpTextField2()
}