package se.gmail.game.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

import se.gmail.game.model.entities.Player;
import se.gmail.game.view.stockMarket.StockMarketWindow;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;
    
    public MainFrame(Player p) {
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2D-Game");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);
        gamePanel = new GamePanel(p);

        this.add(gamePanel, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setStockMarketWindow(StockMarketWindow smWindow) {
        this.add(smWindow, BorderLayout.EAST);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }
}
