package com.example.mobilecw.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobilecw.components.AboutDialog
import com.example.mobilecw.viewModel.MyViewModel

@Composable
fun StartScreen(navController: NavHostController,viewModel: MyViewModel = viewModel()){
    var showAbout by rememberSaveable { mutableStateOf(viewModel.showAbout) }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = { navController.navigate("game")}) {
                Text(text = "New Game")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                showAbout = !showAbout
                //viewModel.toggleAboutDialog()
            }) {
                Text(text = "About")
            }
        }
    }
    if (showAbout){
        AboutDialog(onDismiss = {
            showAbout = false
            //viewModel.toggleAboutDialog()
        },"About","Id: w2051579\nName: Nadil Dinsara")
    }
}