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

    public void setTest1(){
        addPiece(new King(PlayerColor.WHITE), new Coordinates(0, 0));
        addPiece(new King(PlayerColor.BLACK), new Coordinates(7, 7));

    }

    public void setTestPawn(){
        addPiece(new Pawn(PlayerColor.WHITE), new Coordinates(4, 1));
        addPiece(new Pawn(PlayerColor.BLACK), new Coordinates(3, 2));
    }
}
