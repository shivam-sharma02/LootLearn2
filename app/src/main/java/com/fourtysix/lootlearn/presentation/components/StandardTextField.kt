package com.fourtysix.lootlearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fourtysix.lootlearn.presentation.ui.theme.OtpBlockBorder
import com.fourtysix.lootlearn.presentation.ui.theme.Poppins
import com.fourtysix.lootlearn.presentation.ui.theme.errorRed
import com.fourtysix.lootlearn.presentation.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 40,
    error: String = "",
    style: TextStyle = TextStyle(
        color = textGray,
        fontFamily = Poppins,
        fontWeight = FontWeight(300),
        fontSize = 16.sp
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit
) {

    Box(modifier = Modifier
        .width(335.dp)
        .height(60.dp)
    ){
        Column(
            modifier = modifier
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .border(1.dp, color = OtpBlockBorder, RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            TextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxLength) {
                        onValueChange(it)
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                maxLines = maxLines,
                textStyle = style,
                placeholder = {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = textGray,
                            fontFamily = Poppins,
                            fontWeight = FontWeight(300),
                            fontSize = 16.sp
                        )
                    )
                },
                isError = error != "",
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),
                visualTransformation = if (!isPasswordVisible && isPasswordToggleDisplayed) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                singleLine = singleLine,
//            leadingIcon = if (leadingIcon != null) {
//                val icon: @Composable () -> Unit = {
//                    Icon(
//                        imageVector = leadingIcon,
//                        contentDescription = null,
//                        tint = MaterialTheme.colors.onBackground,
//                        modifier = Modifier.size(IconSizeMedium)
//                    )
//                }
//                icon
//            } else null,
                trailingIcon = if(isPasswordToggleDisplayed) {
                    val icon: @Composable () -> Unit = {
                        IconButton(
                            onClick = {
                                onPasswordToggleClick(!isPasswordVisible)
                            },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) {
                                    Icons.Filled.Visibility
                                } else {
                                    Icons.Filled.VisibilityOff
                                },
                                tint = textGray,
                                contentDescription = if (isPasswordVisible) {
                                    ""
                                } else {
                                    ""
                                }
                            )
                        }
                    }
                    icon
                } else null,
                modifier = Modifier
                    .fillMaxWidth().clip(RoundedCornerShape(16.dp))
            )
            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(400),
                        fontSize = 12.sp
                    ),
                    color = errorRed,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(132.dp)
                        .height(22.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Standardtextbutton(){
    StandardTextField(
        text = "Sample text",
        hint = "Enter text",
        keyboardType = KeyboardType.Password,
        onValueChange = {}
    )
//    textField()
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textField(){
    var text by remember {
        mutableStateOf("Hello there")
    }

    TextField(value = text, onValueChange = {value -> text = value}, colors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.White
    ))
}