package engine.utils;

import engine.utils.Coordinates;

import java.util.ArrayList;

public class Steps {
    ArrayList<Coordinates>[] stepsCoordinates = new ArrayList[Quadrant.NB_QUADRANT];
    double[] anglesDegree;

    public Steps(Coordinates[] stepsCoordinates){
        if(stepsCoordinates == null) throw new RuntimeException("Steps cannot be empty!");
        Coordinates originCoordinates = new Coordinates(0,0);

        // Initialize each ArrayList within the array
        for (int i = 0; i < Quadrant.NB_QUADRANT; i++) {
            this.stepsCoordinates[i] = new ArrayList<>();
        }
        for(Coordinates stepCoordinates : stepsCoordinates){
            Quadrant quadrant = Quadrant.coordinatesIsInQuadrant(originCoordinates, stepCoordinates);
            this.stepsCoordinates[quadrant.getValue()].add(stepCoordinates);
        }

        this.anglesDegree       = new double[stepsCoordinates.length];
        for(int i = 0; i < stepsCoordinates.length; ++i)
            this.anglesDegree[i] = Math.toDegrees(Math.atan2(stepsCoordinates[i].getY(), stepsCoordinates[i].getX()));
    }

    public boolean angleIsOk(int angleDegree){
        for (double angle : this.anglesDegree) {
            if (angleDegree == (int) angle) return true;
        }
        return false;
    }

    public boolean angleIsOk(double angleDegree){
        for (double angle : this.anglesDegree) {
            if (angleDegree == angle) return true;
        }
        return false;
    }
    public ArrayList<Coordinates> getStepCoordinates(int angleDegree){
        return this.stepsCoordinates[Quadrant.coordinatesIsInQuadrant((double)angleDegree).getValue()];
    }



    public static Coordinates nextStep(Coordinates step){
        int x = step.getX();
        int y = step.getY();
        x = x == 0 ? 0 : (x < 0 ? (x - 1) : (x + 1));
        y = y == 0 ? 0 : (y < 0 ? (y - 1) : (y + 1));
        return new Coordinates(x, y);
    }
}
