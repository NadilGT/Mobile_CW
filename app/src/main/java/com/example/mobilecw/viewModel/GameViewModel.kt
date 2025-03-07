package com.example.mobilecw.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val _humanDice = MutableStateFlow(List(5) { Random.nextInt(1,7) })
    val humanDice: StateFlow<List<Int>> = _humanDice

    private val _computerDice = MutableStateFlow(List(5) { Random.nextInt(1,7)})
    val computerDice: StateFlow<List<Int>> = _computerDice

    fun rollDices(){
        _humanDice.value = List(5) {Random.nextInt(1,7)}
        _computerDice.value = List(5) {Random.nextInt(1,7)}
    }
}