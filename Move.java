
import java.util.*;
public class Move{
	Board board;
	// private Stack <Board> states = new Stack<>();
	// private boolean moveValid;  // need to use this when getting the player to move again if the player enters an illegal move
	private ArrayList<ArrayList<Integer>> allMoves = new ArrayList<>();

	public Move(Board board) {
		this.board = board;
	}
	public Move(Move move) {
		this.board = new Board(move.board);
	}
	public Board getBoard(){
		return board;
	}
	public void ClearAllMoves() {
		this.allMoves.clear();
	}
	public void setAllLegalMoves(String color) {
		for (Piece p: this.board.getPieces()) {
			this.allMoves.addAll(legalPieceMoves(p, false, color));
		}
	}
	public ArrayList<ArrayList<Integer>> getAllLegalMoves() {
		return this.allMoves;
	}
	// find piece with given row, col
	public Piece findPiece(int row, int col) {
		boolean exists = false; 
		ArrayList<Piece> pieces = getBoard().getPieces(); 
		Piece foundPiece = null; 
		for(Piece p: pieces) {
			foundPiece = p;
			if(foundPiece.getRow() == row && foundPiece.getCol() == col) {
				exists = true;  
				break;
			}
		}
		if(exists){
			return foundPiece;
		}
		else {
			return null;
		}
	}
	// flip the rows for chess notation
	private int inputToRow(String input){
		char r = input.charAt(1);
		int row;
		switch(r) {
		case '8':
			row = 0;
			break;
		case '7':
			row = 1;
			break;
		case '6':
			row = 2;
			break;
		case '5':
			row = 3;
			break;
		case '4':
			row = 4;
			break;
		case '3':
			row = 5;
			break;
		case '2':
			row = 6;
			break;
		case '1':
			row = 7;
			break;
		default:
			row = -1;
			break;
		}
		return row;
	}
	// convert columns to fit chess notation
	private int inputToCol(String input){
		char c = input.charAt(0);
		int col;
		switch(c) {  
		case 'a':
			col = 0;
			break;
		case 'b':
			col = 1;
			break;
		case 'c':
			col = 2;
			break;
		case 'd':
			col = 3;
			break;
		case 'e':
			col = 4;
			break;
		case 'f':
			col = 5;
			break;
		case 'g':
			col = 6;
			break;
		case 'h':
			col = 7;
			break;
		default:
			col = -1;
			break;
		}
		return col;
	}
	public void moveBot(int curRow, int curCol, int destRow, int destCol) {
		if((curRow >= 0 && curRow < 8) && (curCol >= 0 && curCol < 8)) {
			if((destRow >= 0 && destRow < 8) && (destCol >= 0 && destCol < 8)) {
				Piece piece = findPiece(curRow, curCol);
				if(piece != null) { 
					ArrayList<ArrayList<Integer>> allowedMoves = legalPieceMoves(piece, false, "black");
                    for (int i = 0; i < allowedMoves.size(); i++) {
						System.out.println(allowedMoves.get(i));
					}
					ArrayList<Integer> row = allowedMoves.get(0);
					ArrayList<Integer> column = allowedMoves.get(1); 
					ListIterator<Integer> rowIter = row.listIterator();
					ListIterator<Integer> columnIter = column.listIterator();
					int rNext, cNext;
					while(rowIter.hasNext() && columnIter.hasNext()) { 
						rNext = rowIter.next();
						cNext = columnIter.next();
						if(destRow == rNext && destCol == cNext) { 
							getBoard().removePiece(destRow, destCol);
							piece.setRow(destRow);
							piece.setCol(destCol);
							System.out.println("updating game");
							getBoard().updateGameBoard();
							getBoard().currentGameState();
							// moveValid = true;
						}
					}
				}
			}
		}
	}
	// move piece in selected space to destination
	public void move(String curSpace, String destSquare, Player player) {
		// convert input Strings into row, col integers
		int curRow, curColumn, destRow, destColumn;
		curRow = inputToRow(curSpace);
		curColumn = inputToCol(curSpace);
		destRow = inputToRow(destSquare);
		destColumn = inputToCol(destSquare);
		if((curRow >= 0 && curRow < 8) && (curColumn >= 0 && curColumn < 8)) {
			if((destRow >= 0 && destRow < 8) && (destColumn >= 0 && destColumn < 8)) {
				Piece piece = findPiece(curRow, curColumn);
				if(piece != null && player != null && piece.getColor() == player.getColor()) { 
					ArrayList<ArrayList<Integer>> allowedMoves = legalPieceMoves(piece, false, player.getColor());
                    for (int i = 0; i < allowedMoves.size(); i++) {
						System.out.println(allowedMoves.get(i));
					}
					ArrayList<Integer> row = allowedMoves.get(0);
					ArrayList<Integer> column = allowedMoves.get(1); 
					ListIterator<Integer> rowIter = row.listIterator();
					ListIterator<Integer> columnIter = column.listIterator();
					int rNext, cNext;
					while(rowIter.hasNext() && columnIter.hasNext()) { 
						rNext = rowIter.next();
						cNext = columnIter.next();
						if(destRow == rNext && destColumn == cNext) { 
							getBoard().removePiece(destRow, destColumn);
							piece.setRow(destRow);
							piece.setCol(destColumn);
							System.out.println("updating game");
							getBoard().updateGameBoard();
							getBoard().currentGameState();
							// moveValid = true;
						}
					}
				}
			}
		}
	}
	public boolean moveCheck(String curSpace, String destSquare, Player player) {
		// convert input Strings into row, col integers
		int curRow, curColumn, destRow, destColumn;
		curRow = inputToRow(curSpace);
		curColumn = inputToCol(curSpace);
		destRow = inputToRow(destSquare);
		destColumn = inputToCol(destSquare);
		if((curRow >= 0 && curRow < 8) && (curColumn >= 0 && curColumn < 8)) {
			if((destRow >= 0 && destRow < 8) && (destColumn >= 0 && destColumn < 8)) {
				Piece piece = findPiece(curRow, curColumn);
				if(piece != null && player != null && piece.getColor() == player.getColor()) { 
					ArrayList<ArrayList<Integer>> allowedMoves = legalPieceMoves(piece, false, player.getColor());
					ArrayList<Integer> row = allowedMoves.get(0);
					ArrayList<Integer> column = allowedMoves.get(1); 
					ListIterator<Integer> rowIter = row.listIterator();
					ListIterator<Integer> columnIter = column.listIterator();
					int rNext, cNext;
					while(rowIter.hasNext() && columnIter.hasNext()) { 
						rNext = rowIter.next();
						cNext = columnIter.next();
						if(destRow == rNext && destColumn == cNext) { 
							// moveValid = true;
							return true; 
						}
					}
				}
			}
		}
		return false; // move failed
	}

	// check if king piece is on input square
	public boolean kingOnSquare(int row, int column, String oppColor){
		for(Piece p : getBoard().getPieces()) { // iterate through pieces
			if(p.getColor().equals(oppColor)) { // color check
				if(p.getRow() == row && p.getCol() == column) { // square check
					if(p.getName().equals("king")) { // check that type = king
						return true;
					}
				}
			}
		}
		return false; 
	}

	// legal move check for input piece, returns list of allowed moves
	public ArrayList<ArrayList<Integer>> legalPieceMoves(Piece piece, boolean checkMate, String color){
		ArrayList<Integer> row, col; // list of coordinates
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> king, queen, rook, knight, bishop, pawn; // list for each different type of piece
		ArrayList<ArrayList<Integer>> legalMoves = new ArrayList<ArrayList<Integer>>(); // list to be returned with legal move set
		if (piece.getName().equals("king") && piece.getColor().equals(color)) {
			king = legalKingMoves(piece, checkMate);
			row = king.get(0); // row values are stored in first ArrayList
			col = king.get(1); // column values are stored in second ArrayList
		}
		else if (piece.getName().equals("queen") && piece.getColor().equals(color)) {
			queen = legalQueenMoves(piece, checkMate);
			row = queen.get(0);
			col = queen.get(1);
		}
		else if (piece.getName().equals("rook") && piece.getColor().equals(color)) {
			rook = legalRookMoves(piece, checkMate);
			row = rook.get(0);
			col = rook.get(1);
		}
		else if (piece.getName().equals("knight") && piece.getColor().equals(color)) {
			knight = legalKnightMoves(piece, checkMate);
			row = knight.get(0);
			col = knight.get(1);
		}
		else if (piece.getName().equals("bishop") && piece.getColor().equals(color)) {
			bishop = legalBishopMoves(piece, checkMate);
			row = bishop.get(0);
			col = bishop.get(1);
		}
		else if (piece.getName().equals("pawn") && piece.getColor().equals(color)){
			pawn = legalPawnMoves(piece, checkMate);
			row = pawn.get(0);
			col = pawn.get(1);
		}
		if (row != null && col != null) {
			legalMoves.add(row); // add row, col to legalMoves
			legalMoves.add(col);
		}
		piece.setLegalMoves(legalMoves);
		return legalMoves; // return list of legal moves
	}
	// checks if there is a piece on the squre
	public boolean pieceOnSquare(int r, int c){
		for(Piece p : getBoard().getPieces()){ // iterate through pieces on board
			if(p.getRow() == r && p.getCol() == c){ // find piece
				return true;
			}
		}
		return false; // no piece found
	}
	
	// check if there is a piece on the selected square
	public boolean pieceOnSquareTakingColor(int r, int c, String color){
		for(Piece p : getBoard().getPieces()){ // iterate through pieces
			if(p.getColor().equals(color)){ // color check
				if(p.getRow() == r && p.getCol() == c){ // find piece
					return true;
				}
			}
		}
		return false;
	}
	private ArrayList<ArrayList<Integer>> legalPawnMoves(Piece piece, boolean checkMate){
		ArrayList<Integer> row, col; // row, column lists
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); // an arrayList of both row and columnb
		int r = piece.getRow(); // row
		int c = piece.getCol(); // column
		String oppColor = piece.getEnemyColor(); // enemy color
		if(piece.getName().equals("pawn") && piece.getColor().equals("white")) { 
			if(r == 6) { 
				if(pieceOnSquare(r-1, c) != true) {
					row.add(r-1); 
					col.add(c);
					if(pieceOnSquare(r-2, c) != true) {
						row.add(r-2);
						col.add(c);
					}
				}
			}
			else { // not on home row, can only move one space forward
				if((r-1)>=0 && pieceOnSquare(r-1, c) != true) { // make sure space is clear / pawn will not go off board
					row.add(r-1);
					col.add(c);
				}
			}
			// diagonal above: right/left is for capturing opponents
			if(pieceOnSquareTakingColor(r-1, c+1, oppColor)) { // check for opponent in space (above right)
				if(checkMate){ // if checkMate = true, game is over and pawn can take king
					row.add(r-1);
					col.add(c+1);
				}
				else {  // game is not over yet
					if(kingOnSquare(r-1, c+1, oppColor) != true){ // make sure king is not on space
						row.add(r-1);
						col.add(c+1);
					}
				}
			}
			if(pieceOnSquareTakingColor(r-1, c-1, oppColor)) { // above left
				if(checkMate){
					row.add(r-1);
					col.add(c-1);
				}
				else { 
					if(kingOnSquare(r-1, c-1, oppColor) != true){
						row.add(r-1);
						col.add(c-1);
					}
				}
			}
		}
		else if(piece.getName().equals("pawn") && piece.getColor().equals("black")) { // black pawn
			// regular forward movement
			if(r == 1) {
				if(!pieceOnSquare(r+1, c)){
					row.add(r+1);
					col.add(c);
					if(!pieceOnSquare(r+2, c)){
						row.add(r+2);
						col.add(c);
					}
				}
			}
			else { 
				if((r+1)>=0 && !pieceOnSquare(r+1, c)){
					row.add(r+1);
					col.add(c);
				}
			}
			// diagonal below: right/left is for capturing opponents
			if(pieceOnSquareTakingColor(r+1, c+1, oppColor)) { // below right
				if(checkMate) { 
					row.add(r+1);
					col.add(c+1);
				}
				else { 
					if(kingOnSquare(r+1, c+1, oppColor) != true) {
						row.add(r+1);
						col.add(c+1);
					}
				}
			}
			if(pieceOnSquareTakingColor(r+1, c-1, oppColor)) { // below left
				if(checkMate){
					row.add(r+1);
					col.add(c-1);
				} else { 
					if(kingOnSquare(r+1, c-1, oppColor) != true) {
						row.add(r+1);
						col.add(c-1);
					}
				}
			}
			if(getBoard().getChosenGameType() == '1') { 
				// pawn can also move horizontally one space right/left
				if(!pieceOnSquare(r, c+1)) { // make sure space is clear (right)
					row.add(r); // add row, column to possible moves
					col.add(c+1);
				}
				if(!pieceOnSquare(r, c-1)) { // left
					row.add(r); // add row, column to possible moves
					col.add(c-1);
				}
			}
		}
		rowAndCol.add(row); // add row, column to combined list
		rowAndCol.add(col);
		return rowAndCol; // return pawn moves
	}
	private ArrayList<ArrayList<Integer>> legalBishopMoves(Piece piece, boolean checkMate) {
		ArrayList<Integer> row, col; // lists for row, col
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); // combined list to return
		int r = piece.getRow(); // row
		int c = piece.getCol(); // column
		String color = piece.getColor(); // color
		String oppColor = piece.getEnemyColor(); // enemy color
		// check all diagonal above, right
		int j = c+1; // column count
		int i = r-1; // row count
		while(i >= 0 && j <= 7){ // while on the board
			if(pieceOnSquareTakingColor(i, j, color)){ // if same color piece is on space, no add, break
				break;
			}
			else if(pieceOnSquareTakingColor(i, j, oppColor)){ // check for opponent on space, add then break
				if(checkMate){ //check for checkmate, end game if true
					row.add(i);
					col.add(j);
				}
				else { // not checkmate
					if(kingOnSquare(i, j, oppColor) != true) { // if the piece is not the king, can take the piece
						row.add(i);
						col.add(j);
					}
				}
				break;
			}
			else { // empty space so add
				if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
					row.add(i);
					col.add(j);
				}
			}
			i--; // iterate through counters
			j++;
		}

		j = c-1;
		i = r-1;
		while(i >= 0 && j >= 0) { 
			if(pieceOnSquareTakingColor(i, j, color)) { 
				break;
			}
			else if(pieceOnSquareTakingColor(i, j, oppColor)) { 
				if(checkMate){ // capture king
					row.add(i);
					col.add(j);
				}
				else { // not checkmate, make sure piece is not king
					if(kingOnSquare(i, j, oppColor) != true){
						row.add(i);
						col.add(j);
					}
				}
				break;
			}
			else { // empty space so add
				if ((i >= 0 && i < 8) && (j >= 0 && j < 8)) {
					row.add(i);
					col.add(j);
				}
			}
			i--; // iterate counters
			j--;
		}
		// check all diagonal below, right
		j = c+1;
		i = r+1;
		while(i <= 7 && j <= 7){ // while on board
			if(pieceOnSquareTakingColor(i, j, color)) { // if same color piece, no add, break
				break;
			}
			else if(pieceOnSquareTakingColor(i, j, oppColor)) { // check for opponent on space, add then break
				if(checkMate){ // capture king
					row.add(i);
					col.add(j);
				}
				else { // not checkmate, make sure piece is not king
					if(kingOnSquare(i, j, oppColor) != true){
						row.add(i);
						col.add(j);
					}
				}
				break;
			}
			else { // empty space so add
				if ((i > 0 && i < 8) && (j > 0 && j < 8)) {
					row.add(i);
					col.add(j);
				}
			}
			i++; // iterate counters
			j++;
		}
		// check all diagonal, left
		j = c-1;
		i = r+1;
		while(i <= 7 && j >= 0){ // while on board
			if(pieceOnSquareTakingColor(i, j, color)) { // if same color piece, no add, break
				break;
			}
			else if(pieceOnSquareTakingColor(i, j, oppColor)) { // check for opponent on space, add then break
				if(checkMate){ // capture king
					row.add(i);
					col.add(j);
				}
				else { // not checkmate, make sure piece is not king
					if(kingOnSquare(i, j, oppColor) != true) {
						row.add(i);
						col.add(j);
					}
				}
				break;
			}
			else { // empty space so add
				if ((i > 0 && i < 8) && (j > 0 && j < 8)) {
					row.add(i);
					col.add(j);
				}
			}
			i++; // iterate counters
			j--;
		}
		rowAndCol.add(row); // add row, column to combination ArrayList
		rowAndCol.add(col);
		return rowAndCol; // return combination
	}	
	private ArrayList<ArrayList<Integer>> legalKnightMoves(Piece piece, boolean checkMate) {
		ArrayList<Integer> row, col; // row, column lists
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); // combined list to return
		int r = piece.getRow(); // row
		int c = piece.getCol(); // column
		String color = piece.getColor(); // color 
		String oppColor = piece.getEnemyColor(); // enemy color
		// check above, right spaces (2 options)
		// option 1: up two, right one
		if(pieceOnSquareTakingColor(r-2, c+1, color)) { 
			// if same color piece on space, do not add
		} else if(pieceOnSquareTakingColor(r-2, c+1, oppColor)) { // check for opponent on space, add then break
			if(checkMate){ // capture king
				if ((r-2 >= 0 && r-2 < 8) && (c+1 >= 0 && c+1 < 8)) {
					row.add(r-2);
					col.add(c+1);
				}
			}
			else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r-2, c+1, oppColor) != true ) {
					if ((r-2 >= 0 && r-2 < 8) && (c+1 >= 0 && c+1 < 8)) {
						row.add(r-2);
						col.add(c+1);
					}
				}
			}
		}
		else { // empty space so add
			if ((r-2 >= 0 && r-2 < 8) && (c+1 >= 0 && c+1 < 8)) {
				row.add(r-2);
				col.add(c+1);
			}
		}
		// option 2: up one, right two
		if(pieceOnSquareTakingColor(r-1, c+2, color)) {
			// if same color piece on space, do not add
		} else if(pieceOnSquareTakingColor(r-1, c+2, oppColor)) { // check for opponent on space, add then break
			if(checkMate){ // capture king
				if ((r-1 >= 0 && r-1 < 8) && (c+2 >= 0 && c+2 < 8)) {
					row.add(r-1);
					col.add(c+2);
				}
			} else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r-2, c-1, oppColor) != true) {
					if ((r-1 >= 0 && r-1 < 8) && (c+2 >= 0 && c+2 < 8)) {
						row.add(r-1);
						col.add(c+2);
					}
				}
			}
		} else { // empty space so add
			if ((r-1 >= 0 && r-1 < 8) && (c+2 >= 0 && c+2 < 8)) {
				row.add(r-1);
				col.add(c+2);
			}
		}
		// check above, left spaces (2 options)
		//option 1: up two, left one
		if(pieceOnSquareTakingColor(r-2, c-1, color)) {
			// if same color piece on space, do not add
		} else if(pieceOnSquareTakingColor(r-2, c-1, oppColor)) { // check for opponent on space, add then break
			if(checkMate){ // capture king
				if ((r-2 >= 0 && r-2 < 8) && (c-1 >= 0 && c-1 < 8)) {
					row.add(r-2);
					col.add(c-1);
				}
			} else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r-2, c-1, oppColor) != true) {
					if ((r-2 >= 0 && r-2 < 8) && (c-1 >= 0 && c-1 < 8)) {
						row.add(r-2);
						col.add(c-1);
					}
				}
			}
		} else { // empty space so add
			if ((r-2 >= 0 && r-2 < 8) && (c-1 >= 0 && c-1 < 8)) {
				row.add(r-2);
				col.add(c-1);
			}
		}
		// option 2: up one, left two
		if(pieceOnSquareTakingColor(r-1, c-2, color)) {
			// if same color piece on space, do not add
		} else if(pieceOnSquareTakingColor(r-1, c-2, oppColor)) { // check for opponent on space, add then break
			if(checkMate){ // capture king
				if ((r-1 >= 0 && r-1 < 8) && (c-2 >= 0 && c-2 < 8)) {
					row.add(r-1);
					col.add(c-2);
				}
			} else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r-2, c-1, oppColor) != true) {
					if ((r-1 >= 0 && r-1 < 8) && (c-2 >= 0 && c-2 < 8)) {
						row.add(r-1);
						col.add(c-2);
					}
				}
			}
		} else { // empty space so add
			if ((r-1 >= 0 && r-1 < 8) && (c-2 >= 0 && c-2 < 8)) {
				row.add(r-1);
				col.add(c-2);
			}
		}
		// check below, right spaces (2 options)
		//option 1: down two right one
		if(pieceOnSquareTakingColor(r+2, c+1, color)) {
			// if same color piece on space, do not add
		} 
		else if(pieceOnSquareTakingColor(r+2, c+1, oppColor)) { // check for opponent on space, add then break
			if(checkMate) { 
				if ((r+2 >= 0 && r+2 < 8) && (c+1 >= 0 && c+1 < 8)) {
					row.add(r+2);
					col.add(c+1);
				}
			}
			else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r+2, c+1, oppColor) != true){
					if ((r+2 >= 0 && r+2 < 8) && (c+1 >= 0 && c+1 < 8)) {
						row.add(r+2);
						col.add(c+1);
					}
				}
			}
		} 
		else { // empty space so add
			if ((r+2 >= 0 && r+2 < 8) && (c+1 >= 0 && c+1 < 8)) {
				row.add(r+2);
				col.add(c+1);
			}
		}
		// option 2: down one, right two
		if(pieceOnSquareTakingColor(r+1, c+2, color)) {
			// if same color piece on space, do not add
		} 
		else if(pieceOnSquareTakingColor(r+1, c+2, oppColor)) { // check for opponent on space, add then break
			if(checkMate) { // capture king
				if ((r+1 >= 0 && r+1 < 8) && (c+2 >= 0 && c+2 < 8)) {
					row.add(r+1);
					col.add(c+2);
				}
			} 
			else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r+1, c+2, oppColor) != true) {
					if ((r+1 >= 0 && r+1 < 8) && (c+2 >= 0 && c+2 < 8)) {
						row.add(r+1);
						col.add(c+2);
					}
				}
			}
		}
		else { // empty space so add
			if ((r+1 >= 0 && r+1 < 8) && (c+2 >= 0 && c+2 < 8)) {
				row.add(r+1);
				col.add(c+2);
			}
		}
		// check below, left spaces (2 options)
		//option 1: down two, left one
		if(pieceOnSquareTakingColor(r+2, c-1, color)) {
			// if same color piece on space, do not add
		}
		else if(pieceOnSquareTakingColor(r+2, c-1, oppColor)){ // check for opponent on space, add then break
			if(checkMate) { // capture king
				if ((r+2 >= 0 && r+2 < 8) && (c-1 >= 0 && c-1 < 8)) {
					row.add(r+2);
					col.add(c-1);
				}
			} 
			else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r+2, c-1, oppColor) != true) {
					if ((r+2 >= 0 && r+2 < 8) && (c-1 >= 0 && c-1 < 8)) {
						row.add(r+2);
						col.add(c-1);
					}
				}
			}
		}
		else {
			if ((r+2 >= 0 && r+2 < 8) && (c-1 >= 0 && c-1 < 8)) {
				row.add(r+2);
				col.add(c-1);
			}
		}
		// option 2: down one, left two
		if(pieceOnSquareTakingColor(r+1, c-2, color)){
			// if same color piece on space, do not add
		}
		else if(pieceOnSquareTakingColor(r+1, c-2, oppColor)){ // check for opponent on space, add then break
			if(checkMate){ // capture king
				if ((r+1 >= 0 && r+1 < 8) && (c-2 >= 0 && c-2 < 8)) {
					row.add(r+1);
					col.add(c-2);
				}
			}
			else { // not checkmate, make sure piece is not enemy king
				if(kingOnSquare(r+1, c-2, oppColor) != true){
					if ((r+1 >= 0 && r+1 < 8) && (c-2 >= 0 && c-2 < 8)) {
						row.add(r+1);
						col.add(c-2);
					}
				}
			}
		}
		else { // empty space so add
			if ((r+1 >= 0 && r+1 < 8) && (c-2 >= 0 && c-2 < 8)) {
				row.add(r+1);
				col.add(c-2);
			}
		}
		rowAndCol.add(row); // add row, column to combined list
		rowAndCol.add(col);
		return rowAndCol; // return combined list
	}	
	// possible rook movements: returns ArrayList<ArrayList<Integer>>
	private ArrayList<ArrayList<Integer>> legalRookMoves(Piece piece, boolean checkMate){
		ArrayList<Integer> row, col; // list for row, col
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); // combined list to return
		int r = piece.getRow(); // row
		int c = piece.getCol(); // column
		String color = piece.getColor(); // color
		String oppColor = piece.getEnemyColor(); // enemy color
		// check all spaces above piece
		for(int i = r-1; i >= 0; i--) {
			if(pieceOnSquareTakingColor(i, c, color)){ // if same color piece, no add, break
				break;
			}
			else if(pieceOnSquareTakingColor(i, c, oppColor)){ // check for opponent on space, add then break
				if(checkMate) { // capture king
					row.add(i);
					col.add(c);
				} 
				else { // not checkmate so check if piece is enemy king
					if(kingOnSquare(i, c, oppColor) != true){
						row.add(i);
						col.add(c);
					}
				}
				break;
			} 
			else { // empty space so add
				if ((i <= 0 && i < 8) && ( c >= 0 && c < 8)) {
					row.add(i);
					col.add(c);
				}
			}
		}
		// check all spaces below piece
		for(int i = r+1; i < 8; i++) {
			if(pieceOnSquareTakingColor(i, c, color)) { // if same color piece, no add, break
				break;
			} 
			else if(pieceOnSquareTakingColor(i, c, oppColor)) { // check for opponent on space, add then break
				if(checkMate) { // capture king
					row.add(i);
					col.add(c);
				} 
				else { // not checkmate so check if piece is enemy king
					if(kingOnSquare(i, c, oppColor) != true) {
						row.add(i);
						col.add(c);
					}
				}
			}
			else { // empty space so add
				if ((i <= 0 && i < 8) && ( c >= 0 && c < 8)) {
					row.add(i);
					col.add(c);
				}
			}
		}
		// check all spaces to the right of piece
		for(int i = c+1; i < 8; i++){
			if(pieceOnSquareTakingColor(i, c, color)) { // if same color piece, no add, break
				
				break;
			}
			else if(pieceOnSquareTakingColor(i, c, oppColor)) { // check for opponent on space, add then break
				if(checkMate){ // capture king
					row.add(i);
					col.add(c);
				} else { // not checkmate so check if piece is enemy king
					if(kingOnSquare(i, c, oppColor) != true){
						row.add(i);
						col.add(c);
					}
				}
				break;
			}
			else { // empty space so add
				if ((i <= 0 && i < 8) && ( c >= 0 && c < 8)) {
					row.add(i);
					col.add(c);
				}
			}
		}
		// check all spaces to the left of piece
		for(int i = c-1; i >= 0; i--){
			if(pieceOnSquareTakingColor(i, c, color)){ // if same color piece, no add, break
				break;
			}
			else if(pieceOnSquareTakingColor(i, c, oppColor)){ // check for opponent on space, add then break
				if(checkMate){ // capture king
					row.add(i);
					col.add(c);
				}
				else { // not checkmate so check if piece is enemy king
					if(kingOnSquare(i-2, c-1, oppColor) != true){
						if ((i <= 0 && i < 8) && ( c >= 0 && c < 8)) {
							row.add(i);
							col.add(c);
						}
					}
				}
				break;
			}
			else { // empty space so add
				if ((i <= 0 && i < 8) && ( c >= 0 && c < 8)) {
					row.add(i);
					col.add(c);
				}
			}
		}
		rowAndCol.add(row); // add row, column to combined list
		rowAndCol.add(col);
		return rowAndCol; // return combined list
	}
	// possible king movements: returns ArrayList<ArrayList<Integer>>
	private ArrayList<ArrayList<Integer>> legalKingMoves(Piece piece, boolean checkMate){
		ArrayList<Integer> row, col; // row, column list
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); // combined list to be returned
		int r = piece.getRow(); // row
		int c = piece.getCol(); // column
		String color = piece.getColor(); // color
		String oppColor = piece.getEnemyColor(); // enemy color

		if(!pieceOnSquareTakingColor(r, c+1, color)) {
			if(checkMate){ 
				row.add(r);
				col.add(c+1);
			}
			else { 
				if(kingOnSquare(r-2, c-1, oppColor) != true ){
					if (c != 7) {
						row.add(r);
						col.add(c+1);
					}
				}
			}
		}
		if(pieceOnSquareTakingColor(r, c-1, color) != true) { 
			if(checkMate){ 
				row.add(r);
				col.add(c-1);
			}
			else { 
				if(kingOnSquare(r-2, c-1, oppColor) != true){
					if (r != 0 && c != 0) {
						row.add(r-1);
						col.add(c-1);
					}
				}
			}
		}
		// space above
		if(pieceOnSquareTakingColor(r-1, c, color) != true) {
			if(checkMate) {
				row.add(r-1);
				col.add(c);
			}
			else { 
				if(kingOnSquare(r-1, c, oppColor) != true){
					if (r != 0) {
						row.add(r-1);
						col.add(c);
					}
				}
			}
		}
		// space below
		if(pieceOnSquareTakingColor(r+1, c, color) != true) { 
			if(checkMate){ 
				row.add(r+1);
				col.add(c);
			} 
			else {
				if(kingOnSquare(r+1, c, oppColor) != true) {
					if (r != 7) {
						row.add(r+1);
						col.add(c);
					}
				}
			}
		}
		
		if(pieceOnSquareTakingColor(r-1, c+1, color) != true) { 
			if(checkMate) {
				row.add(r-1);
				col.add(c+1);
			}
			else { 
				if(kingOnSquare(r-1, c+1, oppColor) != true) {
					if (r != 0 && c != 7) {
						row.add(r-1);
						col.add(c+1);
					}
				}
			}
		}

		if(pieceOnSquareTakingColor(r-1, c-1, color) != true) { 
			if(checkMate) {
				row.add(r-1);
				col.add(c-1);
			} 
			else { 
				if(kingOnSquare(r-1, c-1, oppColor) != true) {
					if (r != 0 && c != 0) {
						row.add(r-1);
						col.add(c-1);
					}
				}
			}
		}

		if(pieceOnSquareTakingColor(r+1, c+1, color) != true) { 
			if(checkMate) { 
				row.add(r+1);
				col.add(c+1);
			}
			else { 
				if(kingOnSquare(r+1, c+1, oppColor) != true) {
					if (r != 7 && c != 7) {
						row.add(r+1);
						col.add(c+1);
					}
				}
			}
		}
		
		if(pieceOnSquareTakingColor(r+1, c-1, color) != true){ 
			if(checkMate) { 
				row.add(r+1);
				col.add(c-1);
			}
			else { 
				if(kingOnSquare(r+1, c-1, oppColor) != true) {
					if (r != 7 && c != 0) {
						row.add(r+1);
						col.add(c-1);
					}
				}
			}
		}
		rowAndCol.add(row); 
		rowAndCol.add(col);
		return rowAndCol; 
	}

	private ArrayList<ArrayList<Integer>> legalQueenMoves(Piece piece, boolean checkMate){
		ArrayList<Integer> row, col; // row, col list
		row = new ArrayList<Integer>();
		col = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> rowAndCol = new ArrayList<ArrayList<Integer>>(); 

		ArrayList<ArrayList<Integer>> bishop, rook;
		bishop = legalBishopMoves(piece, checkMate);
		rook = legalRookMoves(piece, checkMate);
		row.addAll(bishop.get(0));
		col.addAll(bishop.get(1));
		row.addAll(rook.get(0));
		col.addAll(rook.get(1));
		rowAndCol.add(row); 
		rowAndCol.add(col);
		return rowAndCol; 
	}

	public ArrayList<Piece> possEnemyTargets(Piece piece){
		ArrayList<Piece> enemyTargets = new ArrayList<Piece>(); 
		ArrayList<ArrayList<Integer>> legalMoves = legalPieceMoves(piece, false, piece.getColor()); 
		ArrayList<Integer> row = legalMoves.get(0); 
		ArrayList<Integer> column = legalMoves.get(1); 
		ListIterator<Integer> rowIter = row.listIterator(); 
		ListIterator<Integer> columnIter = column.listIterator();
		int nextRow, nextColumn;
		while(rowIter.hasNext() && columnIter.hasNext()) { 
			nextRow = rowIter.next();
			nextColumn = columnIter.next();
			if(pieceOnSquareTakingColor(nextRow, nextColumn, piece.getEnemyColor())) { 
				enemyTargets.add(board.getPieces(nextRow, nextColumn, piece.getEnemyColor()));
			}
		}
		return enemyTargets;
	}
}