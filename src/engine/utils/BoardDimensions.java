package engine.utils;

import engine.Board;

public enum BoardDimensions {
    WIDTH(8), HEIGHT(8), DIAGONAL(8);
    private final int value;
    BoardDimensions(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }

}
