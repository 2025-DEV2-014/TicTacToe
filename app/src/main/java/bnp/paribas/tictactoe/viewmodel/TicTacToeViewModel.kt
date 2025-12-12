package bnp.paribas.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bnp.paribas.tictactoe.model.GameState
import bnp.paribas.tictactoe.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TicTacToeViewModel(private val gameEngine: GameEngine = GameEngine()): ViewModel() {

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
        _gameState.value = GameState()
    }

    fun onBoardClick(row: Int, col: Int) {
        // Click has no effect if position is already taken OR the game is already over
        if (board[row][col] != Player.EMPTY || winner != Player.EMPTY) {
            return
        }

        viewModelScope.launch {
            val newBoard = board.copyOf()
            newBoard[row][col] = currentPlayer

            val winner = gameEngine.checkForWinner(row, col, newBoard, currentPlayer)
            val newMoveCount = moves + 1
            val newCurrentPlayer = gameEngine.getPlayerForMove(newMoveCount)

            _gameState.update {
                it.copy(
                    board = newBoard,
                    moves = newMoveCount,
                    currentPlayer = newCurrentPlayer,
                    winner = winner
                )
            }
        }
    }
}