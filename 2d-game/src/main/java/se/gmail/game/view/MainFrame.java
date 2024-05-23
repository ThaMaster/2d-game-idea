package se.gmail.game.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;
    
    public MainFrame(JPanel contentPanel) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2D-Game");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);
        gamePanel = new GamePanel();

        this.add(gamePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }
}
