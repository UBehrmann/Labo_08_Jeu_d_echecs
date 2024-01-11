package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Direction;
import engine.movements.Move;
import engine.movements.Movement;

public class Knight extends Piece {
    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color, new Movement[]{
                new Movement(Direction.SPECIAL, 3)
        });
    }

}
