import java.util.ArrayList;
public class Piece{

	private String color;
	private String name; // name of piece
	private int row, col; // position on board
	private double value; // would be used for min max AI
	private ArrayList<ArrayList<Integer>> legalMoves;

	public Piece(){
	}

	public void setLegalMoves(ArrayList<ArrayList<Integer>> legalMoves) {
		this.legalMoves = legalMoves;
	}

	public ArrayList<ArrayList<Integer>> getLegalMoves() {
		return this.legalMoves;
	}
	// constructor for to copy a piece
	public Piece (Piece piece) { 
		this.color = piece.color;
		this.name = piece.name;
		this.row = piece.row;
		this.col = piece.col;
	}
	// constructor
	public Piece(String color, String name, int row, int col) { 
		this.color = color;
		this.name = name;
		this.row = row;
		this.col = col;
	}

	public String getPieceName() {
		String name = "";
		if(getColor().equals("white") && getName() != null){
			name = "W";
			if (getName().equals("king")) {
				name += "K ";
			}
			else if (getName().equals("queen")) {
				name += "Q ";
			} 
			else if (getName().equals("rook")) {
				name += "R ";
			}
			else if (getName().equals("knight")) {
				name += "Kn";
			}
			else if (getName().equals("bishop")) {
				name += "B ";
			}
			else {
				name += "P ";
			}
		} else {
			name = "B";
			if (getName().equals("king")) {
				name += "K ";
			}
			else if (getName().equals("queen")) {
				name += "Q ";
			} 
			else if (getName().equals("rook")) {
				name += "R ";
			}
			else if (getName().equals("knight")) {
				name += "Kn";
			}
			else if (getName().equals("bishop")) {
				name += "B ";
			}
			else {
				name += "P ";
			}
		}
		return name;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public String getEnemyColor() {
		if(this.color.equals("white")){
			return "black";
		} else {
			return "white";
		}
	}

	public void setColor(String string){
		this.color = string;
	}	

	public int getRow(){
		return row;
	}	

	public void setRow(int row){
		this.row = row;
	}	

	public int getCol(){
		return col;
	}	

	public void setCol(int col){
		this.col = col;
	}

	public double setValue() {
		value = 0;
		if (getName() != null) {
			if (getColor().equals("white")) {
				if (getName().equals("king")) {
					value = -10000;
				}
				if (getName().equals("queen")) {
					value = -9;
				} 
				if (getName().equals("rook")) {
					value = -5;
				}
				if (getName().equals("knight")) {
					value = -3;
				}
				if (getName().equals("bishop")) {
					value = -3.5;
				}
				if (getName().equals("pawn")) {
					value = -1;
				}
			}
			else if (getColor().equals("black")) {
				if (getName().equals("king")) {
					value = 200;
				}
				if (getName().equals("queen")) {
					value = 9;
				} 
				if (getName().equals("rook")) {
					value = 5;
				}
				if (getName().equals("knight")) {
					value = 3;
				}
				if (getName().equals("bishop")) {
					value = 3.5;
				}
				if (getName().equals("pawn")) {
					value = 1;
				}
			}
		}
		return value;
	}

	public double getPieceValue() {
		return value;
	}
}