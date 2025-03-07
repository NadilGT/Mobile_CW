package com.example.mobilecw.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecw.components.DiceRow
import com.example.mobilecw.viewModel.GameViewModel

@Composable
fun GameDashBoard(viewModel: GameViewModel = viewModel()){
    val humanDice by viewModel.humanDice.collectAsState()
    val computerDice by viewModel.computerDice.collectAsState()
    
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Human Player")
        DiceRow(diceValues = humanDice)
        
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Computer")
        DiceRow(diceValues = computerDice)

        Spacer(modifier = Modifier.height(32.dp))
        Row {
            Button(onClick = {viewModel.rollDices()}) {
                Text(text = "Throw")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Score")
            }
        }
    }
}