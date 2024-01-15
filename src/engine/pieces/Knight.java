package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Movements;
import engine.movements.Step;
import engine.utils.Coordinates;

public class Knight extends Piece {
    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color, new Movements(new Step[]{
                //Special
                new Step(new Coordinates(1,2), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(1,-2), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1,2), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1,-2), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),

                new Step(new Coordinates(2,1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(2,-1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-2,1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-2,-1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
        }));
    }
}