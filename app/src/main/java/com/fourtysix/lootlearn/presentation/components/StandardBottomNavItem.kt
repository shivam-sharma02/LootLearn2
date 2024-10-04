package com.fourtysix.lootlearn.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.fourtysix.lootlearn.presentation.ui.theme.navIconColor
import com.fourtysix.lootlearn.presentation.ui.theme.navIconUnselected
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.fourtysix.lootlearn.R
import kotlin.jvm.Throws


@Composable
@Throws(IllegalArgumentException::class)
fun RowScope.StandardBottomNavItem(
    modifier: Modifier = Modifier,
    selected : Boolean = false,
    icon : ImageVector,
    contentDescription : String? = null,
    alertCount : Int? = null,
    enabled : Boolean = true,
    selectedColor: Color = navIconColor,
    unselectedColor: Color = navIconUnselected,
    onClick : () -> Unit
    ){
     if (alertCount != null && alertCount < 0 ){
         throw IllegalArgumentException("Alert count can't be negative")
     }

    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier.background(Color.White),
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White)
//                    .drawBehind {
//                        if(selected){
////                            drawLine(
////                                color = if (selected) selectedColor
////                                else unselectedColor,
////                                start = Offset(0f, size.height),
////                                end = Offset(size.width, size.height),
////                                strokeWidth = 2.dp.toPx(),
////                                cap = StrokeCap.Butt
////                            )
//                        }
//                    }
                ,contentAlignment = Alignment.Center
            ){

                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){


                    if(selected){
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.navrectangle), contentDescription = "nav rectangle",
                            modifier = Modifier.width(60.dp).height(6.dp)
                        )
                    }

                    Icon(
                        imageVector = icon,
                        contentDescription = contentDescription,
                        modifier = Modifier.width(48.dp).height(48.dp)
                    )

                    if(alertCount != null){
                        val alertCount = if(alertCount > 99){
                            "99+"
                        } else {
                            alertCount.toString()
                        }
//                    Text(
//                        text = alertCount,
//                        color = Color.White,
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(errorRed)
//                            .align(Alignment.TopEnd)
//                    )
                    }

                }
            }
        }
    )
}