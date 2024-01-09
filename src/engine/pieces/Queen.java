package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Move;
import engine.movements.Movement;

public class Queen extends Piece {
    Movement[] movements;

    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color, new Movement[]{
                new Movement(Move.HORIZONTAL, 8, -8),
                new Movement(Move.VERTICAL, 8, -8),
                new Movement(Move.DIAGONAL, 12, -12)
        });
    }

}
