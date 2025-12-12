package bnp.paribas.tictactoe

import bnp.paribas.tictactoe.model.Player
import bnp.paribas.tictactoe.viewmodel.GameEngine
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameEngineTest {

    private lateinit var gameEngine: GameEngine

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

}