package engine.utils;

public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates coordinates) {
        copy(coordinates);
    }

    public void copy(Coordinates coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Coordinates coordinates) {
        Coordinates c = addition(this, coordinates);
        setCoordinates(c.getX(), c.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public static int deltaY(Coordinates positionInitial,
                             Coordinates positionFinal) {
        return positionFinal.y - positionInitial.y;
    }

    public static int deltaX(Coordinates positionInitial,
                             Coordinates positionFinal) {
        return positionFinal.x - positionInitial.x;
    }

    public static double norm(Coordinates positionInitial,
                              Coordinates positionFinal) {
        int deltaX = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY = Coordinates.deltaY(positionInitial, positionFinal);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static double getAngle(Coordinates positionInitial,
                                  Coordinates positionFinal) {
        int deltaX = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY = Coordinates.deltaY(positionInitial, positionFinal);
        return Math.atan2(deltaY, deltaX);
    }

    public static double getAngleDegree(Coordinates positionInitial,
                                        Coordinates positionFinal) {
        return Math.toDegrees(getAngle(positionInitial, positionFinal));
    }

    public static Coordinates addition(Coordinates c1, Coordinates c2) {
        return new Coordinates(c1.getX() + c2.getX(), c1.getY() + c2.getY());
    }

    public static boolean equal(Coordinates c1, Coordinates c2) {
        return (c1.getX() == c2.getX()) && (c1.getY() == c2.getY());
    }
}
