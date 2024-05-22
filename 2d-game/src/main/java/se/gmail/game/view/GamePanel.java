package se.gmail.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import se.gmail.game.model.tiles.TileManager;
import se.gmail.game.util.enums.Direction;

public class GamePanel extends JPanel {

    final int originalTileSize = 80;
    final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 10;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    private BufferedImage playerImage;
    private int playerXPos;
    private int playerYPos;
    private Direction playerDir;

    private TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        synchronized(g2) {
            tileManager.draw(g2);

            if(playerDir == Direction.EAST) {
                g2.drawImage(playerImage, tileSize+playerXPos, playerYPos, -tileSize, tileSize, null);
            } else {
                g2.drawImage(playerImage, playerXPos, playerYPos, tileSize, tileSize, null);
            }
            g2.setBackground(Color.BLUE);
        }
    }

    public void updatePlayerData(BufferedImage image, int xPos, int yPos, Direction dir) {
        this.playerImage = image;
        this.playerXPos = xPos;
        this.playerYPos = yPos;
        this.playerDir = dir;
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
}
