# ChessGame
# Class Differences:
- Board:
The Board class is used to create a Board object that can hold Piece objects in a 2D array 
Is able to Populate/clear the current board to update the position of the pieces.
-Piece:
The Piece class is used to create different Pieces that go on a chess board like a Bishop or a Knight, etc.
Each Piece has an array inside it called Legal Moves which contain all the current legal moves it can make. 
-Game:
This is where you start the game, when you run the file, a chess game should pop up in the terminal for you to play. 
** Bug for MiniMax, Black Queen is able to capture on d2 on move 1 for some reason **
** Reinforcement AI doesn't work yet **
- Move History:
Move History is a class used when implementing the Minimax Algorithm for the first chess AI.
