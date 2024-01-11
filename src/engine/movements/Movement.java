package engine.movements;
import engine.utils.Coordinates;

import java.util.ArrayList;

public class Movement {
    Move move;

    public Movement(Direction direction, int maxStep){
        this.move = new Move(direction, maxStep);
    }
    public boolean movementIsOk(Coordinates positionInitial, Coordinates positionFinal){
        return this.move.moveStepIsOk(positionInitial, positionFinal);
    }
    public ArrayList<Coordinates> getMovementPossible(Coordinates positionInitial, Coordinates positionFinal){
        return this.move.getMoveStepPossible(positionInitial, positionFinal);
    }
}
