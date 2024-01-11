package engine.movements;

import engine.utils.Coordinates;
import engine.utils.Steps;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    HORIZONTAL, VERTICAL, DIAGONAL, SPECIAL, NONE;

    static final Steps stepForHorizontalDirection         = new Steps( new Coordinates[]{
                                                            new Coordinates(1,0), new Coordinates(-1, 0)});
    static final Steps stepForVerticalDirection           = new Steps( new Coordinates[]{
                                                            new Coordinates(0,1), new Coordinates(0, -1)});
    static final Steps stepForDiagonalDirection           = new Steps(new Coordinates[]{
                                                            new Coordinates(1,1), new Coordinates(-1,1),
                                                            new Coordinates(1,-1), new Coordinates(-1,-1)});
    static final Steps stepForSpecialDirection            = new Steps(new Coordinates[]{
                                                            new Coordinates(1,2), new Coordinates(-1,2),
                                                            new Coordinates(1,-2), new Coordinates(-1,-2),
                                                            new Coordinates(2,1), new Coordinates(-2,1),
                                                            new Coordinates(2,-1), new Coordinates(-2,-1)});

    public static Map<Direction, Steps> createDirectionToSteps() {
        Map<Direction, Steps> directionToSteps  = new HashMap<>();

        directionToSteps.put(HORIZONTAL, stepForHorizontalDirection);
        directionToSteps.put(VERTICAL, stepForVerticalDirection);
        directionToSteps.put(DIAGONAL, stepForDiagonalDirection);
        directionToSteps.put(SPECIAL, stepForSpecialDirection);

        return directionToSteps;
    }
}
