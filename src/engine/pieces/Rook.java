package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Direction;
import engine.movements.Move;
import engine.movements.Movement;

public class Rook extends Piece {
    public Rook(PlayerColor color) {
        super(PieceType.ROOK, color, new Movement[]{
                new Movement(Direction.HORIZONTAL, 8),
                new Movement(Direction.VERTICAL, 8)
        });
    }

}
