package engine.utils;

public class Coordinates {
    int x;
    int y;

    /**
     * Constructor to initialize a Coordinates object with given x and y coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor to create a new Coordinates object with the same coordinates as the provided Coordinates object.
     *
     * @param coordinates the Coordinates object to copy from
     */
    public Coordinates(Coordinates coordinates) {
        copy(coordinates);
    }

    /**
     * Copy the coordinates from another Coordinates object to this object.
     *
     * @param coordinates the Coordinates object to copy from
     */
    public void copy(Coordinates coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
    }

    /**
     * Set new coordinates for this object.
     *
     * @param x the new x-coordinate
     * @param y the new y-coordinate
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Add the coordinates of another Coordinates object to this object.
     *
     * @param coordinates the Coordinates object to add
     */
    public void add(Coordinates coordinates) {
        Coordinates c = addition(this, coordinates);
        setCoordinates(c.getX(), c.getY());
    }

    /**
     * Set a new value for the x-coordinate.
     *
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the x-coordinate of this object.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Set a new value for the y-coordinate.
     *
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the y-coordinate of this object.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Calculate the change in Y-coordinate between two Coordinates objects.
     *
     * @param positionInitial the initial position
     * @param positionFinal   the final position
     * @return the change in Y-coordinate
     */
    public static int deltaY(Coordinates positionInitial, Coordinates positionFinal) {
        return positionFinal.y - positionInitial.y;
    }

    /**
     * Calculate the change in X-coordinate between two Coordinates objects.
     *
     * @param positionInitial the initial position
     * @param positionFinal   the final position
     * @return the change in X-coordinate
     */
    public static int deltaX(Coordinates positionInitial, Coordinates positionFinal) {
        return positionFinal.x - positionInitial.x;
    }

    /**
     * Calculate the angle in radians between two Coordinates objects.
     *
     * @param positionInitial the initial position
     * @param positionFinal   the final position
     * @return the angle in radians
     */
    public static double getAngle(Coordinates positionInitial, Coordinates positionFinal) {
        int deltaX = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY = Coordinates.deltaY(positionInitial, positionFinal);
        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Calculate the angle in degrees between two Coordinates objects.
     *
     * @param positionInitial the initial position
     * @param positionFinal   the final position
     * @return the angle in degrees
     */
    public static double getAngleDegree(Coordinates positionInitial, Coordinates positionFinal) {
        return Math.toDegrees(getAngle(positionInitial, positionFinal));
    }

    /**
     * Perform vector addition of two Coordinates objects and return a new Coordinates object.
     *
     * @param c1 the first Coordinates object
     * @param c2 the second Coordinates object
     * @return a new Coordinates object representing the result of the addition
     */
    public static Coordinates addition(Coordinates c1, Coordinates c2) {
        return new Coordinates(c1.getX() + c2.getX(), c1.getY() + c2.getY());
    }

    /**
     * Check if two Coordinates objects are equal in terms of their x and y coordinates.
     *
     * @param c1 the first Coordinates object
     * @param c2 the second Coordinates object
     * @return true if the coordinates are equal, false otherwise
     */
    public static boolean equal(Coordinates c1, Coordinates c2) {
        return (c1.getX() == c2.getX()) && (c1.getY() == c2.getY());
    }
}
