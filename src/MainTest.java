import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;
import engine.test.GameManagerTest;

public class MainTest {
    public static void main(String[] args) {

        // 1. Création du contrôleur pour gérer le jeu d’échecs
        ChessController controller = new GameManagerTest();            // Ici, vous devez instancier un ChessController
        // 2. Création de la vue désirée
        ChessView view = new GUIView(controller) ;       // mode GUI
        //ChessView view = new ConsoleView(controller) ; // ou mode Console
        // 3 . Lancement du programme
        controller.start(view) ;

    }
}
