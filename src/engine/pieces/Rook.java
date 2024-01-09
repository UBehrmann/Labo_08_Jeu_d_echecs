package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Move;
import engine.movements.Movement;

public class Rook extends Piece {
    Movement[] movements;

    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color, new Movement[]{
                new Movement(Move.HORIZONTAL, 8, -8),
                new Movement(Move.VERTICAL, 8, -8)
        });
    }

}
