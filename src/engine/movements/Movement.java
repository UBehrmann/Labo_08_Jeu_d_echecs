package engine.movements;
import java.util.Map;

public class Movement {
    Move direction;
    private static final Map<Integer, Move> ANGLE_TO_DIRECTION_MAP = Move.createAngleToDirectionMap();
    int maxDistance;
    int minDistance;

    public Movement(Move direction, int maxDistance, int minDistance){
        this.direction      = direction;
        this.maxDistance    = maxDistance;
        this.minDistance    = minDistance;
    }
    public int getDistanceRange(){
        return maxDistance - minDistance;
    }
    public int getMaxDistance(){
        return this.maxDistance;
    }
    public int getMinDistance(){
        return this.minDistance;
    }
    private boolean directionIsOk(Coordinates positionInitial, Coordinates positionFinal){
        int angleDegree = (int)Coordinates.getAngle(positionInitial, positionFinal);

        if(ANGLE_TO_DIRECTION_MAP.containsKey(angleDegree)){
            Move angleDirection = ANGLE_TO_DIRECTION_MAP.get(angleDegree);
            return angleDirection == this.direction;
        }
        return false;
    }
    private boolean distanceIsOk(Coordinates positionInitial, Coordinates positionFinal){
        int deltaX      = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY      = Coordinates.deltaY(positionInitial, positionFinal);
        int norm        = (int)Coordinates.norm(positionInitial, positionFinal);

        return switch (direction) {
            case HORIZONTAL -> deltaX >= minDistance && deltaX <= maxDistance;
            case VERTICAL -> deltaY >= minDistance && deltaY <= maxDistance;
            case SPECIAL, DIAGONAL -> norm >= minDistance && norm <= maxDistance;
            default -> false;
        };
    }
    public boolean movementIsOk(Coordinates positionInitial, Coordinates positionFinal){
        return directionIsOk(positionInitial, positionFinal) && distanceIsOk(positionInitial, positionFinal);
    }
}
