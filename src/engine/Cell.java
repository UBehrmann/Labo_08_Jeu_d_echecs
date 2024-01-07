package engine;

import engine.pieces.*;

public class Cell {

    private Piece piece;

    private int x;
    private int y;

    public Cell( Piece piece , int x, int y ) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void removePiece() {
        piece = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
