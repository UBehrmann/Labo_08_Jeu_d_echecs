package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.*;
import engine.utils.BoardDimensions;
import engine.utils.Coordinates;

public class King extends Piece {

    public King(PlayerColor color) {
        super(PieceType.KING, color, new Movements(new Step[]{
                //Horizontal
                new Step(new Coordinates(1, 0), 2, color == PlayerColor.BLACK
                        , color == PlayerColor.BLACK),
                new Step(new Coordinates(-1, 0), 2,
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),

                //Vertical
                new Step(new Coordinates(0, 1), 1, color == PlayerColor.BLACK
                        , color == PlayerColor.BLACK),
                new Step(new Coordinates(0, -1), 1,
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),

                //Diagonal
                new Step(new Coordinates(1, 1), 1, color == PlayerColor.BLACK
                        , color == PlayerColor.BLACK),
                new Step(new Coordinates(1, -1), 1,
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1, 1), 1,
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
                new Step(new Coordinates(-1, -1), 1,
                        color == PlayerColor.BLACK, color == PlayerColor.BLACK),
        }));
    }


    public void clearFirstMovement() {
        super.setMovements(new Movements(new Step[]{
                //Horizontal
                new Step(new Coordinates(1, 0), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(-1, 0), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),

                //Vertical
                new Step(new Coordinates(0, 1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(0, -1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),

                //Diagonal
                new Step(new Coordinates(1, 1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(1, -1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(-1, 1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
                new Step(new Coordinates(-1, -1), 1,
                        super.getColor() == PlayerColor.BLACK,
                        super.getColor() == PlayerColor.BLACK),
        }));
        this.firstMovement = false;
    }
}