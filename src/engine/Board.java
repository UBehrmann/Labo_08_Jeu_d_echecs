package engine;

import chess.PieceType;
import chess.PlayerColor;
import engine.utils.Cell;
import engine.utils.Coordinates;
import engine.pieces.*;

import java.util.Objects;

public class Board {

    /**
     * An interface to listen to the actions on the pieces.
     * The action can be defined in a lambda expression.
     */
    public interface PieceListener {
        void action(Piece piece, Cell cell);
    }

    private final int width;
    private final int height;
    private final Cell[][] cells;
    private PieceListener onAddPiece;
    private PieceListener onRemovePiece;
    private PieceListener onPromotePiece;
    private int turn = 1;

    /**
     * Create a board with the specified dimensions
     *
     * @param width the width of the board
     * @param height the height of the board
     */
    public Board(int width, int height) {

        this.width = width;
        this.height = height;

        this.cells = new Cell[width][height];

        reset();
    }

    /**
     * Get an array of the colors of the pieces on the board
     *
     * @return PlayerColor[][] an array of the colors of the pieces on the board
     */
    private PlayerColor[][] getPositionOfPieceColors() {
        PlayerColor[][] piecesColors = new PlayerColor[this.width][this.height];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                Piece p = getPieceInBoard(new Coordinates(i, j));
                piecesColors[i][j] = p != null ? p.getColor() : null;
            }
        }
        return piecesColors;
    }

    /**
     * Get the piece at the specified position
     *
     * @param positionInBoard the position of the piece
     * @return Piece the piece at the specified position
     */
    private Piece getPieceInBoard(Coordinates positionInBoard) {
        return cells[positionInBoard.getX()][positionInBoard.getY()].getPiece();
    }

    /**
     * Do the movement of the piece at the specified position to the specified
     * position
     *
     * @param x1 the x position of the piece to move
     * @param y1 the y position of the piece to move
     * @param x2 the x position of the destination
     * @param y2 the y position of the destination
     * @return boolean true if the movement is done, false otherwise
     */
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


        if (checkPieceInWay(piecesColors, piece, positionInitial, positionFinal)) return false;

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

        // If the king is in check after the movement, the movement is
        // prohibited
        if (piece.getType() == PieceType.KING) {

            Piece pieceTmp = getPieceInBoard(positionFinal);
            movePiece(positionInitial, positionFinal);

            if (testCheck(positionFinal, piece.getColor())) {
                movePiece(positionFinal, positionInitial);
                setPiece(pieceTmp, positionFinal);
                return false;
            }else{
                return true;
            }
        }else if (testCheck(findKing(piece.getColor()), piece.getColor()))
            return false;

        // do the movement
        movePiece(positionInitial, positionFinal);

        // Update the turn
        turn++;

        return true;
    }

    /**
     * Check if there is no piece in the way of the movement
     *
     * @param piecesColors the colors of the pieces on the board
     * @param piece the piece to move
     * @param positionInitial the position of the piece to move
     * @param positionFinal the position of the destination
     * @return boolean true if the movement is done, false otherwise
     */
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
                    return true;
                break;
            }

            // If, while traversing the movement, and we have not yet reached
            // the final position, there is a piece,
            // regardless of its color, present (obstacle), then the move is
            // prohibited.
            if (piecesColors[positionPiece.getX()][positionPiece.getY()] != null)
                return true;
        }

        return false;
    }

    /**
     * Do the castling movement
     *
     * @param positionFinal the position of the king after the castling
     */
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
            if(checkPieceInWay(getPositionOfPieceColors(), piece, positionStartRook, positionEndRook)) return;

            // Move the rook
            movePiece(positionStartRook, positionEndRook);
        }
    }

    /**
     * Move a piece from the initial position to the final position
     *
     * @param positionInitial the initial position of the piece
     * @param positionFinal the final position of the piece
     */
    private void movePiece(Coordinates positionInitial,
                           Coordinates positionFinal) {

        Piece piece =
                cells[positionInitial.getX()][positionInitial.getY()].getPiece();

        removePiece(positionInitial);

        setPiece(piece, positionFinal);

        piece.clearFirstMovement();
    }

    /**
     * Check if the king is in check
     *
     * @return boolean true if the king is in check, false otherwise
     */
    public boolean isCheck() {
        PlayerColor color = getCurrentPlayer();

        return testCheck(findKing(color), color);
    }

    /**
     * Check if the king is in check
     *
     * @param positionKing the position of the king
     * @param color the color of the king
     * @return boolean true if the king is in check, false otherwise
     */
    private boolean testCheck(Coordinates positionKing, PlayerColor color) {

        if(positionKing == null || color == null) return false;

        // Check if the king is in check
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordinates positionPiece = new Coordinates(i, j);
                Piece piece = getPieceInBoard(positionPiece);
                if (piece != null && piece.getColor() != color &&
                        piece.movementIsOk(positionPiece, positionKing) &&
                        !checkPieceInWay(getPositionOfPieceColors(), piece, positionPiece, positionKing))
                    return true;
            }
        }

        return false;
    }

    /**
     * Check if the king is in checkmate
     *
     * @return boolean true if the king is in checkmate, false otherwise
     */
    public boolean isCheckMate() {
        PlayerColor color = getCurrentPlayer();

        // Check if the king is in check
        if ( !isCheck() ) return false;

        // Check if the king can move
        return checkIfKingCanNotMove(findKing(color));
    }

    /**
     * Check if the king can not move in any direction
     *
     * @param positionKing the position of the king
     * @return boolean true if the king can not move, false otherwise
     */
    private boolean checkIfKingCanNotMove(Coordinates positionKing) {

        if(positionKing == null) return false;

        for (int i = positionKing.getX() - 1; i <= positionKing.getX() + 1; i++) {
            for (int j = positionKing.getY() - 1; j <= positionKing.getY() + 1; j++) {
                if ((i == positionKing.getX() && j == positionKing.getY()) || i > width -1 || j > height -1 || i < 0 || j < 0) continue;
                if( !testCheck(new Coordinates(i, j), getCurrentPlayer()) ) return false;
            }
        }

        return true;
    }

    /**
     * Check if the king is in stalemate
     *
     * @return boolean true if the king is in stalemate, false otherwise
     */
    public boolean isStaleMate() {
        PlayerColor color = getCurrentPlayer();

        //Find the king
        Coordinates positionKing = findKing(color);

        if (positionKing == null) return false;

        // Check if the king is in check
        if (isCheck()) return false;

        //Check if the king can make a move
        return checkIfKingCanNotMove(positionKing);
    }

    /**
     * Reset the board
     */
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

    /**
     * Initialize the board, set all the pieces on the board
     */
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

    /**
     * Add a piece on the board
     *
     * @param piece the piece to add
     * @param position the position of the piece
     */
    protected void addPiece(Piece piece, Coordinates position) {
        setPiece(piece, position);
    }

    /**
     * Remove a piece from the board
     *
     * @param piece the piece to remove
     * @param position the position of the piece to remove
     */
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

    /**
     * Remove a piece from the board
     *
     * @param position the position of the piece to remove
     */
    private void removePiece(Coordinates position) {
        checkPositionOnBoard(position);
        cells[position.getX()][position.getY()].removePiece();

        if (onRemovePiece != null) {
            onRemovePiece.action(null, cells[position.getX()][position.getY()]);
        }
    }

    /**
     * Check if the position is on the board
     *
     * @param position the position to check
     */
    private void checkPositionOnBoard(Coordinates position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height)
            throw new IllegalArgumentException("Position out of board");
    }

    /**
     * Get the current player
     *
     * @return PlayerColor the color current player
     */
    public PlayerColor getCurrentPlayer() {
        return turn % 2 == 1 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    /**
     * Get the opponent player
     *
     * @return PlayerColor the color of the opponent player
     */
    public PlayerColor getOpponentPlayer() {
        return turn % 2 == 0 ? PlayerColor.WHITE : PlayerColor.BLACK;
    }

    /**
     * Set the listener to add pieces
     *
     * @param listener the listener to add pieces
     */
    public void setAddPieceListener(PieceListener listener) {
        this.onAddPiece = listener;
    }

    /**
     * Set the listener to remove pieces
     *
     * @param listener the listener to remove pieces
     */
    public void setRemovePieceListener(PieceListener listener) {
        this.onRemovePiece = listener;
    }

    /**
     * Set the listener to promote pawns
     *
     * @param listener the listener to promote pawns
     */
    public void setPromotePawnListener(PieceListener listener) {
        this.onPromotePiece = listener;
    }

    /**
     * Get the turn number
     *
     * @return int the turn number
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Find the king of the specified color
     *
     * @param color the color of the king
     * @return Coordinates the coordinates where the king is
     */
    private Coordinates findKing(PlayerColor color) {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (cells[i][j].getPiece() instanceof King &&
                        cells[i][j].getPiece().getColor() == color)
                    return new Coordinates(i, j);
        return null;
    }
}