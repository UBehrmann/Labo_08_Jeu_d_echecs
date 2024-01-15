package engine.movements;

import engine.utils.Coordinates;

public class Step {
    Coordinates coordinates;
    Coordinates[] movement;
    double stepAngleDegree;

    public Step(Coordinates step, int maxStep, boolean invertOrdinateAxis, boolean invertAbscissaAxis){
        if (step == null)throw new IllegalArgumentException("step cannot be null");
        if (maxStep < 0) throw new IllegalArgumentException("maxStep cannot be negative");
        int x = invertAbscissaAxis ? (step.getX() * (-1)) : step.getX();
        int y = invertOrdinateAxis ? (step.getY() * (-1)) : step.getY();
        this.coordinates = new Coordinates(x, y);

        Coordinates originCoordinates = new Coordinates(0,0);
        this.stepAngleDegree  = Coordinates.getAngleDegree(originCoordinates, this.coordinates);

        this.movement = new Coordinates[maxStep];
        for(int i = 0; i < this.movement.length; ++i) this.movement[i] = nextStep(i);
    }

    private Coordinates nextStep(int nbStep){
        int x = this.coordinates.getX();
        int y = this.coordinates.getY();
        x = x == 0 ? 0 : (x < 0 ? (x - nbStep) : (x + nbStep));
        y = y == 0 ? 0 : (y < 0 ? (y - nbStep) : (y + nbStep));
        return new Coordinates(x, y);
    }

    public boolean stepAngleDegreeIsOk(double stepAngleDegree){
        return this.stepAngleDegree == stepAngleDegree;
    }

    public Coordinates[] getMovement() {
        return this.movement;
    }
}