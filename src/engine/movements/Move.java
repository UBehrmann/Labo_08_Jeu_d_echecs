package engine.movements;

import engine.utils.BoardDimensions;
import engine.utils.Coordinates;
import engine.utils.Steps;

import java.util.ArrayList;
import java.util.Map;

public class Move {
    Direction direction;
    int maxStep;
    private static final Map<Direction, Steps>  DIRECTION_TO_STEPS   = Direction.createDirectionToSteps();


    public Move(Direction direction, int maxStep){
        if(maxStep < 0) throw new RuntimeException("maxStep must be >= 0!");
        this.direction  = direction;
        this.maxStep    = maxStep;
    }

    public boolean moveAngleIsOk(int angleDegree){
        if(DIRECTION_TO_STEPS.containsKey(this.direction)){
            return DIRECTION_TO_STEPS.get(this.direction).angleIsOk(angleDegree);
        }
        return false;
    }
    public boolean moveStepIsOk(Coordinates positionInitial, Coordinates positionFinal){
        ArrayList<Coordinates> stepsCoordinates = getMoveStepPossible(positionInitial, positionFinal);
        if(stepsCoordinates == null) return false;

        for (Coordinates stepsCoordinate : stepsCoordinates) {
            Coordinates nextStepCoordinates = stepsCoordinate;

            if ((this.maxStep == 1) && (Coordinates.equal(Coordinates.addition(positionInitial, nextStepCoordinates), positionFinal)))
                return true;

            for(int i = 0; i < this.maxStep; ++i){
                if(Coordinates.equal(Coordinates.addition(positionInitial, nextStepCoordinates), positionFinal))
                    return true;
                nextStepCoordinates = Steps.nextStep(stepsCoordinate);
            }

        }
        return false;
    }

    public ArrayList<Coordinates> getMoveStepPossible(Coordinates positionInitial, Coordinates positionFinal){
        int angleDegree = (int)Coordinates.getAngle(positionInitial, positionFinal);
        if(!moveAngleIsOk(angleDegree)) return null;

        //Recuperer tout les steps que l'on peut faire dans le quadrant correspondant à l'angle
        ArrayList<Coordinates> stepsCoordinates = DIRECTION_TO_STEPS.get(this.direction).getStepCoordinates(angleDegree);
        if(stepsCoordinates == null) return null;

        ArrayList<Coordinates> coordinatesPossibleSteps = new ArrayList<>();
        for (Coordinates stepsCoordinate : stepsCoordinates) {
            Coordinates nextStepCoordinates = Steps.nextStep(stepsCoordinate);
            int x = positionInitial.getX() + nextStepCoordinates.getX();
            int y = positionInitial.getY() + nextStepCoordinates.getY();

            if ((Math.abs(y) < BoardDimensions.HEIGHT.getValue()) && (Math.abs(x) < BoardDimensions.WIDTH.getValue()))
                coordinatesPossibleSteps.add(stepsCoordinate);
        }
        return coordinatesPossibleSteps;
    }

    public ArrayList<Coordinates>[] getMovePossible(Coordinates positionInitial, Coordinates positionFinal){
        int angleDegree = (int)Coordinates.getAngle(positionInitial, positionFinal);
        if(!moveAngleIsOk(angleDegree)) return null;

        //Recuperer tout les steps que l'on peut faire dans le quadrant correspondant à l'angle
        ArrayList<Coordinates> stepsCoordinates = DIRECTION_TO_STEPS.get(this.direction).getStepCoordinates(angleDegree);
        if(stepsCoordinates == null) return null;

        ArrayList<Coordinates>[] coordinatesPossibleSteps = new ArrayList[stepsCoordinates.size()];
        // Initialisez chaque élément du tableau avec une nouvelle ArrayList
        for (int i = 0; i < stepsCoordinates.size(); i++) {
            coordinatesPossibleSteps[i] = new ArrayList<>();
        }

        for(int i = 0; i < stepsCoordinates.size(); ++i){
            Coordinates stepCoordinates = stepsCoordinates.get(i);
            for(int j = 0; j < this.maxStep; ++j){
                coordinatesPossibleSteps[i].add(stepCoordinates);
                stepCoordinates = Steps.nextStep(stepCoordinates);
            }
        }
        return coordinatesPossibleSteps;
    }
}
