package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.*;

public class King extends Piece {
    Movement[] movements;

    public King(PlayerColor color) {
        super(PieceType.KING, color, new Movement[]{
                new Movement(Move.HORIZONTAL, 1, -1),
                new Movement(Move.VERTICAL, 1, -1),
                new Movement(Move.DIAGONAL, 1, -1)
        });
    }

}
