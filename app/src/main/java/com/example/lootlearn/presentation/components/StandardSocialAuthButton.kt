package com.example.lootlearn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.R
import com.example.lootlearn.presentation.ui.theme.FacebookBackgroundColor
import com.example.lootlearn.presentation.ui.theme.GoogleTextContentColor
import com.example.lootlearn.utils.annotatedPrivacyPolicyString


@Composable
fun StandardSocialAuthButton(
    logo : Painter,
    text : String,
    backgroundColor : Color,
    textColor : Color
//    onCLick : () -> Unit
){
    Box(
        modifier = Modifier
            .shadow(5.dp)
            .width(335.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(13.dp),
//            .clickable( onClick = onCLick),
        Alignment.Center
    ){
        Row(
            modifier = Modifier
                .background(backgroundColor),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            androidx.compose.foundation.Image(painter = logo, contentDescription = "Logo of the social media", modifier = Modifier.width(24.dp).height(24.dp))
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = text, style = MaterialTheme.typography.bodyMedium, color = textColor, fontSize = 16.sp, )
        }
    }
}

@Preview
@Composable
fun SocialButtonPreview(){
    StandardSocialAuthButton(logo = painterResource(id = R.drawable.facebooklogo) , text = "Continue with Facebook", backgroundColor = FacebookBackgroundColor, textColor = Color.White)
}