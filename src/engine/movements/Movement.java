package engine.movements;

public abstract class Movement {

    private int distance;

    public Movement(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}

