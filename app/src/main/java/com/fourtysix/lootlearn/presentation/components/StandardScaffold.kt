package com.example.lootlearn.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lootlearn.model.BottomNavItem
import com.fourtysix.lootlearn.R
import com.fourtysix.lootlearn.presentation.components.StandardBottomNavItem
import com.fourtysix.lootlearn.utils.Screen



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StandardScaffold(
    navController: NavController,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StandardScaffoldViewModel = hiltViewModel(),
    bottomNavItem: List<BottomNavItem> = listOf(
        BottomNavItem(
            route = Screen.MainFeedScreen.route,
            icon = ImageVector.vectorResource(R.drawable.homeicon),
            contentDescription = "Home"
        ),
        BottomNavItem(
            route = Screen.BookingScreen.route,
            icon = ImageVector.vectorResource(R.drawable.bookingiconsvg),
            contentDescription = "Booking"
        ),
        BottomNavItem(
            route = Screen.NewItemScreen.route,
            icon = ImageVector.vectorResource(R.drawable.newicon),
            contentDescription = "New"
        ),
        BottomNavItem(
            route = Screen.WalletScreen.route,
            icon = ImageVector.vectorResource(R.drawable.walleticon),
            contentDescription = "wallet"
        ),
        BottomNavItem(
            route = Screen.ChatScreen.route,
            icon = ImageVector.vectorResource(R.drawable.chaticonfinal),
            contentDescription = "chat"
        ),
    )
){
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

                    bottomNavItem.forEachIndexed{ i, bottomNavItem ->
                        bottomNavItem.icon?.let {
                            StandardBottomNavItem(
                                icon = it,
                                contentDescription = bottomNavItem.contentDescription,
                                selected = bottomNavItem.route == navController.currentDestination?.route,
                                alertCount = bottomNavItem.alertCount
                                ) {
                                viewModel.setSelectedBottomNavItem(i)
                                navController.navigate(bottomNavItem.route){
                                    // Clear the back stack if necessary
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
//                    StandardBottomNavItem(
//                        icon = ImageVector.vectorResource(R.drawable.homeicon),
//                        selected = true,
//                        alertCount = 50
//                    ) { }
//
//                    StandardBottomNavItem(
//                        icon = ImageVector.vectorResource(R.drawable.bookingiconsvg),
//                        selected = false,
//                        alertCount = 50
//                    ) { }
//
//                    StandardBottomNavItem(
//                        icon = ImageVector.vectorResource(R.drawable.newicon),
//                        selected = false,
//                        alertCount = 50
//                    ) { }
//
//                    StandardBottomNavItem(
//                        icon = ImageVector.vectorResource(R.drawable.walleticon),
//                        selected = false,
//                        alertCount = 50
//                    ) { }
//
//                    StandardBottomNavItem(
//                        icon = ImageVector.vectorResource(R.drawable.chaticonfinal),
//                        selected = false,
//                        alertCount = 50
//                    ) { }
                }
            }
        },
        containerColor = Color.White
    ){
        content()
    }
}