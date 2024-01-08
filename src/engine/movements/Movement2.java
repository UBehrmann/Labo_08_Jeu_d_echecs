package engine.movements;
import java.util.Map;

public class Movement2 {
    Direction2 direction;
    private static final Map<Integer, Direction2> ANGLE_TO_DIRECTION_MAP = Direction2.createAngleToDirectionMap();
    int maxDistance;
    int minDistance;

    public Movement2(Direction2 direction, int maxDistance, int minDistance){
        this.direction      = direction;
        this.maxDistance    = maxDistance;
        this.minDistance    = minDistance;
    }
    private boolean directionIsOk(int angleDegree){
        if(ANGLE_TO_DIRECTION_MAP.containsKey(angleDegree)){
            Direction2 angleDirection = ANGLE_TO_DIRECTION_MAP.get(angleDegree);
            return angleDirection == this.direction;
        }
        return false;
    }
    private boolean distanceIsOk(int xInitial, int xFinal, int yInitial, int yFinal){
        int deltaX      = xFinal - xInitial;
        int deltaY      = yFinal - yInitial;
        int hypotenuse  = (int)Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return switch (direction) {
            case HORIZONTAL -> deltaX >= minDistance && deltaX <= maxDistance;
            case VERTICAL -> deltaY >= minDistance && deltaY <= maxDistance;
            case SPECIAL, DIAGONAL -> hypotenuse >= minDistance && hypotenuse <= maxDistance;
            default -> false;
        };
    }
    public boolean movementIsOk(int xInitial, int xFinal, int yInitial, int yFinal){
        int deltaX      = xFinal - xInitial;
        int deltaY      = yFinal - yInitial;
        int angleDegree = (int)Math.toDegrees(Math.atan2(deltaY, deltaX));

        return directionIsOk(angleDegree) && distanceIsOk( xInitial, xFinal, yInitial, yFinal);
    }
}
