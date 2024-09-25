package com.example.lootlearn.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lootlearn.presentation.ui.theme.navIconColor
import com.example.lootlearn.presentation.ui.theme.navIconUnselected
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
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
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription
                )
            }
        }
    )
}