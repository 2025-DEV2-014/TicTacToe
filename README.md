
# Tic Tac Toe

## Description

Tic-Tac-Toe, sometimes called Noughts and Crosses or Xs and Os, is one of the simplest and most classic games around! It's super quick to learn, but can be surprisingly tricky to master if you want to become a champion.

#### The Game & The Board
- Tic-Tac-Toe is played by two players on a 3-by-3 grid.
- One player uses 'X' and the other uses 'O'.

#### Rules of Play
- 'X' always goes first.
- Players alternate turns, placing one symbol into an empty square.
- A player cannot play on an already taken position.

#### Winning & Ending
The game continues until one of two things happens:
- Win Condition: If a player achieves three of their symbols in a row (horizontally, vertically, or diagonally), that player wins.
- Draw Condition: If all nine squares are filled and neither player has three in a row, the game is a draw.

## Technical
### Prerequisites
Please use a configuration that is compatible with the one used during development.
1. **Android Studio Version:** Android Studio Otter | 2025.2.1.
2. **Java Version:** Java 21
3. **Kotlin Version:** 2.2.x    
4. **Emulator:** Pixel 4, API 30 (minSDK is 24)
---  

### Dependencies
Additional dependencies can be checked and/or updated in the version catalog: `gradle/libs.versions.toml` file. (Keep them evergreen)



### Steps to Compile and Run
1. **Clone the repo:**    
   `git clone git@github.com:2025-DEV2-014/TicTacToe.git`
2. **Import the project:**
   Open the created folder in Android studio: File > Open
3. **Run the App:**  
   - Select `app` from the dropdown
   - Click the green **Play button**
4. **Possible issues:**
    - Dependencies not automatically syncing: press the Gradle sync button
    - Code won't build: Compile all sources manually, Invalidate cache & restart as last resort.

### Running Tests

- To run the unit tests, use the gradle task `Unit tests`.
- Similarly to run the UI tests, use the `TicTacToeScreenTest` task.

After making the selection (dropdown), you simply press the play-button in Android studio to run the tests. 

If you would like run the tests within a directory directly by right-clicking the `test` or `androidTest` directory and select **Run**.