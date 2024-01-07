package engine.movements;

public class VerticalMovement extends Movement {

    boolean isWhite;

    public VerticalMovement(int distance, boolean isWhite) {
        super(distance);
        this.isWhite = isWhite;
    }
}
