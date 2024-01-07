package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.movements.Movement;

public abstract class Piece {

        private PieceType type;
        private PlayerColor color;

        private Movement[] movements;

        public Piece(PieceType type, PlayerColor color) {
            this.type = type;
            this.color = color;
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

}

