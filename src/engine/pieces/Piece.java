package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.movements.*;

public abstract class Piece {
    private PieceType type;
    private PlayerColor color;
    private Movement[] movements;
    private boolean hasMoved;

    public Piece(PieceType type, PlayerColor color, Movement[] movements) {
        this.type = type;
        this.color = color;
        this.movements = movements;
    }

    public PieceType getType() {

        if(this instanceof Pawn)
            return PieceType.PAWN;
        else if(this instanceof Rook)
            return PieceType.ROOK;
        else if(this instanceof Knight)
            return PieceType.KNIGHT;
        else if(this instanceof Bishop)
            return PieceType.BISHOP;
        else if(this instanceof Queen)
            return PieceType.QUEEN;
        else if(this instanceof King)
            return PieceType.KING;
        else
            return null;

    }

    public PlayerColor getColor() {
            return color;
    }

    public boolean possibleMovement(Coordinates positionInitial, Coordinates positionFinal){
            for(Movement movement: this.movements){
                if (movement.movementIsOk(positionInitial, positionFinal)) return true;
            }
            return false;
    }

    private Movement getPossibleMovement(Coordinates positionInitial, Coordinates positionFinal){
            for(Movement movement: this.movements){
                if (movement.movementIsOk(positionInitial, positionFinal)) return movement;
            }
            return null;
    }


    public Coordinates[] getPossibleMovementCoordinates(Board board, Coordinates positionInitial, Coordinates positionFinal){
        Movement possibleMovement = getPossibleMovement(positionInitial, positionFinal);
        if(possibleMovement == null) return null;

    }
}

