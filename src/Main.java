import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;
import engine.GameManager;

public class Main {
    public static void main(String[] args) {

        // 1. Création du contrôleur pour gérer le jeu d’échecs
        ChessController controller = new GameManager();            // Ici, vous devez instancier un ChessController
        // 2. Création de la vue désirée
        ChessView view = new GUIView(controller) ;       // mode GUI
        //ChessView view = new ConsoleView(controller) ; // ou mode Console
        // 3 . Lancement du programme
        controller.start(view) ;

    }
}