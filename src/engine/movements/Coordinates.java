package engine.movements;

public class Coordinates {
    int x;
    int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void copy(Coordinates coordinates){
        this.x = coordinates.x;
        this.y = coordinates.y;
    }
    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }

    public static int deltaY(Coordinates positionInitial, Coordinates positionFinal){
        return positionFinal.y - positionInitial.y;
    }
    public static int deltaX(Coordinates positionInitial, Coordinates positionFinal){
        return positionFinal.x - positionInitial.x;
    }
    public static double norm(Coordinates positionInitial, Coordinates positionFinal){
        int deltaX      = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY      = Coordinates.deltaY(positionInitial, positionFinal);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    public static double getAngle(Coordinates positionInitial, Coordinates positionFinal){
        int deltaX      = Coordinates.deltaX(positionInitial, positionFinal);
        int deltaY      = Coordinates.deltaY(positionInitial, positionFinal);
        return Math.toDegrees(Math.atan2(deltaY, deltaX));
    }
}
