package com.example.lootlearn.presentation.screens

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.lootlearn.R
import com.example.lootlearn.presentation.ui.theme.GradientColors
import com.example.lootlearn.utils.Screen
import com.example.lootlearn.utils.gradientBrush
import kotlinx.coroutines.delay

@SuppressLint("ResourceType")
@Composable
fun SplashScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = gradientBrush(GradientColors, true)
            ),
//            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context).data(data = R.raw.lootlearnsplash).apply(block = {
                        size(Size(650, 400))
                    }).build(), imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )


//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.applelogo), text = "Continue with Apple", backgroundColor = Color.Black,textColor = Color.White )
//            Spacer(modifier = Modifier.height(5.dp))
//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.facebooklogo), text = "Continue with Facebook", backgroundColor = FacebookBackgroundColor,textColor = Color.White )
//            Spacer(modifier = Modifier.height(5.dp))
//            StandardSocialAuthButton(logo = painterResource(id = R.drawable.googlelogo), text = "Continue with Google", backgroundColor = Color.White,textColor = GoogleTextContentColor )
//            Spacer(modifier = Modifier.height(5.dp))
//
//            StandardButton(buttonText = "Continue")
            LaunchedEffect(key1 = true){

                delay(2200)
                navController.popBackStack()
                navController.navigate(Screen.AuthChoiceScreen.route)
                
            }

        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun splashPreview(){
//    SplashScreen()
//}