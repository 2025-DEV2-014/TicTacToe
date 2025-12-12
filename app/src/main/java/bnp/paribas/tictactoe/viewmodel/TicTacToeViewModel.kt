package bnp.paribas.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import bnp.paribas.tictactoe.model.GameState
import bnp.paribas.tictactoe.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel(): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    val board: Array<Array<Player>>
        get() = gameState.value.board

    val winner: Player
        get() = gameState.value.winner

    val currentPlayer: Player
        get() = gameState.value.currentPlayer

    val moves: Int
        get() = gameState.value.moves

    fun restart() {
        //TODO: restart game
    }

    fun onBoardClick(row: Int, col: Int) {
        //TODO: handle logic board click
    }
}