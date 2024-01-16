package engine.movements;

import engine.utils.Coordinates;

public class Step {
    private Coordinates coordinates;
    private Coordinates[] movement;
    private double stepAngleDegree;

    public Step(Coordinates step, int maxStep, boolean invertOrdinateAxis,
                boolean invertAbscissaAxis) {
        if (step == null)
            throw new IllegalArgumentException("step cannot be null");
        if (maxStep < 0)
            throw new IllegalArgumentException("maxStep cannot be negative");
        int x = invertAbscissaAxis ? (step.getX() * (-1)) : step.getX();
        int y = invertOrdinateAxis ? (step.getY() * (-1)) : step.getY();
        this.coordinates = new Coordinates(x, y);

        Coordinates originCoordinates = new Coordinates(0, 0);
        this.stepAngleDegree = Coordinates.getAngleDegree(originCoordinates,
                this.coordinates);

        this.movement = new Coordinates[maxStep];
        for (int i = 0; i < this.movement.length; ++i)
            this.movement[i] = nextStep(i);
    }

    /**
     * Get the next step for the number of step given
     *
     * @param nbStep the number of step
     * @return the next step
     */
    private Coordinates nextStep(int nbStep) {
        int x = this.coordinates.getX();
        int y = this.coordinates.getY();
        x = x == 0 ? 0 : (x < 0 ? (x - nbStep) : (x + nbStep));
        y = y == 0 ? 0 : (y < 0 ? (y - nbStep) : (y + nbStep));
        return new Coordinates(x, y);
    }

    /**
     * Check if the angle of the step is the same as the angle of the movement
     *
     * @param stepAngleDegree the angle of the step
     * @return true if the angle of the step is the same as the angle of the
     *        movement
     */
    public boolean stepAngleDegreeIsOk(double stepAngleDegree) {
        return this.stepAngleDegree == stepAngleDegree;
    }

    /**
     * Get the coordinates of the movement
     *
     * @return the coordinates
     */
    public Coordinates[] getMovement() {
        return this.movement;
    }
}