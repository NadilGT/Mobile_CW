package com.example.mobilecw.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobilecw.R
import com.example.mobilecw.components.AboutDialog
import com.example.mobilecw.ui.theme.Blue
import com.example.mobilecw.ui.theme.red
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
            Image(painter = painterResource(id = R.drawable.dice), contentDescription = "Dice")
            Spacer(modifier = Modifier.height(36.dp))
            Button(onClick = { navController.navigate("game")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Text(
                    text = "New Game",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                showAbout = !showAbout
                //viewModel.toggleAboutDialog()
            },modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = red)
                ) {
                Text(
                    text = "About",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
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