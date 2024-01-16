package engine.utils;

import engine.pieces.*;

public class Cell {

    private Piece piece;

    private final int x;
    private final int y;

    /**
     * Constructor to initialize a Cell object with a piece and its position (x, y).
     *
     * @param piece the piece placed on the cell (can be null for an empty cell)
     * @param x     the x-coordinate of the cell's position
     * @param y     the y-coordinate of the cell's position
     */
    public Cell(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }

    /**
     * Set the piece on the cell to a new piece.
     *
     * @param piece the new piece to be placed on the cell
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Get the piece currently on the cell.
     *
     * @return the piece on the cell (can be null for an empty cell)
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Check if the cell is empty (contains no piece).
     *
     * @return true if the cell is empty, false otherwise
     */
    public boolean isEmpty() {
        return piece == null;
    }

    /**
     * Remove the piece from the cell, making it empty.
     */
    public void removePiece() {
        piece = null;
    }

    /**
     * Get the x-coordinate of the cell's position.
     *
     * @return the x-coordinate of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of the cell's position.
     *
     * @return the y-coordinate of the cell
     */
    public int getY() {
        return y;
    }
}
