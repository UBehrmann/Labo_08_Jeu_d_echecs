package engine;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;
import engine.utils.Coordinates;
import engine.pieces.*;

import java.util.Objects;

public class Board {

    private final int width;
    private final int height;
    private final Cell[][] cells;
    private PieceListener onAddPiece;
    private PieceListener onRemovePiece;
    private PieceListener onPromotePiece;
    private int turn = 1;

    public Board(int width, int height) {

        this.width = width;
        this.height = height;

        this.cells = new Cell[width][height];

        reset();
    }

    public PlayerColor[][] getPositionOfPieceColors() {
        PlayerColor[][] piecesColors = new PlayerColor[this.width][this.height];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                Piece p = getPieceInBoard(new Coordinates(i, j));
                piecesColors[i][j] = p != null ? p.getColor() : null;
            }
        }
        return piecesColors;
    }

    public Piece getPieceInBoard(Coordinates positionInBoard) {
        return cells[positionInBoard.getX()][positionInBoard.getY()].getPiece();
    }

    public boolean doMovement(int x1, int y1, int x2, int y2) {
        // Check if the piece is on the board
        Piece piece = cells[x1][y1].getPiece();

        // Check if the piece isn't null and if it is the same color as the
        // current player
        if (piece == null || piece.getColor() != getCurrentPlayer())
            return false;

        // Define the initial and final position
        Coordinates positionInitial = new Coordinates(x1, y1);
        Coordinates positionFinal = new Coordinates(x2, y2);

        // Check if the piece can move to the new position
        if (!piece.movementIsOk(positionInitial, positionFinal)) return false;

        // get the position of the pieces' colors on the board
        PlayerColor[][] piecesColors = getPositionOfPieceColors();


        if ( !checkPieceInWay(piecesColors, piece, positionInitial, positionFinal)) return false;

        // King special movements
        if (piece.getType() == PieceType.KING) {
            // If the piece is a king, and he would be in check after the
            // movement,
            // the movement is prohibited
            if (testCheck(positionFinal, piece.getColor())) return false;
            // If the king is castling, check if the rook is in the correct
            // position
            // and if it is the first movement of the king and the rook
            if (piece.isFirstMovement()) castling(positionFinal);
        }

        // If the piece is a pawn, and it takes a piece in the "en passant"
        // movement, remove the piece
        if (piece.getType() == PieceType.PAWN && Math.abs(x2 - x1) == 1 &&
                Math.abs(y2 - y1) == 1) {
            if (getPieceInBoard(new Coordinates(x2, y1)) != null) {
                removePiece(new Coordinates(x2, y1));
            } else if (getPieceInBoard(new Coordinates(x2, y2)) == null) {
                return false;
            }
        }

        // do the movement
        movePiece(new Coordinates(x1, y1), new Coordinates(x2, y2));

        // Update the turn
        turn++;

        return true;
    }

    private boolean checkPieceInWay(PlayerColor[][] piecesColors, Piece piece, Coordinates positionInitial, Coordinates positionFinal) {
        // Get the possible movement of the part from the initial to the final
        // position
        // The possible movement is a sequence of coordinates following a step
        // from the initial position to the final position.
        Coordinates[] movementPiece = piece.getPossibleMovement(positionInitial,
                positionFinal);


        // position initial -> ... -> position final -> ... -> Max step
        for (Coordinates positionPiece : movementPiece) {
            //If there is a piece of the same color as the one making the last
            // move to the final position, the move is considered forbidden.
            // Otherwise, (if there is a piece of a different color or no piece
            // at all), control stops, and the move is allowed.
            if (Coordinates.equal(positionPiece, positionFinal)) {
                if (piecesColors[positionPiece.getX()][positionPiece.getY()] ==
                        piece.getColor())
                    return false;
                break;
            }

            // If, while traversing the movement, and we have not yet reached
            // the final position, there is a piece,
            // regardless of its color, present (obstacle), then the move is
            // prohibited.
            if (piecesColors[positionPiece.getX()][positionPiece.getY()] != null)
                return false;
        }

        return true;
    }

    private void castling(Coordinates positionFinal) {

        // Check if the rook is in the correct position
        if (positionFinal.getX() == 2 || positionFinal.getX() == 6) {

            Coordinates positionStartRook = new Coordinates(positionFinal.getX() == 2 ? 0 : 7,
                    positionFinal.getY());
            Coordinates positionEndRook = new Coordinates(positionFinal.getX() == 2 ? 3 : 5,
                    positionFinal.getY());

            // Check if it is the first movement of the king and the rook
            Piece piece = getPieceInBoard(positionStartRook);

            if(!(piece instanceof Rook) || !piece.isFirstMovement()) return;

            // Check if there is a piece between the king and the rook
            if( !checkPieceInWay(getPositionOfPieceColors(), piece, positionStartRook, positionEndRook)) return;

            // Move the rook
            movePiece(positionStartRook, positionEndRook);
        }
    }

    private void movePiece(Coordinates positionInitial,
                           Coordinates positionFinal) {

        Piece piece =
                cells[positionInitial.getX()][positionInitial.getY()].getPiece();

        removePiece(positionInitial);

        setPiece(piece, positionFinal);

        piece.clearFirstMovement();
    }

    public boolean isCheck() {
        PlayerColor color = getCurrentPlayer();

        // Find the king
        Cell kingCell = findKing(color);
        if (kingCell == null) return false;
        Coordinates positionKing = new Coordinates(kingCell.getX(),
                kingCell.getY());

        return testCheck(positionKing, color);
    }

    public boolean testCheck(Coordinates positionKing, PlayerColor color) {
        // Check if the king is in check
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinates positionPiece = new Coordinates(i, j);
                Piece piece = getPieceInBoard(positionPiece);
                if (piece != null && piece.getColor() != color &&
                        piece.movementIsOk(positionPiece, positionKing))
                    return true;
            }
        }

        return false;
    }

    public boolean isCheckMate() {
        PlayerColor color = getCurrentPlayer();

        // Find the king
        Cell kingCell = findKing(color);
        if (kingCell == null) return false;
        Coordinates positionKing = new Coordinates(kingCell.getX(),
                kingCell.getY());

        // Check if the king is in check
        if ( !isCheck() ) return false;

        // Check if the king can move
        return checkIfKingCanNotMove(positionKing);
    }

    private boolean checkIfKingCanNotMove(Coordinates positionKing) {
        for (int i = positionKing.getX() - 1; i <= positionKing.getX() + 1; i++) {
            for (int j = positionKing.getY() - 1; j <= positionKing.getY() + 1; j++) {
                if ((i == positionKing.getX() && j == positionKing.getY()) || i > width -1 || j > height -1 || i < 0 || j < 0) continue;
                if( !testCheck(new Coordinates(i, j), getCurrentPlayer()) ) return false;
            }
        }

        return true;
    }

    public boolean isStaleMate() {
        PlayerColor color = getCurrentPlayer();

        //Find the king
        Cell kingCell = findKing(color);

        if (kingCell == null) return false;

        Coordinates positionKing = new Coordinates(kingCell.getX(),
                kingCell.getY());

        // Check if the king is in check
        if (isCheck()) return false;

        //Check if the king can make a move
        return checkIfKingCanNotMove(positionKing);
    }

    public boolean isDraw() {
        return true;
    }

    public void reset() {

        // Remove all pieces from the board
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(null, i, j);
                removePiece(new Coordinates(i, j));
            }

        // Reset the turn counter
        turn = 1;
    }

    public void initialize() {

        // Put the pieces on the board
        // White pieces
        addPiece(new Rook(PlayerColor.WHITE), new Coordinates(0, 0));
        addPiece(new Knight(PlayerColor.WHITE), new Coordinates(1, 0));
        addPiece(new Bishop(PlayerColor.WHITE), new Coordinates(2, 0));
        addPiece(new Queen(PlayerColor.WHITE), new Coordinates(3, 0));
        addPiece(new King(PlayerColor.WHITE), new Coordinates(4, 0));
        addPiece(new Bishop(PlayerColor.WHITE), new Coordinates(5, 0));
        addPiece(new Knight(PlayerColor.WHITE), new Coordinates(6, 0));
        addPiece(new Rook(PlayerColor.WHITE), new Coordinates(7, 0));
        for (int i = 0; i < 8; i++) {
            addPiece(new Pawn(PlayerColor.WHITE), new Coordinates(i, 1));
        }

        // Black pieces
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(0, 7));
        addPiece(new Knight(PlayerColor.BLACK), new Coordinates(1, 7));
        addPiece(new Bishop(PlayerColor.BLACK), new Coordinates(2, 7));
        addPiece(new Queen(PlayerColor.BLACK), new Coordinates(3, 7));
        addPiece(new King(PlayerColor.BLACK), new Coordinates(4, 7));
        addPiece(new Bishop(PlayerColor.BLACK), new Coordinates(5, 7));
        addPiece(new Knight(PlayerColor.BLACK), new Coordinates(6, 7));
        addPiece(new Rook(PlayerColor.BLACK), new Coordinates(7, 7));
        for (int i = 0; i < 8; i++) {
            addPiece(new Pawn(PlayerColor.BLACK), new Coordinates(i, 6));
        }
    }

    public void addPiece(Piece piece, Coordinates position) {
        setPiece(piece, position);
    }

    public void setPiece(Piece piece, Coordinates position) {

        Objects.requireNonNull(piece, "Piece cannot be null");

        checkPositionOnBoard(position);

        cells[position.getX()][position.getY()].setPiece(piece);

        if (onAddPiece != null) {
            onAddPiece.action(piece, cells[position.getX()][position.getY()]);
        }

        if (piece instanceof Pawn && (position.getY() == 0 ||
                position.getY() == 7)) {
            if (onPromotePiece != null) {
                onPromotePiece.action(piece,
                        cells[position.getX()][position.getY()]);
            }
        }
    }

    public void removePiece(Coordinates position) {
        checkPositionOnBoard(position);
        cells[position.getX()][position.getY()].removePiece();

        if (onRemovePiece != null) {
            onRemovePiece.action(null, cells[position.getX()][position.getY()]);
        }
    }

    private void checkPositionOnBoard(Coordinates position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height)
            throw new IllegalArgumentException("Position out of board");
    }

    public PlayerColor getCurrentPlayer() {
        return turn % 2 == 1 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public PlayerColor getOpponentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    public void setAddPieceListener(PieceListener listener) {
        this.onAddPiece = listener;
    }

    public void setRemovePieceListener(PieceListener listener) {
        this.onRemovePiece = listener;
    }

    public void setPromotePawnListener(PieceListener listener) {
        this.onPromotePiece = listener;
    }

    public int getTurn() {
        return turn;
    }

    private Cell findKing(PlayerColor color) {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (cells[i][j].getPiece() instanceof King &&
                        cells[i][j].getPiece().getColor() == color)
                    return cells[i][j];
        return null;
    }

    public interface PieceListener {
        void action(Piece piece, Cell cell);
    }

}
