package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.HorizontalMovement;
import engine.movements.Movement;
import engine.movements.VerticalMovement;

public class Pawn extends Piece {

    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color);

        Movement[] movements = new Movement[1];

        movements[0] = new VerticalMovement(1, color == PlayerColor.WHITE);

        setMovements(movements);
    }
}
