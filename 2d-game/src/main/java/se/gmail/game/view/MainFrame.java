package se.gmail.game.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.RepaintManager;

import se.gmail.game.model.entities.Player;
import se.gmail.game.view.inventory.InventoryPanel;
import se.gmail.game.view.stockMarket.StockMarketWindow;
import se.gmail.game.view.userInterface.UserInterface;

public class MainFrame extends JFrame {

    private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];

    private JLayeredPane layeredPane;
    private GamePanel gamePanel;
    private InventoryPanel invPanel;
    private UserInterface ui;

    private boolean isFullscreen = false;

    public MainFrame(Player p) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2D-Game");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);

        // Initialize panels
        gamePanel = new GamePanel(p);
        invPanel = new InventoryPanel(p.getInventory());
        ui = new UserInterface();

        setupMainPanel();

        this.pack();
        this.setSize(new Dimension(UtilView.screenWidth, UtilView.screenHeight));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setupMainPanel() {
        layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);

        // Add game panel to the layered pane
        gamePanel.setBounds(0, 0, UtilView.screenWidth, UtilView.screenHeight);
        layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);

        // Add inventory panel to the layered pane
        int xOffset = (UtilView.screenWidth / 2) - (invPanel.getInventoryWidth()/2);
        int yOffset = (UtilView.screenHeight / 2) - (invPanel.getInventoryHeight()/2);
        invPanel.setBounds(xOffset, yOffset, invPanel.getInventoryWidth(), invPanel.getInventoryHeight());
        invPanel.setOpaque(false);
        layeredPane.add(invPanel, JLayeredPane.PALETTE_LAYER);

        ui.setBounds(0, 0, UtilView.screenWidth, UtilView.screenHeight);
        ui.setOpaque(false);
        layeredPane.add(ui, JLayeredPane.PALETTE_LAYER);
        
        // Add additional panels below...
    }

    public void setStockMarketWindow(StockMarketWindow smWindow) {
        this.add(smWindow, BorderLayout.EAST);
    }

    public boolean isInventoryVisible() {
        return this.invPanel.isVisible();
    }

    public void showInventory(boolean b) {
        this.invPanel.setVisible(b);
        this.revalidate();
        this.repaint();
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean b) {
        this.isFullscreen = b;
        if(isFullscreen) {
            device.setFullScreenWindow(this);
        } else {
            device.setFullScreenWindow(null);
        }
    }
}
