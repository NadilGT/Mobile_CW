package com.example.mobilecw.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecw.components.DiceRow
import com.example.mobilecw.ui.theme.Blue
import com.example.mobilecw.ui.theme.Green
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
    val humanWin by viewModel.humanWin.collectAsState()
    val computerWin by viewModel.computerWin.collectAsState()

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
        if (gameOver && humanWin) {
            AlertDialog(
                onDismissRequest = {},
                text = { Text("You Win!",
                    color = Color.Green,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                       },
                confirmButton = {
                    Button(onClick = { viewModel.resetGame() }) {
                        Text("OK")
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .width(200.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = Color.White
            )
        }
        else if (gameOver && computerWin){
            AlertDialog(
                onDismissRequest = {},
                text = { Text("You Lose!",
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                },
                confirmButton = {
                    Button(onClick = { viewModel.resetGame() }) {
                        Text("OK")
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .width(200.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = Color.White
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Computer: $computerScore")
        if (rollCount >= 1){
            DiceRow(diceValues = computerDice, isHuman = false, viewModel = viewModel)  // Computer dice are not selectable
        }

        Spacer(modifier = Modifier.height(32.dp))
        Column (
            verticalArrangement = Arrangement.Center
        ){
            if (!allHumanDiceSelected){
                Button(
                    onClick = { viewModel.rollDices() },
                    enabled = rollCount < 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    if (rollCount < 1){
                        Text(
                            text = "Throw: $rollCount",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    else{
                        Text(
                            text = "Re-roll: $rollCount",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    viewModel.updateScore()
                    viewModel.submitScore()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                enabled = rollCount in 1..2 && !scoreSubmitted
            ) {
                Text(
                    text = "Score",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
