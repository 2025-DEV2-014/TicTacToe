package bnp.paribas.tictactoe

import bnp.paribas.tictactoe.viewmodel.TicTacToeViewModel
import bnp.paribas.tictactoe.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.ranges.until

class TicTacToeViewModelTest{

    private lateinit var viewModel: TicTacToeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel()
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When the game starts then X plays first`() {
        assertEquals(Player.X, viewModel.currentPlayer)
    }

    @Test
    fun `When the game starts then the board should be empty`() {
        for (i in 0 until viewModel.board.size) {
            for (j in 0 until viewModel.board[i].size) {
                assertEquals(viewModel.board[i][j], Player.EMPTY)
            }
        }
    }

    @Test
    fun `Game correctly switches between players`(){
        viewModel.onBoardClick(0,0)

        assertEquals(Player.O, viewModel.currentPlayer)
        assertEquals(viewModel.board[0][0], Player.X)

        viewModel.onBoardClick(0,1)

        assertEquals(Player.X, viewModel.currentPlayer)
        assertEquals(viewModel.board[0][1], Player.O)
    }

    @Test
    fun `A player is not able to select an already taken square`() {
        viewModel.onBoardClick(0,0)
        viewModel.onBoardClick(0,0)

        assertEquals(Player.X, viewModel.board[0][0])
        assertEquals(Player.O, viewModel.currentPlayer)
    }

    @Test
    fun `When game is restarted during game then values are reset`() {
        viewModel.onBoardClick(1,1)
        viewModel.onBoardClick(1,2)
        viewModel.restart()

        // affected ones are empty
        assertEquals(viewModel.board[1][1], Player.EMPTY)
        assertEquals(viewModel.board[1][2], Player.EMPTY)
        // others as well (still)
        assertEquals(viewModel.board[0][0], Player.EMPTY)
        assertEquals(viewModel.board[2][2], Player.EMPTY)

        assertEquals(Player.X, viewModel.currentPlayer)
    }

    @Test
    fun `When board is full without a winning condition, then no winner is selected`() {
        /**
         * X X O
         * O O X
         * X X O
         */
        // center column
        viewModel.onBoardClick(0,1)
        viewModel.onBoardClick(1,1)
        viewModel.onBoardClick(2,1)
        // end column
        viewModel.onBoardClick(0,2)
        viewModel.onBoardClick(1,2)
        viewModel.onBoardClick(2,2)
        // start column
        viewModel.onBoardClick(0,0)
        viewModel.onBoardClick(1,0)
        viewModel.onBoardClick(2,0)

        assertEquals(Player.EMPTY, viewModel.winner)
    }


    @Test
    fun `When a player wins by row, then a winner is selected`() {
        /**
         * X X X
         * O O .
         * . . .
         */
        viewModel.onBoardClick(0, 0)
        viewModel.onBoardClick(1, 0)
        viewModel.onBoardClick(0, 1)
        viewModel.onBoardClick(1, 1)
        viewModel.onBoardClick(0, 2)

        assertEquals(Player.X, viewModel.winner)
    }

    @Test
    fun `When a player wins by column, then a winner is selected`() {
        /**
         * X O .
         * X O .
         * X . .
         */
        viewModel.onBoardClick(0, 0)
        viewModel.onBoardClick(0, 1)
        viewModel.onBoardClick(1, 0)
        viewModel.onBoardClick(1, 1)
        viewModel.onBoardClick(2, 0)

        assertEquals(Player.X, viewModel.winner)
    }

    @Test
    fun `When a player wins by diagonal (downward), then a winner is selected`() {
        /**
         * O X X
         * . O X
         * . . O
         */
        viewModel.onBoardClick(0, 1)
        viewModel.onBoardClick(0, 0)
        viewModel.onBoardClick(0, 2)
        viewModel.onBoardClick(1, 1)
        viewModel.onBoardClick(1, 2)
        viewModel.onBoardClick(2, 2)

        assertEquals(Player.O, viewModel.winner)
    }

    @Test
    fun `When a player wins by diagonal (upward), then a winner is selected`() {
        /**
         * X X O
         * . O X
         * O . .
         */
        viewModel.onBoardClick(0, 0)
        viewModel.onBoardClick(1, 1)
        viewModel.onBoardClick(0, 1)
        viewModel.onBoardClick(0, 2)
        viewModel.onBoardClick(1, 2)
        viewModel.onBoardClick(2, 0)

        assertEquals(Player.O, viewModel.winner)
    }
}