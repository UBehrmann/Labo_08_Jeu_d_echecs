package engine;

import chess.ChessController;
import chess.ChessView;
import engine.pieces.Piece;

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

        board.setPieceListener((piece, cell) -> {
            if(view != null) {
                view.putPiece(piece.getType(), piece.getColor(), cell.getX(), cell.getY());
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
        return false;
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
