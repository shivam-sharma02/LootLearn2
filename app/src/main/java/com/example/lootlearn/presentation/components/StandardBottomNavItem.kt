package com.example.lootlearn.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lootlearn.presentation.ui.theme.navIconColor
import com.example.lootlearn.presentation.ui.theme.navIconUnselected
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
        modifier = modifier,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unselectedColor,
        icon = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(12.dp)
                    .drawBehind {
                        if(selected){
                            drawLine(
                                color = if (selected) selectedColor
                                else unselectedColor,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Butt
                            )
                        }
                    }
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
                if(alertCount != null){
                    val alertCount = if(alertCount > 99){
                        "99+"
                    } else {
                        alertCount.toString()
                    }
                }
            }
        }
    )
}