package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.HorizontalMovement;
import engine.movements.Movement;

public class Rook extends Piece {

    public Rook(PlayerColor color) {

        super(PieceType.ROOK, color);

        Movement[] movements = new Movement[1];

        movements[0] = new HorizontalMovement(1);

        setMovements(movements);
    }

}
