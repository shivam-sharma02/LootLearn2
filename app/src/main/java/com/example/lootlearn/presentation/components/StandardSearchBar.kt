package com.example.lootlearn.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lootlearn.R
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.textGray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardSearchBar(
    modifier: Modifier,
    onValueChange: (String) -> Unit
){
    Box(
        modifier = Modifier
//            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .width(343.dp)
            .height(36.dp)
    ){

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            Image(ImageVector.vectorResource(R.drawable.magnifyingglass), contentDescription = "search icon",
//                modifier = Modifier.width(15.6.dp).height(15.7.dp))

            TextField(
                modifier = Modifier.fillMaxSize(),
                value = "",
                onValueChange = {
                    onValueChange(it)
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.magnifyingglass),
                        contentDescription = "search icon",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.microphone), // Trailing icon
                        contentDescription = "Trailing Icon",
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                ),
                maxLines = 1,
                textStyle = TextStyle(
                    color = textGray,
                    fontFamily = Poppins,
                    fontWeight = FontWeight(300),
                    fontSize = 6.sp
                ),
                placeholder = {
                    androidx.compose.material3.Text(
                        text = "Search",
                        style = TextStyle(
                            color = textGray,
                            fontFamily = Poppins,
                            fontWeight = FontWeight(400),
                            fontSize = 7.sp
                        )
                    )
                }
            )

//            Spacer(modifier = Modifier.width(46.dp))
//
//            Image(ImageVector.vectorResource(R.drawable.microphone), contentDescription = "microphone icon",
//                modifier = Modifier.width(15.6.dp).height(15.7.dp),)
        }
    }
}

@Preview
@Composable
fun preview(){
//    StandardSearchBar(onValueChange = {})
}