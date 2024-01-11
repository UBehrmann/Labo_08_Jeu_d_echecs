package engine.utils;

public enum Quadrant {
    QUADRANT_I(0), QUADRANT_II(1), QUADRANT_III(2), QUADRANT_IV(3);
    private final int value;
    Quadrant(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }

    static final double MIN_QUADRANT_I      = 0.0;
    static final double MAX_QUADRANT_I      = 90.0;
    static final double MIN_QUADRANT_II     = MAX_QUADRANT_I;
    static final double MAX_QUADRANT_II     = 180.0;

    static final double MIN_QUADRANT_IV     = -90.0;
    static final double MAX_QUADRANT_IV     = 0.0;
    static final double MIN_QUADRANT_III    = -180.0;
    static final double MAX_QUADRANT_III    = MIN_QUADRANT_IV;

    static final int NB_QUADRANT            = 4;


    public static Quadrant coordinatesIsInQuadrant(Coordinates positionInitial, Coordinates positionFinal){
        double angleDegree = Coordinates.getAngle(positionInitial, positionFinal);
        return coordinatesIsInQuadrant(angleDegree);
    }

    public static Quadrant coordinatesIsInQuadrant(double angleDegree){
        if(angleDegree <= MAX_QUADRANT_I && angleDegree >= MIN_QUADRANT_I) return QUADRANT_I;
        if(angleDegree <= MAX_QUADRANT_II && angleDegree > MIN_QUADRANT_II) return QUADRANT_II;

        if(angleDegree < MAX_QUADRANT_III && angleDegree >= MIN_QUADRANT_III) return QUADRANT_III;
        if(angleDegree < MAX_QUADRANT_IV && angleDegree >= MIN_QUADRANT_IV) return QUADRANT_IV;

        return null;
    }
}
