package se.gmail.game;

import javax.swing.SwingUtilities;

import se.gmail.game.controller.GameController;

public class App {
    public static void main(String[] args) throws InterruptedException {
        
        Runnable startApp = () -> {
            try {
                new GameController();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        };

        SwingUtilities.invokeLater(startApp);
        while(true) {
            Thread.sleep(1000);
        }
    }
}
