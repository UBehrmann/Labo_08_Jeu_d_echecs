package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.*;

public abstract class Piece {

        private PieceType type;
        private PlayerColor color;
        private Movement[] movements;
        private boolean hasMoved;

        public Piece(PieceType type, PlayerColor color) {
            this.type = type;
            this.color = color;
        }

        public void setMovements(Movement[] movements) {
            this.movements = movements;
        }

        public PieceType getType() {

            if(this instanceof Pawn)
                return PieceType.PAWN;
            else if(this instanceof Rook)
                return PieceType.ROOK;
            else if(this instanceof Knight)
                return PieceType.KNIGHT;
            else if(this instanceof Bishop)
                return PieceType.BISHOP;
            else if(this instanceof Queen)
                return PieceType.QUEEN;
            else if(this instanceof King)
                return PieceType.KING;
            else
                return null;

        }

        public PlayerColor getColor() {
            return color;
        }

    public boolean canMove(int x1, int y1, int x2, int y2){

            int dx = x2 - x1;
            int dy = y2 - y1;

            for(Movement movement : movements){

                if(movement instanceof HorizontalMovement){
                    if(dy == 0 && dx == movement.getDistance()){
                        hasMoved = true;
                        return true;
                    }

                }

                if(movement instanceof VerticalMovement){
                    if(dx == 0 && Math.abs(dy) == movement.getDistance()){
                        if(color == PlayerColor.WHITE && dy > 0){
                            hasMoved = true;
                            return true;
                        }
                        else if(color == PlayerColor.BLACK && dy < 0){
                            hasMoved = true;
                            return true;
                        }
                    }
                }

                if(movement instanceof DiagonalMovement){
                    if(dx == dy && dx == movement.getDistance()){
                        hasMoved = true;
                        return true;
                    }
                }
            }

            return false;
    }
}

