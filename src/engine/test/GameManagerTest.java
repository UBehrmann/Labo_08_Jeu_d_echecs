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
        ChessView.UserChoice test = view.askUser("Tests", "Quelles tests voulez vous faire?",
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test 1";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test pawns";
                    }
                }
        );

        if(test == null)
            return;

        switch (test.textValue()) {
            case "Test 1":
                ((BoardTest) board).setTest1();
                break;
            case "Test pawns":
                ((BoardTest) board).setTestPawn();
                break;
        }
    }
}
