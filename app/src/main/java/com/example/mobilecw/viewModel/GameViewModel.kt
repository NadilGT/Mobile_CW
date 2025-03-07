package com.example.mobilecw.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val _humanDice = MutableStateFlow(List(5) { Random.nextInt(1,7) })
    val humanDice: StateFlow<List<Int>> = _humanDice

    private val _humanScore = MutableStateFlow(0)
    val humanScore: StateFlow<Int> = _humanScore

    private val _computerDice = MutableStateFlow(List(5) { Random.nextInt(1,7)})
    val computerDice: StateFlow<List<Int>> = _computerDice

    private val _computerScore = MutableStateFlow(0)
    val computerScore: StateFlow<Int> = _computerScore

    private val _rollCount = MutableStateFlow(0)
    val rollCount: StateFlow<Int> = _rollCount

    fun rollDices(){
        _humanDice.value = List(5) {Random.nextInt(1,7)}
        _computerDice.value = List(5) {Random.nextInt(1,7)}
        _rollCount.value += 1
    }

    fun updateScore(){
        _humanScore.value += _humanDice.value.sum()
        _computerScore.value += _computerDice.value.sum()
        _rollCount.value = 0
    }
}