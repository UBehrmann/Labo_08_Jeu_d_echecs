package engine.movements;

import java.util.HashMap;
import java.util.Map;

enum Direction2 {
    HORIZONTAL, VERTICAL, DIAGONAL, SPECIAL;

    public static Map<Integer, Direction2> createAngleToDirectionMap() {
        Map<Integer, Direction2> angleToDirectionMap = new HashMap<>();

        int[] angleForHorizontalDirection   = {0, 180};
        {for(int angle : angleForHorizontalDirection) angleToDirectionMap.put(angle, HORIZONTAL);}

        int[] angleForVerticalDirection     = {90, 270};
        {for(int angle : angleForVerticalDirection) angleToDirectionMap.put(angle, VERTICAL);}

        int[] angleForDiagonalDirection     = {45, 135, 225, 315};
        {for(int angle : angleForDiagonalDirection) angleToDirectionMap.put(angle, DIAGONAL);}

        int[] angleForSpecialDirection      = {30, 60, 120, 150, 210, 240, 300, 330};
        {for(int angle : angleForSpecialDirection) angleToDirectionMap.put(angle, SPECIAL);}

        return angleToDirectionMap;
    }
}
