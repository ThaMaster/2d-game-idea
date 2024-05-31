package se.gmail.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.model.entities.Player;
import se.gmail.game.model.tiles.TileManager;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS
    final int originalTileSize = 80;
    final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 10;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    private Player player;

    private final int pScreenX = screenWidth/2 - (tileSize/2);
    private final int pScreenY = screenHeight/2 - (tileSize/2);

    private TileManager tileManager = new TileManager(this);
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public GamePanel(Player p) {
        this.player = p;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        for(int i = 0; i < 3; i++) {
            Enemy e = new Enemy();
            e.setWorldPosition((int)(screenWidth/2), (int)(screenHeight/2));
            this.enemies.add(e);
        }

        this.player.setScreenPosition(pScreenX, pScreenY);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        synchronized(g2) {
            tileManager.draw(g2);
            player.draw(g2);

            for(Enemy e : enemies) {
                int screenX = e.getWorldXPosition() - player.getWorldXPosition() + player.getScreenXPosition();
                int screenY = e.getWorldYPosition() - player.getWorldYPosition() + player.getScreenYPosition();
                e.setScreenPosition(screenX, screenY);
                e.update();
                e.getAnimator().update();
                // Prevent enemies going outside the screen, for now...
                if(e.getWorldXPosition() > screenWidth) {
                    e.setWorldXPosition(screenWidth-1);
                } 
                if(e.getWorldXPosition() < 0) {
                    e.setWorldXPosition(1);
                }
                if(e.getWorldYPosition() > screenHeight) {
                    e.setWorldYPosition(screenHeight-1);
                } 
                if(e.getWorldYPosition() < 0) {
                    e.setWorldYPosition(1);
                }

                if(tileManager.insideScreen(e.getWorldXPosition(), e.getWorldYPosition())) {
                    e.draw(g2);
                }
            }
        }
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public int getMaxScreenColumns() {
        return this.maxScreenCol;
    }

    public int getMaxScreenRows() {
        return this.maxScreenRow;
    }

    public int getMaxScreenWidth() {
        return this.screenWidth;
    }

    public int getMaxScreenHeight() {
        return this.screenHeight;
    }

    public int getMaxWorldColumns() {
        return this.maxWorldCol;
    }

    public int getMaxWorldRows() {
        return this.maxWorldRow;
    }

    public Player getPlayer() {
        return this.player;
    }
}
