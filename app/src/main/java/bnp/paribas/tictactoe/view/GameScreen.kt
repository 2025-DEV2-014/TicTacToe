package bnp.paribas.tictactoe.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bnp.paribas.tictactoe.model.Player
import bnp.paribas.tictactoe.viewmodel.TicTacToeViewModel

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel = TicTacToeViewModel()
) {

    val gameState by viewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Tic Tac Toe",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        Column {
            for (row in 0..2) {
                Row {
                    for (col in 0..2) {
                        val buttonText = when (gameState.board[row][col]) {
                            Player.X -> "X"
                            Player.O -> "O"
                            else -> " "
                        }
                        Button(
                            modifier = Modifier.size(100.dp).padding(2.dp).testTag("$col - $row"),
                            onClick = { viewModel.onBoardClick(row, col) },
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = buttonText,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        val statusText: String = when {
            gameState.winner != Player.EMPTY -> "Player ${gameState.winner} wins!"
            gameState.moves >= 9 -> "It's a draw!"
            else -> "Player ${gameState.currentPlayer}'s turn"
        }

        Text(text = statusText,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.restart()
        }) {
            Text(text = "Restart")
        }
    }
}
