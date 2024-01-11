package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Direction;
import engine.movements.Move;
import engine.movements.Movement;

public class Queen extends Piece {
    public Queen(PlayerColor color) {
        super(PieceType.QUEEN, color, new Movement[]{
                new Movement(Direction.HORIZONTAL, 8),
                new Movement(Direction.VERTICAL, 8),
                new Movement(Direction.DIAGONAL, 12)
        });
    }

}
