package bnp.paribas.tictactoe.model

data class GameState (
    val board: Array<Array<Player>> = Array(3) {Array(3) { Player.EMPTY }},
    val moves: Int = 0,
    val currentPlayer: Player = Player.X,
    val winner: Player = Player.EMPTY,
) {
    val statusText: String
        get() = when {
            winner != Player.EMPTY -> "Player ${winner} wins!"
            moves >= 9 -> "It's a draw!"
            else -> "Player ${currentPlayer}'s turn"
        }
}