package bnp.paribas.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import bnp.paribas.tictactoe.view.GameScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TicTacToeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GameScreen()
        }
    }

    @Test
    fun when_the_game_starts_it_is_turn_of_player_X() {
        composeTestRule
            .onNodeWithText("Player X's turn")
            .assertExists()
    }

    @Test
    fun when_the_game_starts_the_board_is_empty() {
        composeTestRule
            .onAllNodesWithText(" ")
            .assertCountEquals(9)
    }
}