package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Direction;
import engine.movements.Move;
import engine.movements.Movement;

public class Bishop extends Piece {
    public Bishop(PlayerColor color) {
        super(PieceType.BISHOP, color, new Movement[]{
                new Movement(Direction.DIAGONAL, 12)
        });
    }
}
