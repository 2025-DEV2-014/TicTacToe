package bnp.paribas.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bnp.paribas.tictactoe.model.GameState
import bnp.paribas.tictactoe.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

            val winner = checkWin(row, col, newBoard, currentPlayer)
            val newMoveCount = moves + 1
            val newCurrentPlayer = if (newMoveCount % 2 == 0) Player.X else Player.O

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

    private fun checkWin(row: Int, col: Int, board: Array<Array<Player>>, currentPlayer: Player): Player {

        // Check all row entries
        if (board[row].all { it == currentPlayer }) {
            return currentPlayer
        }

        // Check column
        if (board.all { it[col] == currentPlayer }) {
            return currentPlayer
        }

        // check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
            board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return currentPlayer
        }

        // No winner (yet?)
        return Player.EMPTY
    }
}