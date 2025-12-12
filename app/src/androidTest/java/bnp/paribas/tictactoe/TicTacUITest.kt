package bnp.paribas.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import bnp.paribas.tictactoe.view.GameScreen
import bnp.paribas.tictactoe.viewmodel.TicTacToeViewModel
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
            GameScreen(
                viewModel = TicTacToeViewModel()
            )
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

    @Test
    fun when_playing_then_turn_should_switch_and_be_represented_correctly() {
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 0").assertTextEquals("X")

        // second player turn
        composeTestRule.onNodeWithText("Player O's turn").assertExists()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("0 - 1").assertTextEquals("O")

        composeTestRule.onNodeWithText("Player X's turn").assertExists()
    }

    @Test
    fun when_selecting_a_taken_field_it_should_have_no_effect() {
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 0").performClick()

        composeTestRule.onNodeWithTag("0 - 0").assertTextEquals("X")
        // turn didn't change yet
        composeTestRule.onNodeWithText("Player O's turn").assertExists()
    }

    @Test
    fun when_restarting_screen_is_cleaned_up() {
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithText("Restart").performClick()

        composeTestRule.onNodeWithTag("0 - 0").assertTextEquals(" ")
        composeTestRule.onNodeWithTag("0 - 1").assertTextEquals(" ")
    }

    @Test
    fun when_having_full_board_it_should_be_draw() {
        /**
         * X X O
         * O O X
         * X X O
         */
        // center column
        composeTestRule.onNodeWithTag("1 - 0").performClick()
        composeTestRule.onNodeWithTag("1 - 1").performClick()
        composeTestRule.onNodeWithTag("1 - 2").performClick()
        // end column
        composeTestRule.onNodeWithTag("2 - 0").performClick()
        composeTestRule.onNodeWithTag("2 - 1").performClick()
        composeTestRule.onNodeWithTag("2 - 2").performClick()
        // start column
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("0 - 2").performClick()

        composeTestRule.onNodeWithText("It's a draw!").assertExists()
    }

    @Test
    fun when_winning_by_column_message_should_show() {
        /**
         * X O .
         * X O .
         * X . .
         */
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("1 - 0").performClick()
        composeTestRule.onNodeWithTag("1 - 1").performClick()
        composeTestRule.onNodeWithTag("2 - 0").performClick()

        composeTestRule.onNodeWithText("Player X wins!").assertExists()
    }

    @Test
    fun when_winning_by_row_message_should_show() {
        /**
         * X X X
         * O O .
         * . . .
         */
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("1 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("1 - 1").performClick()
        composeTestRule.onNodeWithTag("0 - 2").performClick()

        composeTestRule.onNodeWithText("Player X wins!").assertExists()
    }

    @Test
    fun when_winning_by_diagonal_message_should_show() {
        /**
         * X O .
         * . X O
         * . . X
         */
        composeTestRule.onNodeWithTag("0 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("1 - 1").performClick()
        composeTestRule.onNodeWithTag("2 - 1").performClick()
        composeTestRule.onNodeWithTag("2 - 2").performClick()

        composeTestRule.onNodeWithText("Player X wins!").assertExists()
    }

    @Test
    fun when_winning_by_diagonal_upward_message_should_show() {
        /**
         * . O X
         * . X .
         * X O .
         */
        composeTestRule.onNodeWithTag("2 - 0").performClick()
        composeTestRule.onNodeWithTag("0 - 1").performClick()
        composeTestRule.onNodeWithTag("1 - 1").performClick()
        composeTestRule.onNodeWithTag("2 - 1").performClick()
        composeTestRule.onNodeWithTag("0 - 2").performClick()

        composeTestRule.onNodeWithText("Player X wins!").assertExists()
    }


}