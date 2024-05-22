package se.gmail.game.view;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;
    
    public MainFrame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D-Game");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);
        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }
}
