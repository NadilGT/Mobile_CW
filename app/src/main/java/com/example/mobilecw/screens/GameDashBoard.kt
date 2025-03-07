package com.example.mobilecw.screens

import androidx.compose.foundation.layout.*
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
fun GameDashBoard(viewModel: GameViewModel = viewModel()) {
    val humanDice by viewModel.humanDice.collectAsState()
    val humanScore by viewModel.humanScore.collectAsState()
    val computerDice by viewModel.computerDice.collectAsState()
    val computerScore by viewModel.computerScore.collectAsState()
    val rollCount by viewModel.rollCount.collectAsState()

    val selectedHumanDice by viewModel.selectedHumanDice.collectAsState()

    val allHumanDiceSelected = selectedHumanDice.size == humanDice.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Human Player: $humanScore")
        DiceRow(diceValues = humanDice, isHuman = true, viewModel = viewModel)  // Pass isHuman = true

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Computer: $computerScore")
        DiceRow(diceValues = computerDice, isHuman = false, viewModel = viewModel)  // Computer dice are not selectable

        Spacer(modifier = Modifier.height(32.dp))
        Row {
            if (!allHumanDiceSelected){
                Button(
                    onClick = { viewModel.rollDices() },
                    enabled = rollCount < 1
                ) {
                    Text(text = "Throw")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { viewModel.updateScore() },
                enabled = rollCount < 2
            ) {
                Text(text = "Score")
            }
        }
    }
}
