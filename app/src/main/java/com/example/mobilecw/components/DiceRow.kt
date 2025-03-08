package com.example.mobilecw.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecw.R
import com.example.mobilecw.viewModel.GameViewModel

@Composable
fun DiceRow(diceValues: List<Int>, isHuman: Boolean, viewModel: GameViewModel = viewModel()) {
    val selectedHumanDice by viewModel.selectedHumanDice.collectAsState()
    val selectedComputerDice by viewModel.selectedComputerDice.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        diceValues.forEachIndexed { index, value ->
            val isSelected = if (isHuman) selectedHumanDice.contains(index) else selectedComputerDice.contains(index)

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(
                        if (isSelected) Color.Gray else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(3.dp)
                    .clickable{
                        if (isHuman) viewModel.toggleHumanDiceSelection(index)
                        else viewModel.toggleComputerDiceSelection(index)
                    }
            ) {
                Image(
                    painter = painterResource(id = getDiceImage(value)),
                    contentDescription = "Dice $value",
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

fun getDiceImage(value: Int): Int {
    return when (value) {
        1 -> R.drawable.dice1
        2 -> R.drawable.dice2
        3 -> R.drawable.dice3
        4 -> R.drawable.dice4
        5 -> R.drawable.dice5
        6 -> R.drawable.dice6
        else -> R.drawable.dice1
    }
}
