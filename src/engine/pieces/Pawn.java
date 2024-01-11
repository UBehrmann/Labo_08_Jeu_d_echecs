package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Direction;
import engine.movements.Move;
import engine.movements.Movement;

public class Pawn extends Piece {
    boolean firstMovement;

    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color, new Movement[]{
                new Movement(Direction.VERTICAL, 1),
                new Movement(Direction.VERTICAL, 2)
        });
        this.firstMovement = true;
    }

    public boolean isFirstMovement() {
        return this.firstMovement;
    }
    public void clearFirstMovement() {
        super.setMovements(new Movement[]{
                new Movement(Direction.VERTICAL, 1)});
        this.firstMovement = false;
    }
}
