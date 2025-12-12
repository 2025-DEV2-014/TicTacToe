package bnp.paribas.tictactoe

import bnp.paribas.tictactoe.viewmodel.TicTacToeViewModel
import bnp.paribas.tictactoe.model.Player
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.ranges.until

class TicTacToeViewModelTest{

    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel()
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
}