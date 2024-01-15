package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Movements;
import engine.movements.Step;
import engine.utils.Coordinates;

public class Pawn extends Piece {

    public Pawn(PlayerColor color) {
        super(PieceType.PAWN, color, new Movements(new Step[]{
                //Vertical
                new Step(new Coordinates(0,1), 2, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                // "En passant" or attack
                new Step(new Coordinates(1,1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1,1), 1, color == PlayerColor.BLACK, color == PlayerColor.BLACK),
        }));
        this.firstMovement = true;
    }

    public void clearFirstMovement() {
        super.setMovements(new Movements(new Step[]{
                //Vertical
                new Step(new Coordinates(0,1), 1, super.getColor() == PlayerColor.BLACK, super.getColor() == PlayerColor.BLACK),
                // "En passant" or attack
                new Step(new Coordinates(1,1), 1, super.getColor() == PlayerColor.BLACK, super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(-1,1), 1, super.getColor() == PlayerColor.BLACK, super.getColor() == PlayerColor.BLACK),
        }));
        this.firstMovement = false;
    }
}