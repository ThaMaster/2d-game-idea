package se.gmail.game.controller;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import se.gmail.game.model.entities.Player;
import se.gmail.game.view.MainFrame;

public class UpdateHandler implements Runnable {

    int fps = 60;
    long targetTime = 1000 / fps;

    KeyHandler keyHandler;
    Thread gameThread;
    MainFrame mainWindow;

    Player player;

    public UpdateHandler(KeyHandler keyH, MainFrame mf) {
        this.keyHandler = keyH;
        this.mainWindow = mf;
        this.player = new Player();
        mainWindow.getGamePanel().updatePlayerData(player.getImage(), player.getXPosition(), player.getYPosition());
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
public void run() {
    final int targetFPS = 60;
    final long targetTimePerFrame = 1000000000 / targetFPS; // nanoseconds per frame

    long lastTime = System.nanoTime();
    long currentTime;
    long elapsedTime;
    long sleepTime;

    while (gameThread != null) {
        currentTime = System.nanoTime();
        elapsedTime = currentTime - lastTime;

        // Update game logic
        update();

        // Render game
        repaint();

        // Calculate sleep time to achieve target frame rate
        sleepTime = targetTimePerFrame - elapsedTime;

        if (sleepTime > 0) {
            try {
                // Sleep to cap frame rate and reduce CPU usage
                Thread.sleep(sleepTime / 1000000); // Convert nanoseconds to milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // If rendering took longer than expected, yield to other threads
            Thread.yield();
        }

        lastTime = System.nanoTime();
    }
}





    private void update() {
        if (keyHandler.isKeyActive(KeyEvent.VK_W)) {
            player.setYPosition(player.getYPosition() - player.getSpeed());
        }
        if (keyHandler.isKeyActive(KeyEvent.VK_S)) {
            player.setYPosition(player.getYPosition() + player.getSpeed());
        }
        if (keyHandler.isKeyActive(KeyEvent.VK_D)) {
            player.setXPosition(player.getXPosition() + player.getSpeed());
        }
        if (keyHandler.isKeyActive(KeyEvent.VK_A)) {
            player.setXPosition(player.getXPosition() - player.getSpeed());
        }
    }

    private void repaint() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindow.getGamePanel().updatePlayerData(player.getImage(), player.getXPosition(), player.getYPosition());
                mainWindow.getGamePanel().revalidate();
                mainWindow.getGamePanel().repaint();
            }
        });

    }
}
