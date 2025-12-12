package bnp.paribas.tictactoe.viewmodel

import bnp.paribas.tictactoe.model.Player

class GameEngine {

    internal fun checkForWinner(row: Int, col: Int, board: Array<Array<Player>>, currentPlayer: Player): Player {
        //TODO
        return Player.EMPTY
    }

    internal fun getPlayerForMove(move: Int): Player {
        return if (move % 2 == 0) Player.X
        else Player.O
    }
}