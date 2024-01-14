package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.*;
import engine.utils.Coordinates;

import java.util.ArrayList;

public abstract class Piece {
    private PieceType type;
    private PlayerColor color;
    private Movements movements;
    private boolean hasMoved;


    public Piece(PieceType type, PlayerColor color, Movements movements) {
        if (type == null) throw new IllegalArgumentException("Piece type cannot be null");
        if (color == null) throw new IllegalArgumentException("Player color cannot be null");
        if (movements == null) throw new IllegalArgumentException("Movements cannot be null");

        this.type = type;
        this.color = color;
        this.movements = movements;
    }


    protected void setMovements(Movements movements){
        this.movements = movements;
    }


    public PieceType getType() {
        return this.type;

    }
    public PlayerColor getColor() {
        return color;
    }
    public Coordinates[] getPossibleMovement(Coordinates positionInitial, Coordinates positionFinal){
        Step possibleStep = this.movements.getPossibleStep(positionInitial, positionFinal);
        if(possibleStep == null) return null;

        Coordinates[] possibleStepCoordinates = possibleStep.getSteps();
        if(possibleStepCoordinates == null) return null;

        Coordinates[] c = new Coordinates[possibleStepCoordinates.length];
        for(int i = 0; i < possibleStepCoordinates.length; ++i) c[i] = new Coordinates(Coordinates.addition(possibleStepCoordinates[i], positionInitial));

        return c;
    }
    public boolean movementIsOk(Coordinates positionInitial, Coordinates positionFinal){
        Coordinates[] possibleMovement = getPossibleMovement(positionInitial, positionFinal);
        if(possibleMovement == null) return false;

        for(Coordinates c : possibleMovement){
            if(Coordinates.equal(c,positionFinal)) return true;
        }
        return false;
    }

}

