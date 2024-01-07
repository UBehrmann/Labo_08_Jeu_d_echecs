package engine;

import chess.PlayerColor;
import engine.pieces.*;

import java.util.Objects;

public class Board {

    public interface PieceListener {
        void action(Piece piece, Cell cell);
    }
    private PieceListener onAddPiece;
    private int width;
    private int height;
    private Cell[][] cells;
    private int turn;

    public Board(int width, int height) {

        this.width = width;
        this.height = height;

        this.cells = new Cell[width][height];

        reset();
    }

    public boolean doMovement( int x1, int y1, int x2, int y2 ) {
        return true;
    }

    public boolean isCheck( PlayerColor color ) {
        return true;
    }

    public boolean isCheckMate( PlayerColor color ) {
        return true;
    }

    public boolean isStaleMate( PlayerColor color ) {
        return true;
    }

    public boolean isDraw() {
        return true;
    }

    public void promote( Piece piece ) {
    }

    public void reset() {

        // Remove all pieces from the board
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                cells[i][j] = new Cell(null, i, j);

    }

    public void initialize() {

        // Put the pieces on the board
        // White pieces
        addPiece(new Rook( PlayerColor.WHITE ), 0, 0);
        addPiece(new Knight( PlayerColor.WHITE ), 1, 0);
        addPiece(new Bishop( PlayerColor.WHITE ), 2, 0);
        addPiece(new Queen( PlayerColor.WHITE ), 3, 0);
        addPiece(new King( PlayerColor.WHITE ), 4, 0);
        addPiece(new Bishop( PlayerColor.WHITE ), 5, 0);
        addPiece(new Knight( PlayerColor.WHITE ), 6, 0);
        addPiece(new Rook( PlayerColor.WHITE ), 7, 0);

        for ( int i = 0; i < 8; i++ ) {
            addPiece(new Pawn( PlayerColor.WHITE ), i, 1);
        }

        // Black pieces
        addPiece(new Rook( PlayerColor.BLACK ), 0, 7);
        addPiece(new Knight( PlayerColor.BLACK ), 1, 7);
        addPiece(new Bishop( PlayerColor.BLACK ), 2, 7);
        addPiece(new Queen( PlayerColor.BLACK ), 3, 7);
        addPiece(new King( PlayerColor.BLACK ), 4, 7);
        addPiece(new Bishop( PlayerColor.BLACK ), 5, 7);
        addPiece(new Knight( PlayerColor.BLACK ), 6, 7);
        addPiece(new Rook( PlayerColor.BLACK ), 7, 7);

        for ( int i = 0; i < 8; i++ ) {
            addPiece(new Pawn( PlayerColor.BLACK ), i, 6);
        }

    }

    public void addPiece(Piece piece, int x, int y) {
        setPiece(piece, x, y);
    }

    public void setPiece(Piece piece, int x, int y) {

        Objects.requireNonNull(piece, "Piece cannot be null");

        checkPositionOnBoard(x, y);

        cells[x][y].setPiece(piece);

        if(onAddPiece != null) {
            onAddPiece.action(piece, cells[x][y]);
        }
    }

    private void checkPositionOnBoard(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height)
            throw new IllegalArgumentException("Position out of board");
    }

    public PlayerColor getCurrentPlayer() {
        return turn % 2 == 1 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public void setPieceListener(PieceListener listener) {

        this.onAddPiece = listener;
    }

}
