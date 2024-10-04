package com.example.lootlearn.presentation.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lootlearn.R
import com.example.lootlearn.presentation.components.StandardScaffold
import com.example.lootlearn.presentation.screens.authChoices.googlesignin.UserData
import com.example.lootlearn.presentation.ui.theme.Poppins
import com.example.lootlearn.presentation.ui.theme.verificationText

@Composable
fun MainFeedScreen(
    navController: NavController,
    context: Context
//    userData: UserData?,
){

    StandardScaffold(

    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(0.dp, 0.dp,0.dp,0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Column (
                    modifier = Modifier.fillMaxWidth().height(162.dp)
                        .background(verificationText)
                        .padding(0.dp, 40.dp, 0.dp, 0.dp)
                ){

//                if(userData?.username != null) {
//                    Text(
//                        text = userData.username,
//                        textAlign = TextAlign.Center,
//                        fontSize = 36.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                }

                    Row (
                        modifier = Modifier.padding(20.dp, 12.dp,16.dp,0.dp)
                    ){
                        Image(painter = painterResource(R.drawable.profileinfo1), contentDescription = "profile info",
                            modifier = Modifier.width(177.5.dp).height(44.dp))
//                    if(userData?.profilePictureUrl != null) {
//                        AsyncImage(
//                            model = userData.profilePictureUrl,
//                            contentDescription = "Profile picture",
//                            modifier = Modifier
////                                .size(150.dp)
//                                .width(44.dp)
//                                .height(44.dp)
//                                .clip(CircleShape),
//                            contentScale = ContentScale.Crop
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                    }

                        Spacer(modifier = Modifier.width(66.dp))

                        Image(imageVector = ImageVector.vectorResource(R.drawable.favourite), contentDescription = "favourite btn",
                            modifier = Modifier.clickable {  })

                        Image(imageVector = ImageVector.vectorResource(R.drawable.notification), contentDescription = "notification btn",
                            modifier = Modifier.clickable {  })
                    }

                    Spacer(modifier = Modifier.height(19.dp))

//                StandardSearchBar(modifier = Modifier.width(343.dp).height(36.dp)) {
//
//                }

                    Image(imageVector = ImageVector.vectorResource(R.drawable.searchfield), contentDescription = "seach bar",
                        modifier = Modifier.clickable {  }.width(343.dp).height(36.dp).padding(16.dp,0.dp,0.dp,0.dp))

                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(imageVector = ImageVector.vectorResource(R.drawable.upcomingbooking), contentDescription = "upcoming booking",
                    modifier = Modifier.width(351.dp).height(100.dp))

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "Suggested for you",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(0.dp,0.dp,180.dp,0.dp)
                )


//                Image(imageVector = ImageVector.vectorResource(R.drawable.suggested), contentDescription = "suggeted text",
//                        modifier = Modifier.width(176.dp).height(24.dp).padding(0.dp,0.dp,180.dp,0.dp))
//


                Spacer(modifier = Modifier.height(8.dp))


                Image(painter = painterResource(R.drawable.michael), contentDescription = "michael info",
                    modifier = Modifier.width(359.dp).height(458.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Image(painter = painterResource(R.drawable.ken), contentDescription = "ken info",
                    modifier = Modifier.width(359.dp).height(458.dp))

                Spacer(modifier = Modifier.height(12.dp))

                Image(painter = painterResource(R.drawable.benjamin), contentDescription = "ken info",
                    modifier = Modifier.width(359.dp).height(458.dp))

                Spacer(modifier = Modifier.height(100.dp))

//            Text(
//                text = "Hello, you're on the main feed screen."
//            )
            }

        }

    }
}

@Preview
@Composable
fun preb(){
//    MainFeedScreen()
}