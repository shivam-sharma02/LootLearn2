package com.example.lootlearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.presentation.ui.theme.ButtonBackground
import com.example.lootlearn.presentation.ui.theme.Poppins

//@Composable
//fun StandardButton(
//     buttonText : String
//){
//    Button(modifier = Modifier
//        .fillMaxWidth()
//        ,
//        onClick = { /*TODO*/ }
//    ) {
//        Text(text = buttonText)
//    }
//}


@Preview(showBackground = true)
@Composable
fun standardButtonPreview() {
//    StandardButton(buttonText = "Normal button", )
}

@Composable
fun StandardButton(
    buttonText: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(335.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ButtonBackground),
        contentAlignment = Alignment.Center
    ) {

        if (isLoading) {
            CircularProgressIndicator(color = Color.White, strokeWidth = 4.dp)
        } else {
            Text(
                text = buttonText,
                Modifier
                    .clickable(onClick = onClick),
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500)
                ),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}