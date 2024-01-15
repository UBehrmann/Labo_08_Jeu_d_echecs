package engine.test;

import chess.ChessView;
import engine.GameManager;

public class GameManagerTest extends GameManager {

    public GameManagerTest() {
        this.board = new BoardTest(8, 8);
    }

    @Override
    public void start(ChessView view) {
        // TODO Auto-generated method stub
        super.start(view);

        // Initialize the board
        newGame();
    }

    @Override
    public void newGame() {

        // Reset the board
        board.reset();

        // Ask the player which test he wants to do
        ChessView.UserChoice test = view.askUser("Tests", "Which test do you " +
                        "want to do?",

                () -> "Test pawns",
                () -> "Test Rook",
                () -> "Test Bishop",
                () -> "Test Knight",
                () -> "Test Queen",
                () -> "Test King",
                () -> "Test pawn promotion",
                () -> "Test check",
                () -> "Test castling"
        );

        if (test == null) return;

        // Set the board to the selected test
        switch (test.textValue()) {
            case "Test pawns":
                ((BoardTest) board).setTestPawn();
                break;
            case "Test Rook":
                ((BoardTest) board).setTestRook();
                break;
            case "Test Bishop":
                ((BoardTest) board).setTestBishop();
                break;
            case "Test Knight":
                ((BoardTest) board).setTestKnight();
                break;
            case "Test Queen":
                ((BoardTest) board).setTestQueen();
                break;
            case "Test King":
                ((BoardTest) board).setTestKing();
                break;
            case "Test pawn promotion":
                ((BoardTest) board).setTestPawnPromotion();
                break;
            case "Test check":
                ((BoardTest) board).setTestCheck();
                break;
            case "Test castling":
                ((BoardTest) board).setTestCastling();
                break;
        }
    }
}
