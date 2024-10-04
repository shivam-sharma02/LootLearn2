package com.fourtysix.lootlearn.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lootlearn.presentation.components.StandardScaffold
import com.example.lootlearn.presentation.components.StandardScaffoldViewModel



@Composable
fun NewItemScreen(navController: NavController){
    val viewModel: StandardScaffoldViewModel = hiltViewModel()
    StandardScaffold(
        navController = navController,
        viewModel = viewModel,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
            }
        }
    )
}