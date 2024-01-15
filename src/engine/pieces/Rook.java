package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Movements;
import engine.movements.Step;
import engine.utils.BoardDimensions;
import engine.utils.Coordinates;

public class Rook extends Piece {
    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color, new Movements(new Step[]{
                //Horizontal
                new Step(new Coordinates(1, 0),
                        BoardDimensions.WIDTH.getValue(),
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1, 0),
                        BoardDimensions.WIDTH.getValue(),
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),

                //Vertical
                new Step(new Coordinates(0, 1),
                        BoardDimensions.HEIGHT.getValue(),
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(0, -1),
                        BoardDimensions.HEIGHT.getValue(),
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
        }));
    }
}