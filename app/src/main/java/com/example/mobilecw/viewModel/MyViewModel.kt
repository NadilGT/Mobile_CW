package com.example.mobilecw.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var showAbout by mutableStateOf(false)
        private set

//    fun toggleAboutDialog(){
//        showAbout = !showAbout
//    }
}