package com.example.mobilecw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mobilecw.navigation.GameApp
import com.example.mobilecw.ui.theme.MobileCWTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileCWTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ){
                    GameApp()
                }
            }
        }
    }
}