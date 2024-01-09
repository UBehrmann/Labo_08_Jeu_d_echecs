package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Move;
import engine.movements.Movement;

public class Knight extends Piece {
    Movement[] movements;
    public Knight(PlayerColor color) {
        super(PieceType.KNIGHT, color, new Movement[]{
                new Movement(Move.SPECIAL, 3, -3)
        });
    }

}
