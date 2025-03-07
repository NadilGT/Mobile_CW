package com.example.mobilecw.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _humanDice = MutableStateFlow(List(5) { Random.nextInt(1, 7) })
    val humanDice: StateFlow<List<Int>> = _humanDice

    private val _humanScore = MutableStateFlow(0)
    val humanScore: StateFlow<Int> = _humanScore

    private val _computerDice = MutableStateFlow(List(5) { Random.nextInt(1, 7) })
    val computerDice: StateFlow<List<Int>> = _computerDice

    private val _computerScore = MutableStateFlow(0)
    val computerScore: StateFlow<Int> = _computerScore

    private val _rollCount = MutableStateFlow(0)
    val rollCount: StateFlow<Int> = _rollCount

    private val _selectedHumanDice = MutableStateFlow(mutableSetOf<Int>())  // Only human dice
    val selectedHumanDice: StateFlow<Set<Int>> = _selectedHumanDice

    private val _selectedComputerDice = MutableStateFlow(mutableSetOf<Int>())
    val selectedComputerDice: StateFlow<Set<Int>> = _selectedComputerDice

    fun rollDices() {
        if (_rollCount.value < 3) {
            _humanDice.value = _humanDice.value.mapIndexed { index, value ->
                if (_selectedHumanDice.value.contains(index)) value
                else Random.nextInt(1, 7)
            }
            _computerDice.value = _computerDice.value.mapIndexed { index, value ->
                if (_selectedComputerDice.value.contains(index)) value
                else Random.nextInt(1,7)
            }
            _rollCount.value += 1

            if (_rollCount.value == 3) {
                updateScore()
            }
        }
    }

    fun updateScore() {
        _humanScore.value += _humanDice.value.sum()
        _computerScore.value += _computerDice.value.sum()
        _rollCount.value = 0
    }

    fun toggleHumanDiceSelection(index: Int) {
        _selectedHumanDice.value = _selectedHumanDice.value.toMutableSet().apply {
            if (contains(index)) remove(index) else add(index)
        }
    }
    fun toggleComputerDiceSelection(index: Int) {
        _selectedComputerDice.value = _selectedComputerDice.value.toMutableSet().apply {
            if (contains(index)) remove(index) else add(index)
        }
    }
}
