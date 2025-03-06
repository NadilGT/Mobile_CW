package com.example.mobilecw.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilecw.screens.GameDashBoard
import com.example.mobilecw.screens.StartScreen

@Composable
fun GameApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("game") { GameDashBoard() }
    }
}