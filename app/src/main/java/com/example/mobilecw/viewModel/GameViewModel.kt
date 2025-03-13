package com.example.mobilecw.viewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.mobilecw.components.AboutDialog
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

    private val _scoreSubmitted = MutableStateFlow(false)
    val scoreSubmitted: StateFlow<Boolean> = _scoreSubmitted

    private val _gameOver = MutableStateFlow(false)
    val gameOver: StateFlow<Boolean> = _gameOver

    private val _humanWin = MutableStateFlow(false)
    val humanWin: StateFlow<Boolean> = _humanWin

    private val _computerWin = MutableStateFlow(false)
    val computerWin: StateFlow<Boolean> = _computerWin

    private val _gameTie = MutableStateFlow(false)
    val gameTie: StateFlow<Boolean> = _gameTie

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
            _scoreSubmitted.value = false

            if (_rollCount.value == 3) {
                updateScore()
            }
        }
    }

    fun updateScore() {
        _humanScore.value += _humanDice.value.sum()
        _computerScore.value += _computerDice.value.sum()
        _rollCount.value = 0

        _selectedHumanDice.value = mutableSetOf()
        _selectedComputerDice.value = mutableSetOf()

        checkWinner()
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
        println(_selectedComputerDice.value)
    }

    fun submitScore() {
        if (!_scoreSubmitted.value) {
            _scoreSubmitted.value = true  // Disable Score button after first click
        }
    }

    private fun checkWinner() {
        if (_humanScore.value >= 101 && _computerScore.value >= 101) {
            if (_humanScore.value == _computerScore.value){
                _gameTie.value = true
                _rollCount.value = 0
                _selectedHumanDice.value = mutableSetOf()
                _selectedComputerDice.value = mutableSetOf()
            } else if(_humanScore.value > _computerScore.value){
                _gameOver.value = true
                _humanWin.value = true
            } else {
                _gameOver.value = true
                _computerWin.value = true
            }
//            _gameOver.value = true
//            _humanWin.value = true
        }
        else if (_humanScore.value >= 101){
            _gameOver.value = true
            _humanWin.value = true
        }
        else if (_computerScore.value >= 101){
            _gameOver.value = true
            _computerWin.value = true
        }
    }

    fun resetGame() {
        _humanScore.value = 0
        _computerScore.value = 0
        _rollCount.value = 0
        _selectedHumanDice.value = mutableSetOf()
        _selectedComputerDice.value = mutableSetOf()
        _gameOver.value = false
    }

    fun resetRoundAfterTie() {
        _gameTie.value = false
        _rollCount.value = 0
        _selectedHumanDice.value = mutableSetOf()
        _selectedComputerDice.value = mutableSetOf()
    }
}
