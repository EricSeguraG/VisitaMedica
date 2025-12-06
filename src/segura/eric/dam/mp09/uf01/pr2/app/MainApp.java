package segura.eric.dam.mp09.uf01.pr2.app;

import segura.eric.dam.mp09.uf01.pr2.seguretat.view.console.VisitaMedicaConsoleView;

/**
 * Punt d'entrada de l'aplicació.
 */
public class MainApp {

    public static void main(String[] args) {
        VisitaMedicaConsoleView view = new VisitaMedicaConsoleView();
        view.start();
    }
}