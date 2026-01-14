package bnp.paribas.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import bnp.paribas.tictactoe.model.GameState
import bnp.paribas.tictactoe.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(private val gameEngine: GameEngine = GameEngine()): ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState = _gameState.asStateFlow()

    fun restart() {
        _gameState.value = GameState()
    }

    fun onBoardClick(row: Int, col: Int) {
        _gameState.update { state ->
            // Click has no effect if position is already taken OR the game is already over
            if (state.board[row][col] != Player.EMPTY || state.winner != Player.EMPTY) {
                return@update state
            }

            val newBoard = state.board.copyOf()
            newBoard[row][col] = state.currentPlayer

            val winner = gameEngine.checkForWinner(row, col, newBoard, state.currentPlayer)
            val newMoveCount = state.moves + 1
            val newCurrentPlayer = gameEngine.getPlayerForMove(newMoveCount)

            state.copy(
                board = newBoard,
                moves = newMoveCount,
                currentPlayer = newCurrentPlayer,
                winner = winner
            )
        }
    }
}