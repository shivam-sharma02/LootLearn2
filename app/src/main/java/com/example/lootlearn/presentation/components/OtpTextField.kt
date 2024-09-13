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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.presentation.ui.theme.OtpBlockBorder
import com.example.lootlearn.presentation.ui.theme.OtpBlockShadow

@Composable
fun OtpTextField(){
    Row (modifier = Modifier.fillMaxWidth(), Arrangement.Center ){

        OtpBlock()
        Spacer(modifier = Modifier.width(20.dp))

        OtpBlock()
        Spacer(modifier = Modifier.width(20.dp))

        OtpBlock()
        Spacer(modifier = Modifier.width(20.dp))

        OtpBlock()
    }
}

@Composable
fun OtpBlock() {
    Box(
        modifier = Modifier
            .width(56.dp)
            .height(60.dp)
            .border(1.dp, OtpBlockBorder, RoundedCornerShape(8.dp))
            .shadow(
                20.dp,
                RoundedCornerShape(8.dp),
                spotColor = OtpBlockShadow,
                ambientColor = OtpBlockShadow
            )
            .background(Color.White),
        contentAlignment = Alignment.Center

    ){
        Text(text = "9",
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyMedium, fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BlockPreview(){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        OtpTextField()
    }
}