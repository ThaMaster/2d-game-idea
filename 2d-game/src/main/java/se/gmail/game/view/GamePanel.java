package se.gmail.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import se.gmail.game.util.enums.Direction;

public class GamePanel extends JPanel {

    final int originalTileSize = 48;
    final int scale = 2;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    BufferedImage playerImage;
    int playerXPos;
    int playerYPos;
    Direction playerDir;

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
}
