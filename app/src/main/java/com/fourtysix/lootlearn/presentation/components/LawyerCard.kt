package com.fourtysix.lootlearn.presentation.components

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fourtysix.lootlearn.R
import com.fourtysix.lootlearn.presentation.ui.theme.ButtonBackground
import com.fourtysix.lootlearn.presentation.ui.theme.Poppins
import com.fourtysix.lootlearn.presentation.ui.theme.cardDetailGrey

@Composable
fun LawyerCard(
    name : String,
    profileImage : Painter,
    aboutText : String,
    skills : String,
    languages : String,
    rating : String
){

    Column (
        modifier = Modifier
            .width(359.dp)
            .height(458.dp)
            .shadow(5.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(8.dp,8.dp,8.dp,16.dp)
    ){

        Box(

            modifier = Modifier.width(343.dp).height(228.dp)
        ) {
            Image(painter = profileImage, contentDescription = "michael's profile picture",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .width(343.dp)
                    .height(228.dp)
            )

            Image(imageVector = ImageVector.vectorResource(R.drawable.greensign), contentDescription = "Green indicator",
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
            )


            Column (
                modifier = Modifier.width(331.dp).height(108.dp)
                    .padding(299.dp,12.dp,0.dp,0.dp),
                verticalArrangement = Arrangement.Center
            ){

                Image(imageVector = ImageVector.vectorResource(R.drawable.cardfavourite), contentDescription = "favourite button",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp).clickable {  }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(imageVector = ImageVector.vectorResource(R.drawable.cardshare), contentDescription = "favourite button",
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp).clickable {  } )

            }



        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .width(343.dp)
                .height(24.dp).padding(6.dp,1.5.dp,0.dp,1.5.dp).weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.width(2.dp))

            Image(painter = painterResource(R.drawable.verifiedicon), contentDescription = "verified signature",
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Image(imageVector = ImageVector.vectorResource(R.drawable.usflag), contentDescription = "country flag",
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))


            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp,0.dp,6.dp,0.dp)
            ){
                Image(imageVector = ImageVector.vectorResource(R.drawable.ratingstar), contentDescription = "rating star",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = "$rating",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(600),
                        fontSize = 13.sp
                    )
                )
            }


        }


        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .width(343.dp)
                .height(106.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(cardDetailGrey)
                .padding(0.dp,6.dp,0.dp,6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "\"$aboutText\"" ,
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                ),
                color = Color(0xFF10002B),
                modifier = Modifier.width(323.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Skills : $skills",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                ),
                color = Color(0xFF3A3A3A),
                modifier = Modifier.width(213.dp).height(21.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Speaks : $languages",
                style = TextStyle(
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 13.sp
                ),
                color = Color(0xFF3A3A3A),
                modifier = Modifier.width(213.dp).height(21.dp),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Column {
                Text(
                    text = "$ 2.5 USD/min",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp
                    ),
                    color = Color(0xFF373F5A),
                    modifier = Modifier.width(129.dp).height(26.dp),
                    textAlign = TextAlign.Center,
                )

                Text(
                    text = "*First 5 min free",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight(500),
                        fontSize = 10.sp
                    ),
                    color = Color(0xFF05B92B),
                    modifier = Modifier.width(100.dp).height(16.dp).padding(7.dp,0.dp,0.dp,0.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(67.dp))

            Box(
                modifier = Modifier
                    .width(131.dp)
                    .height(37.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(ButtonBackground),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Aligns the icon and text in the center
                    modifier = Modifier.clickable(onClick = { /* Your click action */ })
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.sendicon), // Replace with your icon resource
                        contentDescription = "Leading Icon",
                        modifier = Modifier.width(18.dp).height(18.dp) // Adjust size if necessary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Message",
                        style = TextStyle(
                            fontFamily = Poppins,
                            fontWeight = FontWeight(500)
                        ),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

        }



    }

}

@Preview
@Composable
fun previewLayerCard(){
//    LawyerCard("Michael Johnson")
}