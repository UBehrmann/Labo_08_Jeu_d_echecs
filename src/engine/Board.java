package engine;

import chess.PlayerColor;
import engine.movements.Coordinates;
import engine.movements.Move;
import engine.movements.Movement;
import engine.pieces.*;

import java.awt.*;
import java.util.Objects;

public class Board {

    public interface PieceListener {
        void action(Piece piece, Cell cell);
    }
    private PieceListener onAddPiece;
    private PieceListener onRemovePiece;
    private int width;
    private int height;
    private Cell[][] cells;
    private int turn = 1;

    public Board(int width, int height) {

        this.width = width;
        this.height = height;

        this.cells = new Cell[width][height];

        reset();
    }

    public boolean doMovement( int x1, int y1, int x2, int y2 ) {

        // Check if the piece is on the board
        Piece piece = cells[x1][y1].getPiece();

        if(piece == null) return false;

        // Check if the piece is the same color as the current player
        if(piece.getColor() != getCurrentPlayer()) return false;

        // Define the inital and final position
        PlayerColor playerColor = piece.getColor();
        Coordinates positionInitial = new Coordinates(x1, playerColor == PlayerColor.BLACK ? y2 : y1);
        Coordinates positionFinal = new Coordinates(x2, playerColor == PlayerColor.BLACK ? y2 : y1);

        // Check if the piece can move to the new position
        if(piece.possibleMovement(positionInitial, positionFinal)) return false;

        // Get possible movement
        Coordinates[] possibleMovement = piece.getPossibleMovementCoordinates(this, positionInitial, positionFinal);


        // Check if the part eats another

        //Recupéerer les coordonnée possible

        //TODO a faire ...

        // do the movement
        movePiece(new Coordinates(x1, y1), new Coordinates(x2, y2));

        return true;
    }

    private void movePiece(Coordinates positionInitial, Coordinates positionFinal) {

        Piece piece = cells[positionInitial.getX()][positionInitial.getY()].getPiece();

        removePiece(positionInitial);

        setPiece(piece, positionFinal);

        // Update the turn
        turn++;
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
        addPiece(new Rook( PlayerColor.WHITE ), new Coordinates(0, 0));
        addPiece(new Knight( PlayerColor.WHITE ), new Coordinates(1, 0));
        addPiece(new Bishop( PlayerColor.WHITE ), new Coordinates(2, 0));
        addPiece(new Queen( PlayerColor.WHITE ), new Coordinates(3, 0));
        addPiece(new King( PlayerColor.WHITE ), new Coordinates(4, 0));
        addPiece(new Bishop( PlayerColor.WHITE ), new Coordinates(5, 0));
        addPiece(new Knight( PlayerColor.WHITE ), new Coordinates(6, 0));
        addPiece(new Rook( PlayerColor.WHITE ), new Coordinates(7, 0));
        for ( int i = 0; i < 8; i++ ) {
            addPiece(new Pawn( PlayerColor.WHITE ), new Coordinates(i, 1));
        }

        // Black pieces
        addPiece(new Rook( PlayerColor.BLACK ), new Coordinates(0, 7));
        addPiece(new Knight( PlayerColor.BLACK ), new Coordinates(1, 7));
        addPiece(new Bishop( PlayerColor.BLACK ), new Coordinates(2, 7));
        addPiece(new Queen( PlayerColor.BLACK ), new Coordinates(3, 7));
        addPiece(new King( PlayerColor.BLACK ), new Coordinates(4, 7));
        addPiece(new Bishop( PlayerColor.BLACK ), new Coordinates(5, 7));
        addPiece(new Knight( PlayerColor.BLACK ), new Coordinates(6, 7));
        addPiece(new Rook( PlayerColor.BLACK ), new Coordinates(7, 7));
        for ( int i = 0; i < 8; i++ ) {
            addPiece(new Pawn( PlayerColor.BLACK ), new Coordinates(i, 6));
        }

    }

    public void addPiece(Piece piece, Coordinates position) {
        setPiece(piece, position);
    }

    public void setPiece(Piece piece, Coordinates position) {

        Objects.requireNonNull(piece, "Piece cannot be null");

        checkPositionOnBoard(position);

        cells[position.getX()][position.getY()].setPiece(piece);

        if(onAddPiece != null) {
            onAddPiece.action(piece, cells[position.getX()][position.getY()]);
        }
    }

    public void removePiece(Coordinates position) {
        checkPositionOnBoard(position);
        cells[position.getX()][position.getX()].removePiece();

        if(onRemovePiece != null) {
            onRemovePiece.action(null, cells[position.getX()][position.getX()]);
        }
    }

    private void checkPositionOnBoard(Coordinates position) {
        if(position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height)
            throw new IllegalArgumentException("Position out of board");
    }

    public PlayerColor getCurrentPlayer() {
        return turn % 2 == 1 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public void setAddPieceListener(PieceListener listener) {
        this.onAddPiece = listener;
    }

    public void setRemovePieceListener(PieceListener listener) {
        this.onRemovePiece = listener;
    }

}
