package engine;

import chess.ChessController;
import chess.ChessView;

public class GameManager implements ChessController {

    private ChessView view;

    private Board board;

    public static final int BOARD_SIZE = 8;

    public GameManager() {
        this.board = new Board(BOARD_SIZE, BOARD_SIZE);
    }

    private void updateMessage() {
        if(view == null || board == null) return;

        StringBuilder message = new StringBuilder();

        message.append("Turn: ");

        // Update the message
        view.displayMessage(message.toString());
    }

    private  void initListeners() {

        // Add the listener to add pieces
        board.setAddPieceListener((piece, cell) -> {
            if(view != null) {
                view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
            }
        });

        // Add the listener to remove pieces
        board.setRemovePieceListener((piece, cell) -> {
            if(view != null) {
                view.removePiece(cell.getX(), cell.getY());
            }
        });

    }

    @Override
    public void start(ChessView view) {
        this.view = view;

        // Start the view
        view.startView();

        // Initialize the piece listeners
        initListeners();

        // Put the pieces on the board
        board.initialize();

        // Update the message
        updateMessage();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {

        // Check if the movement is valid
        boolean valid = board.doMovement(fromX, fromY, toX, toY);

        // Update the message
        updateMessage();

        return valid;
    }

    @Override
    public void newGame() {

        // Reset the board
        board.reset();

        // Put the pieces on the board
        board.initialize();board.initialize();

        // Update the message
        updateMessage();
    }


}
