package bnp.paribas.tictactoe.viewmodel

import bnp.paribas.tictactoe.model.Player

class GameEngine {

    internal fun checkForWinner(row: Int, col: Int, board: Array<Array<Player>>, currentPlayer: Player): Player {
        // Check all row entries
        if (board[row].all { it == currentPlayer }) {
            return currentPlayer
        }

        // Check column
        if (board.all { it[col] == currentPlayer }) {
            return currentPlayer
        }

        // check diagonals
        if (board[1][1] != Player.EMPTY && (
                    board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                            board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return currentPlayer
        }

        // No winner (yet?)
        return Player.EMPTY
    }

    internal fun getPlayerForMove(move: Int): Player {
        return if (move % 2 == 0) Player.X
        else Player.O
    }
}