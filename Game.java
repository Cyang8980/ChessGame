import java.util.*;
public class Game{

	private static Stack <MoveHistory> states = new Stack<>();
	private Board board; // current board
	private ArrayList<Player> players = new ArrayList<Player>(); // list of players
	public static Player black;
	public static boolean popped = false;
	public Game() {
	}

	public Board getBoard() {
		return board;
	}
	public ArrayList<Player> getPlayers() {
		return players;
	}

	// public static Move AlphaBetaSearch(Move state, int depth, double maxValue, double minValue, String color) {
	// 	Move mover = null;
	// 	for (Piece p: state.getBoard().getPiecesColor(color)) {
    //         Move newMover = new Move(state);
	// 		if (color.equals("black")) {
	// 			ArrayList<ArrayList<Integer>> legalMoves = state.legalPieceMoves(p, false, "black");
	// 			ArrayList<Integer> row = legalMoves.get(0);
	// 			ArrayList<Integer> col = legalMoves.get(1);
	// 			mover = Mini(depth-1,row,col,newMover, maxValue);
	// 			System.out.println("MIN'S BOARD");
	// 			Board tempBoard = mover.getBoard();
	// 			tempBoard.updateGameBoard();
	// 			tempBoard.populateBoard();
	// 		}
    //         else { // white
	// 			ArrayList<ArrayList<Integer>> legalMoves = mover.legalPieceMoves(p, false, "white");
	// 			ArrayList<Integer> row = legalMoves.get(0);
	// 			ArrayList<Integer> col = legalMoves.get(1);
	// 			mover = Max(depth-1,row,col,newMover, minValue);
	// 			System.out.println("MAX'S BOARD");
	// 			Board tempBoard = mover.getBoard();
	// 			tempBoard.updateGameBoard();
	// 			tempBoard.populateBoard();
	// 		}
	// 	}
	// 	return mover;
	// }
	public static Move AlphaBetaSearch(Move state, int depth, double maxValue, double minValue, String color) {
		Move bestMove = null;
		for (Piece p : state.getBoard().getPiecesColor(color)) {
			ArrayList<ArrayList<Integer>> legalMoves = state.legalPieceMoves(p, false, color);
			for (int m = 0; m < legalMoves.get(0).size(); m++) {
				int row = legalMoves.get(0).get(m);
				int col = legalMoves.get(1).get(m);
				Move testMove = new Move(state);
				testMove.moveBot(p.getRow(), p.getCol(), row, col);
				Move result;
				if (color.equals("black")) {
					result = Mini(depth - 1, testMove, maxValue, minValue);
				} else {
					result = Max(depth - 1, testMove, maxValue, minValue);
				}
				if (bestMove == null || result.getBoard().evaluate() > bestMove.getBoard().evaluate()) {
					bestMove = result;
				}
			}
		}
		return bestMove;
	}

    // public static Move Max(double depth, ArrayList<Integer> row, ArrayList<Integer> col, Move mover, double minValue) {
	// 	System.out.println("in max function");
	// 	Move resultMover = new Move(mover);
	// 	double max = minValue;
	// 	if (depth == 0) {
    //         System.out.println(mover.board.evaluate());
	// 		return resultMover;
	// 	}
	// 	for (int i = 0; i <= depth; i ++) {
	// 		System.out.println("in depth for loop");
    //         for (Piece p: mover.board.getPiecesColor("white")) {
    //             row = mover.legalPieceMoves(p,false,"white").get(0);
    //             col = mover.legalPieceMoves(p,false,"white").get(1);
    //             for (int m = 0; m < row.size(); m++) {
	// 				MoveHistory newState = new MoveHistory(mover.getBoard(),mover);
	// 				states.push(newState);
	// 				Board tempBoard = new Board(mover.getBoard());
    //                 mover.moveBot(p.getRow(),p.getCol(),row.get(m),col.get(m));
    //                 MoveHistory state = states.pop();
	// 				if (mover.getBoard().evaluate() > tempBoard.evaluate()) {
	// 					max = mover.getBoard().evaluate();
	// 					System.out.println("Undoing");
	// 					if (mover.board != state.getBoard()) {
	// 						mover.board = state.getBoard();
	// 						}
	// 					if (mover != state.getMover()) {
	// 						System.out.println("Not the same move object");
	// 						mover = state.getMover();
	// 						mover.board = mover.getBoard();
	// 					}
	// 				}
    //                 System.out.println("calling Mini function");
	// 				resultMover = Mini(depth-1,row,col,mover,max);
    //                 if (mover.board != state.getBoard()) {
    //                     mover.board = state.getBoard();
    //                     }
    //                 if (mover != state.getMover()) {
    //                     System.out.println("Not the same move object");
    //                     mover = state.getMover();
    //                     mover.board = mover.getBoard();
    //                 }
    //             }
    //         }
	// 		double score = Math.max(minValue, resultMover.getBoard().evaluate());
	// 		if (score < minValue) {
	// 			minValue = score;
	// 		    resultMover = mover;
	// 		}
	// 	}
	// 	resultMover.getBoard().updateGameBoard();
	// 	resultMover.getBoard().populateBoard();
	// 	resultMover.getBoard().currentGameState();
	// 	return resultMover;
	// }
	public static Move Max(int depth, Move mover, double maxValue, double minValue) {
		if (depth == 0) {
			return mover;
		}
		Move bestMove = null;
		for (Piece p : mover.getBoard().getPiecesColor("white")) {
			ArrayList<ArrayList<Integer>> legalMoves = mover.legalPieceMoves(p, false, "white");
			for (int m = 0; m < legalMoves.get(0).size(); m++) {
				int row = legalMoves.get(0).get(m);
				int col = legalMoves.get(1).get(m);
				Move testMove = new Move(mover);
				testMove.moveBot(p.getRow(), p.getCol(), row, col);
				Move result = Mini(depth - 1, testMove, maxValue, minValue);
				if (result.getBoard().evaluate() > (bestMove == null ? Double.NEGATIVE_INFINITY : bestMove.getBoard().evaluate())) {
					bestMove = result;
				}
			}
		}
		return bestMove;
	}
	public static Move Mini(int depth, Move mover, double maxValue, double minValue) {
		if (depth == 0) {
			return mover;
		}
		Move bestMove = null;
		for (Piece p : mover.getBoard().getPiecesColor("black")) {
			ArrayList<ArrayList<Integer>> legalMoves = mover.legalPieceMoves(p, false, "black");
			for (int m = 0; m < legalMoves.get(0).size(); m++) {
				int row = legalMoves.get(0).get(m);
				int col = legalMoves.get(1).get(m);
				Move testMove = new Move(mover);
				testMove.moveBot(p.getRow(), p.getCol(), row, col);
				Move result = Max(depth - 1, testMove, maxValue, minValue);
				if (result.getBoard().evaluate() < (bestMove == null ? Double.POSITIVE_INFINITY : bestMove.getBoard().evaluate())) {
					bestMove = result;
				}
			}
		}
		return bestMove;
	}
	// public static Move Mini(double depth, ArrayList<Integer> row, ArrayList<Integer> col, Move mover, double maxValue) {
	// 	// System.out.println("in min function");
	// 	Move resultMover = new Move(mover);
	// 	double min = maxValue;
	// 	if (depth == 0) {
    //         System.out.println(mover.board.evaluate());
	// 		return mover;
	// 	}
	// 	for (int i = 0; i <= depth; i ++) {
	// 		System.out.println("in depth for loop");
    //         for (Piece p: mover.board.getPiecesColor("black")) {
    //             row = mover.legalPieceMoves(p,false,"black").get(0);
    //             col = mover.legalPieceMoves(p,false,"black").get(1);
	// 			System.out.println(row.size());
    //             for (int m = 0; m < row.size(); m++) {
    //                 MoveHistory newState = new MoveHistory(mover.board,mover);
	// 				states.push(newState);
    //                 mover.moveBot(p.getRow(),p.getCol(),row.get(m),col.get(m));
	// 				System.out.println("calling Max function");
	// 				Board tempBoard = new Board(mover.getBoard());
    //                 mover.moveBot(p.getRow(),p.getCol(),row.get(m),col.get(m));
    //                 MoveHistory state = states.pop();

	// 				if (mover.getBoard().evaluate() > tempBoard.evaluate()) {
	// 					min = mover.getBoard().evaluate();
	// 					System.out.println("Undoing");
	// 					if (mover.board != state.getBoard()) {
	// 						mover.board = state.getBoard();
	// 						}
	// 					if (mover != state.getMover()) {
	// 						System.out.println("Not the same move object");
	// 						mover = state.getMover();
	// 						mover.board = mover.getBoard();
	// 					}
	// 				}
    //                 System.out.println("calling Max function");
	// 				resultMover = Max(depth-1,row,col,mover,min);
    //                 if (mover.board != state.getBoard()) {
    //                     mover.board = state.getBoard();

    //                     }
    //                 if (mover != state.getMover()) {
    //                     System.out.println("Not the same move object");
    //                     mover = state.getMover();
    //                     mover.board = mover.getBoard();
    //                 }
    //             }
    //         }
 	// 		double score = Math.min(maxValue, resultMover.getBoard().evaluate());
	// 		if (score < maxValue) {
	// 			maxValue = score;
	// 			resultMover = mover;
	// 		}
	// 	}
	// 	resultMover.getBoard().updateGameBoard();
	// 	resultMover.getBoard().populateBoard();
	// 	resultMover.getBoard().currentGameState();
	// 	return resultMover;
	// }
	public static void main(String[] args) {

		for(int i = 0; i < 5; i++){
			System.out.println();
		}
		boolean mainMenu = true; 

		while(mainMenu){
			System.out.println(" ------------------------------------");
			System.out.println("                CHESS                ");
			System.out.println("   !castling/!En passant/!Promotion  ");
			System.out.println(" ------------------------------------");
			System.out.println("   1. Play a new game.               ");
			System.out.println(" ------------------------------------");
			System.out.println("   2. Play AI 1.0 (Alpha-Beta)       ");
			System.out.println("        You are Always White         ");
			System.out.println(" ------------------------------------");
			System.out.println("   3. Play AI 2.0 (reinforcement)    ");
			System.out.println("        You are Always White         ");
			System.out.println(" ------------------------------------");
			System.out.println("   4. Have AI play eachother         ");
			System.out.println("                                     ");
			
			Scanner userInput = new Scanner(System.in);
			String inputString;
			inputString = userInput.nextLine();
			System.out.println();
			Board chessBoard = new Board();
			Move mover = new Move(chessBoard);
			chessBoard.initBoard(); // initiate the board, start game
			Player white = new Player("white");
			System.out.println("Hello Player 1. Please input your desired user name: ");
			white.setName(userInput.nextLine());
			System.out.println();
			if(inputString.charAt(0) == '1') { // start a new game player vs player.
				black = new Player("black");
				System.out.println("And hello Player 2. Please input your desired user name: ");
				black.setName(userInput.nextLine());
				System.out.println();
				// states.push(new MoveHistory(chessBoard,mover));
				boolean turn = false; // white starts the game
				String source, destin;
				int countTurns = 0;
				while(true) {
					if(!turn) {
						System.out.println(white.getColor());
						chessBoard.setTurn(countTurns);
						// System.out.println("Length of stack " + states.size());
						System.out.println("Printing All Legal Moves");
						System.out.println("==========================");
						mover.setAllLegalMoves("white");
						// System.out.println("Rows:");
						// System.out.println(mover.getAllLegalMoves().get(0));
						// System.out.println("Cols:");
						// System.out.println(mover.getAllLegalMoves().get(1));
						System.out.println("Input current coordinates of the piece that you want to move. If undo, type undo");
						source = userInput.nextLine();
						if (source.equals("undo") && states.size() >= 1) {
							MoveHistory state = states.pop();
							if (chessBoard != state.getBoard()) {
								chessBoard = state.getBoard();
								chessBoard.updateGameBoard();
								chessBoard.populateBoard();
								}
							if (mover != state.getMover()) {
								System.out.println("Not the same move object");
								mover = state.getMover();
								chessBoard = mover.getBoard();
								mover.getBoard().currentGameState();
							}
							turn = true;
							continue;
						}
						System.out.println("Input coordinates of the destination space.");
						destin = userInput.nextLine();
						if (chessBoard.gameOver().equals("null")) {
							while(true) {
								if(mover.moveCheck(source, destin, white)) {
									System.out.println("adding new state to stack");
									states.push(new MoveHistory(chessBoard,mover));
									System.out.println("added new state to stack");
									System.out.println("got to move");
									mover.move(source, destin, white);
									System.out.println("clearing prev list All Legal Moves");
									mover.ClearAllMoves();
									mover.setAllLegalMoves("white");
									break;
								}
								else {
									System.out.println("Your move was illegal");
									System.out.println("Input current coordinates of the piece that you want to move.");
									source = userInput.nextLine();
									// ask user to know where they want to move it to
									System.out.println("Input coordinates of the destination space.");
									destin = userInput.nextLine();
								}
							}
						}
						else if (chessBoard.gameOver().equals("black")) {
							System.out.println("black wins");
							userInput.close();
							return;
						}
						else if (chessBoard.gameOver().equals("white")) {
							System.out.println("white wins");
							userInput.close();
							return;
						}
						else {
							System.out.println("draw");
							userInput.close();
							return;
						}
						turn = true;
						countTurns = countTurns + 1;
					}
					else { // black player's turn
						System.out.println(turn);
						System.out.println(black.getColor());
						System.out.println("Length of stack " + states.size());

						for(int i = 0; i < 5; i++){
							System.out.println();
						}
						chessBoard.setTurn(countTurns);

						System.out.println("Input current coordinates of the piece that you want to move. If undo, type undo");
						source = userInput.nextLine();
						if (source.equals("undo") && states.size() >= 1) {
							MoveHistory state = states.pop();
							if (chessBoard != state.getBoard()) {
								chessBoard = state.getBoard();
								chessBoard.updateGameBoard(); 
								chessBoard.populateBoard();
								}
							if (mover != state.getMover()) {
								System.out.println("Not the same move object");
								mover = state.getMover();
								chessBoard = mover.getBoard();
								mover.getBoard().currentGameState();
							}
							turn = false;
							continue;
						}
						System.out.println("Input coordinates of the destination space.");
						destin = userInput.nextLine();
						if (chessBoard.gameOver().equals("null")) {
							while(true) {
								if(mover.moveCheck(source, destin, black)) {
									System.out.println("adding new state to stack");
									states.push(new MoveHistory(chessBoard,mover));
									System.out.println("added new state to stack");
									System.out.println("got to move");
									mover.move(source, destin, black);
									System.out.println("clearing prev list All Legal Moves");
									mover.ClearAllMoves();
									break;
								}
								else {
									System.out.println("Your move was illegal");
									System.out.println("Input current coordinates of the piece that you want to move.");
									source = userInput.nextLine();
									System.out.println("Input coordinates of the destination space.");
									destin = userInput.nextLine();
								}
							}
						}
						else if (chessBoard.gameOver().equals("black")) {
							System.out.println("black wins");
							userInput.close();
							return;
						}
						else if (chessBoard.gameOver().equals("white")) {
							System.out.println("white wins");
							userInput.close();
							return;
						}
						else {
							System.out.println("draw");
							userInput.close();
							return;
						}
						turn = false;
						countTurns = countTurns + 1;
					}
				}
			}
			else if (inputString.charAt(0) == '2') {
				boolean turn = false; // white starts the game
				String source, destin;
				int countTurns = 0;
				while(true) {
					if(!turn) {
						System.out.println(white.getColor());
						chessBoard.setTurn(countTurns);
						mover.setAllLegalMoves("white");
						System.out.println("Input current coordinates of the piece that you want to move. If undo, type undo");
						source = userInput.nextLine();
						if (source.equals("undo") && states.size() >= 1) {
							MoveHistory state = states.pop();
							if (chessBoard != state.getBoard()) {
								chessBoard = state.getBoard();
								chessBoard.updateGameBoard();
								chessBoard.populateBoard();
								}
							if (mover != state.getMover()) {
								System.out.println("Not the same move object");
								mover = state.getMover();
								chessBoard = mover.getBoard();
								mover.getBoard().currentGameState();
							}
							turn = true;
							continue;
						}
						System.out.println("Input coordinates of the destination space.");
						destin = userInput.nextLine();
						if (chessBoard.gameOver().equals("null")) {
							while(true) {
								if(mover.moveCheck(source, destin, white)) {
									System.out.println("adding new state to stack");
									states.push(new MoveHistory(chessBoard,mover));
									System.out.println("added new state to stack");
									System.out.println("got to move");
									mover.move(source, destin, white);
									mover.ClearAllMoves();
									mover.setAllLegalMoves("white");
									break;
								}
								else {
									System.out.println("Your move was illegal");
									System.out.println("Input current coordinates of the piece that you want to move.");
									source = userInput.nextLine();
									// ask user to know where they want to move it to
									System.out.println("Input coordinates of the destination space.");
									destin = userInput.nextLine();
								}
							}
						}
						else if (chessBoard.gameOver().equals("black")) {
							System.out.println("black wins");
							userInput.close();
							return;
						}
						else if (chessBoard.gameOver().equals("white")) {
							System.out.println("white wins");
							userInput.close();
							return;
						}
						else {
							System.out.println("draw");
							userInput.close();
							return;
						}
						turn = true;
						countTurns = countTurns + 1;
					}
					else { // MiniMax Turn
						mover = AlphaBetaSearch(mover,3,Double.MAX_VALUE,Double.MIN_VALUE,"black");
						chessBoard = mover.getBoard();
						chessBoard.updateGameBoard();
						chessBoard.populateBoard();
						turn = false;
					}
				}
			}
			else if(inputString.charAt(0) == '3') {
				// black = new RL();
				boolean turn = false; // white starts the game
				String source, destin;
				int countTurns = 0;
				while(true) {
					if(!turn) {
						System.out.println(white.getColor());
						chessBoard.setTurn(countTurns);
						mover.setAllLegalMoves("white");
						System.out.println("Input current coordinates of the piece that you want to move. If undo, type undo");
						source = userInput.nextLine();
						if (source.equals("undo") && states.size() >= 1) {
							MoveHistory state = states.pop();
							if (chessBoard != state.getBoard()) {
								chessBoard = state.getBoard();
								chessBoard.updateGameBoard();
								chessBoard.populateBoard();
								}
							if (mover != state.getMover()) {
								System.out.println("Not the same move object");
								mover = state.getMover();
								chessBoard = mover.getBoard();
								mover.getBoard().currentGameState();
							}
							turn = true;
							continue;
						}
						System.out.println("Input coordinates of the destination space.");
						destin = userInput.nextLine();
						if (chessBoard.gameOver().equals("null")) {
							while(true) {
								if(mover.moveCheck(source, destin, white)) {
									System.out.println("adding new state to stack");
									states.push(new MoveHistory(chessBoard,mover));
									System.out.println("added new state to stack");
									System.out.println("got to move");
									mover.move(source, destin, white);
									System.out.println("clearing prev list All Legal Moves");
									mover.ClearAllMoves();
									System.out.println("Printing All Legal Moves");
									System.out.println("==========================");
									mover.setAllLegalMoves("white");
									break;
								}
								else {
									System.out.println("Your move was illegal");
									System.out.println("Input current coordinates of the piece that you want to move.");
									source = userInput.nextLine();
									System.out.println("Input coordinates of the destination space.");
									destin = userInput.nextLine();
								}
							}
						}
						else if (chessBoard.gameOver().equals("black")) {
							System.out.println("black wins");
							userInput.close();
							return;
						}
						else if (chessBoard.gameOver().equals("white")) {
							System.out.println("white wins");
							userInput.close();
							return;
						}
						else {
							System.out.println("draw");
							userInput.close();
							return;
						}
						turn = true;
						countTurns = countTurns + 1;
					}
				System.out.println("RL not done");
				turn = false;
				}
			}
			userInput.close();
		}
	}
}