package bnp.paribas.tictactoe

import bnp.paribas.tictactoe.model.GameState
import bnp.paribas.tictactoe.model.Player
import org.junit.Assert.assertEquals
import org.junit.Test

class GameStateTest {

    @Test
    fun `When the game has a winner, the statustext is correct`() {
        val state = GameState(moves = 5, currentPlayer = Player.X, winner = Player.X )
        assertEquals(state.statusText, "Player X wins!")
    }

    @Test
    fun `When the game has a winner, the statustext is correct (even if moves exceed limit)`() {
        val state = GameState(moves = 9, currentPlayer = Player.X, winner = Player.X )
        assertEquals(state.statusText, "Player X wins!")
    }

    @Test
    fun `When the game has no winner and it is not over, the statustext indicates the correct player`() {
        val state = GameState(moves = 5, currentPlayer = Player.X, winner = Player.EMPTY )
        assertEquals(state.statusText, "Player X's turn")

        val secondState = GameState(moves = 5, currentPlayer = Player.O, winner = Player.EMPTY )
        assertEquals(secondState.statusText, "Player O's turn")
    }

    @Test
    fun `When the game has no winner and it is over, the statustext indicates that is it a draw`() {
        val secondState = GameState(moves = 9, currentPlayer = Player.O, winner = Player.EMPTY )
        assertEquals(secondState.statusText, "It's a draw!")
    }

}