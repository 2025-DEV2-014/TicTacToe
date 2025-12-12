package bnp.paribas.tictactoe

import bnp.paribas.tictactoe.model.Player
import bnp.paribas.tictactoe.viewmodel.GameEngine
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameEngineTest {

    private lateinit var gameEngine: GameEngine
    private val emptyBoard = Array(3) { Array(3) { Player.EMPTY } }

    @Before
    fun setUp() {
        gameEngine = GameEngine()
    }

    // player rotation checks
    @Test
    fun `When the next player is selected after an even turn, it should be 0`() {
        val player = gameEngine.getPlayerForMove(0)
        assertEquals(Player.X, player)
    }

    @Test
    fun `When the next player is selected after an uneven turn, it should be X`() {
        val player = gameEngine.getPlayerForMove(1)
        assertEquals(Player.O, player)
    }

    // no valid winner checks (unhappy path)
    @Test
    fun `When the checking for a winner on an empty board, it should not return one`() {
        val winner = gameEngine.checkForWinner(0,0, emptyBoard, Player.X)
        assertEquals(Player.EMPTY, winner)
    }

    @Test
    fun `When the checking for a winner with a random board, it should not return one`() {
        val rowBoard = emptyBoard
        rowBoard[2][0] = Player.X
        rowBoard[0][2] = Player.O
        rowBoard[1][1] = Player.X

        val winner = gameEngine.checkForWinner(0,0, rowBoard, Player.X)
        assertEquals(Player.EMPTY, winner)
    }

    // valid winner checks (happy path)
    @Test
    fun `When the checking for a winner with a filled column, it should return one`() {
        val colBoard = emptyBoard
        colBoard[0][0] = Player.X
        colBoard[1][0] = Player.X
        colBoard[2][0] = Player.X

        val winner = gameEngine.checkForWinner(0,0, colBoard, Player.X)
        assertEquals(Player.X, winner)
    }

    @Test
    fun `When the checking for a winner with a filled row, it should return one`() {
        val rowBoard = emptyBoard
        rowBoard[0][0] = Player.X
        rowBoard[0][1] = Player.X
        rowBoard[0][2] = Player.X

        val winner = gameEngine.checkForWinner(0,0, rowBoard, Player.X)
        assertEquals(Player.X, winner)
    }

    @Test
    fun `When the checking for a winner with a filled diagonal (downward), it should return one`() {
        val rowBoard = emptyBoard
        rowBoard[0][0] = Player.X
        rowBoard[1][1] = Player.X
        rowBoard[2][2] = Player.X

        val winner = gameEngine.checkForWinner(0,0, rowBoard, Player.X)
        assertEquals(Player.X, winner)
    }

    @Test
    fun `When the checking for a winner with a filled diagonal (upward), it should return one`() {
        val rowBoard = emptyBoard
        rowBoard[2][0] = Player.X
        rowBoard[1][1] = Player.X
        rowBoard[0][2] = Player.X

        val winner = gameEngine.checkForWinner(0,0, rowBoard, Player.X)
        assertEquals(Player.X, winner)
    }

}