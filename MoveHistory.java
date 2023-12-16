public class MoveHistory {
    private Move m;
    private Board b;

    public MoveHistory (Board board, Move move) {
        this.b = new Board(board);
        this.m = new Move(move);
    }
    public Board getBoard() {
        // System.out.println("stack pieces: " + this.b.getPieces());
        return this.b;
    }

    public Move getMover() {
        return this.m;
    }
}
