package com.example.mobilecw.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
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
    val scoreSubmitted by viewModel.scoreSubmitted.collectAsState()

    val gameOver by viewModel.gameOver.collectAsState()

    val selectedHumanDice by viewModel.selectedHumanDice.collectAsState()

    val allHumanDiceSelected = selectedHumanDice.size == humanDice.size

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Human Player: $humanScore")
        if(rollCount >= 1){
            DiceRow(diceValues = humanDice, isHuman = true, viewModel = viewModel)  // Pass isHuman = true
        }
        if (gameOver) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Congratulations!") },
                text = { Text("You won the game!") },
                confirmButton = {
                    Button(onClick = { viewModel.resetGame() }) {
                        Text("OK")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Computer: $computerScore")
        if (rollCount >= 1){
            DiceRow(diceValues = computerDice, isHuman = false, viewModel = viewModel)  // Computer dice are not selectable
        }

        Spacer(modifier = Modifier.height(32.dp))
        Row {
            if (!allHumanDiceSelected){
                Button(
                    onClick = { viewModel.rollDices() },
                    enabled = rollCount < 3
                ) {
                    Text(text = "Throw: $rollCount")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    viewModel.updateScore()
                    viewModel.submitScore()
                          },
                enabled = rollCount in 1..2 && !scoreSubmitted
            ) {
                Text(text = "Score")
            }
        }
    }
}
