package com.example.mobilecw.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobilecw.R

@Composable
fun DiceRow(diceValues: List<Int>){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        diceValues.forEach { value ->
            Image(
                painter = painterResource(id = getDiceImage(value)),
                contentDescription = "Dice $value",
                modifier = Modifier.size(64.dp).padding(4.dp)
            )
        }
    }
}

fun getDiceImage(value: Int):Int{
    return when (value){
        1 -> R.drawable.dice1
        2 -> R.drawable.dice2
        3 -> R.drawable.dice3
        4 -> R.drawable.dice4
        5 -> R.drawable.dice5
        6 -> R.drawable.dice6
        else -> {R.drawable.dice1}
    }
}