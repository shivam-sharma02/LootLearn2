package com.example.lootlearn.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.lootlearn.R

@Composable
fun StandardScaffold(content : @Composable (PaddingValues) -> Unit){
    Scaffold (
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth().navigationBarsPadding(),
                cutoutShape = CircleShape,
                elevation = 5.dp,
                backgroundColor = Color.White
            ) {
                BottomNavigation(
                    modifier = Modifier.fillMaxWidth().navigationBarsPadding()
                ) {
                    StandardBottomNavItem(
                        icon = ImageVector.vectorResource(R.drawable.homeicon),
                        selected = true,
                        alertCount = 50
                    ) { }

                    StandardBottomNavItem(
                        icon = ImageVector.vectorResource(R.drawable.bookingiconsvg),
                        selected = false,
                        alertCount = 50
                    ) { }

                    StandardBottomNavItem(
                        icon = ImageVector.vectorResource(R.drawable.newicon),
                        selected = false,
                        alertCount = 50
                    ) { }

                    StandardBottomNavItem(
                        icon = ImageVector.vectorResource(R.drawable.walleticon),
                        selected = false,
                        alertCount = 50
                    ) { }

                    StandardBottomNavItem(
                        icon = ImageVector.vectorResource(R.drawable.chaticonsvg),
                        selected = false,
                        alertCount = 50
                    ) { }
                }
            }
        },
        content = content,
        containerColor = Color.White
    )
}