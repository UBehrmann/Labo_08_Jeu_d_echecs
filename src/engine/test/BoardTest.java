package engine.test;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.utils.Coordinates;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Objects;

public class BoardTest extends Board {

    public BoardTest(int width, int height) {
        super(width, height);
    }

    /**
     * Set the board to test the pawn movement and attack
     *
     * @return void
     */
    public void setTestPawn(){
        addPiece(new Pawn(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Pawn(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the knight movement
     *
     * @return void
     */
    public void setTestKnight(){
        addPiece(new Knight(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Knight(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the bishop movement
     *
     * @return void
     */
    public void setTestBishop(){
        addPiece(new Bishop(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Bishop(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the rook movement
     *
     * @return void
     */
    public void setTestRook(){
        addPiece(new Rook(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the queen movement
     *
     * @return void
     */
    public void setTestQueen(){
        addPiece(new Queen(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Queen(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the king movement
     *
     * @return void
     */
    public void setTestKing(){
        addPiece(new King(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new King(PlayerColor.BLACK), new Coordinates(3, 2));
    }

    /**
     * Set the board to test the pawn promotion
     *
     * @return void
     */
    public void setTestPawnPromotion(){
        addPiece(new Pawn(PlayerColor.WHITE), new Coordinates(4, 6));
        addPiece(new Pawn(PlayerColor.BLACK), new Coordinates(3, 1));
    }

    /**
     * Set the board to test the check
     *
     * @return void
     */
    public void setTestCheck(){
        addPiece(new King(PlayerColor.WHITE), new Coordinates(4, 0));
        addPiece(new King(PlayerColor.BLACK), new Coordinates(3, 2));
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(4, 7));
    }

    /**
     * Set the board to the castling test
     *
     * @return void
     */
    public void setTestCastling() {
        addPiece(new King(PlayerColor.WHITE), new Coordinates(4, 0));
        addPiece(new Rook(PlayerColor.WHITE), new Coordinates(0, 0));
        addPiece(new Rook(PlayerColor.WHITE), new Coordinates(7, 0));
        addPiece(new King(PlayerColor.BLACK), new Coordinates(4, 7));
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(0, 7));
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(7, 7));
    }

}
