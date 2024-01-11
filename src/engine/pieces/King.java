package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.*;

public class King extends Piece {
    public King(PlayerColor color) {
        super(PieceType.KING, color, new Movement[]{
                new Movement(Direction.HORIZONTAL, 1),
                new Movement(Direction.VERTICAL, 1),
                new Movement(Direction.DIAGONAL, 1)
        });
    }
}
