package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Move;
import engine.movements.Movement;

public class Bishop extends Piece {
    Movement[] movements;
    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color, new Movement[]{
                new Movement(Move.DIAGONAL, 12, -12)
        });
    }

}
