
import java.util.*;
public class Board{
	private Piece[][] board = new Piece[8][8]; // dynamic game board array
	private ArrayList<Piece> pieces = new ArrayList<Piece>(32); // dynamic ArrayList of pieces
	private int turn; // white = 0, black = 1
	private char chosenGameType; // used to do player vs player or player vs AI or AI vs AI

	// Constructor
	public Board() {
	}
	// deep copying of a boarda
	public Board(Board board) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board.board[r][c] != null) {
					Piece newPiece = new Piece(board.board[r][c]);
					if (newPiece != board.board[r][c]) {
						this.board[r][c] = newPiece;
					}
				}
			}
		}
		for (Piece p: board.pieces) {
			this.pieces.add(new Piece(p));
		}
		turn = board.turn;
		chosenGameType = board.chosenGameType;
	}
	// returning the 2D array (Basically the board)
	public Piece [][] getBoard() {
		return board;
	}
	// checking if the game is over, if it is over returns a string else return null
	public String gameOver() {
		boolean blackKing = false;
		boolean whiteKing = false;
		ArrayList <Piece> pieces = new ArrayList<>(32);
		for (int r = 0; r < 8; r ++) {
			for (int c = 0; c < 8; c++) {
				if ((board[r][c]) != null) {
					if (board[r][c].getName().equals("king") && board[r][c].getColor().equals("black")) {
						blackKing = true;
					}
					else if (board[r][c].getName().equals("king") && board[r][c].getColor().equals("white")) {
						whiteKing = true;
					}
					else {
						pieces.add(board[r][c]);
					}
				}
			}
		}
		if (!blackKing) {
			return "white";
		}
		else if (!whiteKing) {
			return "black";
		}
		else if (pieces.size() < 3){
			return "draw";
		}
		return "null";
	}
	// prints the current state of the game
	public void currentGameState() {
		System.out.println();
		System.out.println(" ------------------------------------");
		for(int i = 0; i < 8; i++){
			System.out.print(" |");
			System.out.print(8-i+" |");
			for(int j = 0; j < 8; j++){
				if(board[i][j] == null){ // empty space
					System.out.print("   "); 
					System.out.print("|");
				} else { // piece
					System.out.print(board[i][j].getPieceName()); 
					System.out.print("|");
				}
			}
			if(i < 8) { 
				System.out.println();
				System.out.println(" |--|-------------------------------|");
			}

		}
		// print a - h for completeness of board notation
		System.out.println(" |  | a | b | c | d | e | f | g | h |");
		System.out.println(" ------------------------------------");
		System.out.println();
		
	}
	// remove a piece from the board, r = row, c = col
	public void removePiece(int r, int c) {
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){
			if(p.getRow() == r && p.getCol() == c){ // find desired piece, remove, update game status
				pieces.remove(p);
				updatePieces(pieces);
				updateGameBoard();
				break;
			}
		}
	}
	// add a piece to the board
	public void addPiece(Piece p, int r, int c) {
		ArrayList<Piece> pieces = getPieces(); // current pieces
		if(!pieceOnSpace(r, c)){ // if space is clear, add piece
			pieces.add(p);
			updatePieces(pieces); // update game status
			updateGameBoard();
		} else {
			System.out.println("There is already a piece in this space! You cannot add a piece here.");
		}
	}
	// clear the board if necessary
	public void clearBoard(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}
	}
	// check input location for a piece
	public boolean pieceOnSpace(int r, int c) {
		for(Piece p : getPieces()){
			if(p.getRow() == r && p.getCol() == c){
				return true; // piece found
			}
		}
		return false; // no piece found
	}
	// create pieces -- Note on color value: white = 0, black = 1
	public void createPieces() {
		ArrayList<Piece> pieces = getPieces(); // ArrayList of all 32 pieces in game
		for(int i = 0; i < 8; i++){ // 8 pawns/player
			pieces.add(new Piece("black", "pawn", 1, i)); // Piece(color, type, row, col)
			pieces.add(new Piece("white", "pawn", 6, i));
		}
		// 2 rooks/player
		pieces.add(new Piece("black", "rook", 0, 0));
		pieces.add(new Piece("black", "rook", 0, 7));
		pieces.add(new Piece("white", "rook", 7, 0));
		pieces.add(new Piece("white", "rook", 7, 7));
		// 2 bishops/player
		pieces.add(new Piece("black", "bishop", 0, 2));
		pieces.add(new Piece("black", "bishop", 0, 5));
		pieces.add(new Piece("white", "bishop", 7, 2));
		pieces.add(new Piece("white", "bishop", 7, 5));
		// 2 knights/player
		pieces.add(new Piece("black", "knight", 0, 1));
		pieces.add(new Piece("black", "knight", 0, 6));
		pieces.add(new Piece("white", "knight", 7, 1));
		pieces.add(new Piece("white", "knight", 7, 6));
		// 1 queen/player
		pieces.add(new Piece("black", "queen", 0, 3));
		pieces.add(new Piece("white", "queen", 7, 3));
		// 1 king/player
		pieces.add(new Piece("black", "king", 0, 4));
		pieces.add(new Piece("white", "king", 7, 4));
	}
	// populate board with pieces
	public void populateBoard() {
		// ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){ 
			board[p.getRow()][p.getCol()] = p; // place pieces
		}
	}
	// bool to set type of game
	public boolean chooseGameType(int choice) {
		if(choice == 0){ // normal game
			return false;
		} else { // scramble
			return true;
		}
	}
	// initiate the game
	public void initBoard() {
		clearBoard(); // start with clear board
		createPieces(); // create pieces, update game state
		populateBoard();
		currentGameState();
	}
	// update the pieces
	public void updatePieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	// update board
	public void updateGameBoard() {
		clearBoard();
		populateBoard();
	}
	// get pieces
	public ArrayList<Piece> getPieces() {
		return this.pieces;
	}
	// get turn
	public int getTurn() {
		return turn;
	}
	// set turn
	public void setTurn(int turn) {
		this.turn = turn;
	}
	// get piece at given locations
	public Piece getPieces(int r, int c) {
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){ // iterate through pieces to find piece
			if(p.getRow() == r && p.getCol() == c){
				return p;
			}
		}
		return new Piece(); // no piece found, blank space
	}
	// get piece at given location with given color
	public Piece getPieces(int r, int c, String color) {
		ArrayList<Piece> pieces = getPieces(); // current pieces
		for(Piece p : pieces){ // iterate through pieces to find piece
			if((p.getRow() == r && p.getCol() == c) && p.getColor().equals(color)){ // if same location and color, return piece
				return p;
			}
		}
		return new Piece(); // no piece found, blank space
	}
	// get chosen game type
	public char getChosenGameType() {
		return chosenGameType;
	}
	// swap two input pieces
	public void swapPieces(Piece one, Piece two) {
		ArrayList<Piece> pieces = getPieces(); // current pieces
		Piece hold = one; // set piece one equal to a holding piece
		int count = 0;
		for(Piece p : pieces){ // iterate through pieces to find piece
			if(p == one){ // find Piece one
				p.setName(two.getName()); // set properties of piece one to piece two
				p.setRow(two.getRow());
				p.setCol(two.getCol());
				p.setColor(two.getColor());
				pieces.set(count, p); // put piece back in ArrayList
				updatePieces(pieces); // update game state
				updateGameBoard();
				break;
			}
			count++;
		}
		count = 0; // reset count
		for(Piece p : pieces) {
			if(p == two){
				p.setName(hold.getName()); // set properties of piece one to piece two
				p.setRow(hold.getRow());
				p.setCol(hold.getCol());
				p.setColor(hold.getColor());
				pieces.set(count, p); // put piece back in ArrayList
				updatePieces(pieces); // update game state
				updateGameBoard();
				break;
			}
			count++;
		}
	}
	// evaluate the position
	public double evaluate() {
		double value = 0;
		// need to add value to squares 
		for (Piece p: pieces) {
			if (p.getColor().equals("white")) {
				// calc piece values
				if (p.getName().equals("pawn")) {
					value = value + 1;
				}
				else if (p.getName().equals("knight")) {
					value = value + 3;
				}
				else if (p.getName().equals("bishop")) {
					value = value + 3.5;
				}
				else if (p.getName().equals("rook")) {
					value = value + 3;
				}
				else if (p.getName().equals("queen")) {
					value = value + 9;
				}
				else if (p.getName().equals("king")) {
					value = value + 10000;
				}
				// calc square values
				if (p.getRow() == 4 || p.getRow() == 5) {
					value = value + 0.5;
				}
				else if (p.getRow() == 6 || p.getRow() == 7) {
					value = value + 0.6;
				}
				else {
					value = value + 0.1;
				}
			}
			else {
				// calc piece values
				if (p.getName().equals("pawn")) {
					value = value - 1;
				}
				else if (p.getName().equals("knight")) {
					value = value - 3;
				}
				else if (p.getName().equals("bishop")) {
					value = value - 3.5;
				}
				else if (p.getName().equals("rook")) {
					value = value - 3;
				}
				else if (p.getName().equals("queen")) {
					value = value - 9;
				}
				else if (p.getName().equals("king")) {
					value = value - 10000;
				}
				// calc square values
				if (p.getRow() == 4 || p.getRow() == 5) {
					value = value - 0.5;
				}
				else if (p.getRow() == 6 || p.getRow() == 7) {
					value = value - 0.6;
				}
				else {
					value = value - 0.1;
				}
			}
		}
		return value;
	}
	// getPieces given color
	public ArrayList<Piece> getPiecesColor(String color) {
		ArrayList<Piece> colorPieces = new ArrayList<>();
		for (Piece p: this.pieces) {
			if(p.getColor().equals(color)) {
				colorPieces.add(p);
			}
		}
		return colorPieces;
	}
}