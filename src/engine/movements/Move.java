package engine.movements;

import java.util.HashMap;
import java.util.Map;

public enum Move {
    HORIZONTAL, VERTICAL, DIAGONAL, SPECIAL;

    public static Map<Integer, Move> createAngleToDirectionMap() {
        Map<Integer, Move> angleToDirectionMap = new HashMap<>();

        int[] angleForHorizontalDirection   = {0, 180};
        {for(int angle : angleForHorizontalDirection) angleToDirectionMap.put(angle, HORIZONTAL);}

        int[] angleForVerticalDirection     = {90, 270};
        {for(int angle : angleForVerticalDirection) angleToDirectionMap.put(angle, VERTICAL);}

        int[] angleForDiagonalDirection     = {45, 135, 225, 315};
        {for(int angle : angleForDiagonalDirection) angleToDirectionMap.put(angle, DIAGONAL);}

        //int[] angleForSpecialDirection      = {30, 60, 120, 150, 210, 240, 300, 330};
        int[] angleForSpecialDirection      = {26, 63, 116, 153, 206, 243, 243, 296};
        {for(int angle : angleForSpecialDirection) angleToDirectionMap.put(angle, SPECIAL);}

        return angleToDirectionMap;
    }
}
