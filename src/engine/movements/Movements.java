package engine.movements;

import engine.utils.Coordinates;

public class Movements {
    Step[] steps;

    public Movements(Step[] steps) {
        if (steps == null)
            throw new IllegalArgumentException("step cannot be null");
        this.steps = steps;
    }

    /**
     * Get the possible step for the initial position and the final position
     *
     * @param positionInitial the initial position
     * @param positionFinal   the final position
     * @return the possible step or null if there is no possible step
     */
    public Step getPossibleStep(Coordinates positionInitial,
                                Coordinates positionFinal) {
        double angleDegree = Coordinates.getAngleDegree(positionInitial,
                positionFinal);
        for (Step step : this.steps) {
            if (step.stepAngleDegreeIsOk(angleDegree)) return step;
        }
        return null;
    }
}