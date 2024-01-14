package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Movements;
import engine.movements.Step;
import engine.utils.BoardDimensions;
import engine.utils.Coordinates;

public class Bishop extends Piece {
    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color, new Movements(new Step[]{
                //Diagonal
                new Step(new Coordinates(1,1), BoardDimensions.DIAGONAL.getValue(), color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(1,-1), BoardDimensions.DIAGONAL.getValue(), color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1,1), BoardDimensions.DIAGONAL.getValue(), color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1,-1), BoardDimensions.DIAGONAL.getValue(), color == PlayerColor.BLACK, color == PlayerColor.BLACK),
        }));
    }
}
