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
                        return "Test pawns";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test Rook";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test Bishop";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test Knight";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test Queen";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test King";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test check";
                    }
                },
                new ChessView.UserChoice() {
                    @Override
                    public String textValue() {
                        return "Test castling";
                    }
                }
        );

        if(test == null)
            return;

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
            case "Test check":
                ((BoardTest) board).setTestCheck();
                break;
            case "Test castling":
                ((BoardTest) board).setTestCastling();
                break;
        }
    }
}
