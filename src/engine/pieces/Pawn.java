package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Move;
import engine.movements.Movement;

public class Pawn extends Piece {
    Movement[] movements;
    boolean firstMovement;

    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color, new Movement[]{
                new Movement(Move.VERTICAL, 1, 0)
        });
        setFirstMovement();
    }

    public boolean isFirstMovement() {
        return this.firstMovement;
    }

    public void setFirstMovement(){
        this.firstMovement = true;
    }

    public void clearFirstMovement(){
        this.firstMovement = false;
    }
}
