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
        long lastTime = System.nanoTime();
        long currentTime;
        long elapsedTime;
        long sleepTime;
        double delta = 0;
        double drawInterval = 1000000000 / fps;
        long timer = System.currentTimeMillis();
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            elapsedTime = currentTime - lastTime;
            lastTime = currentTime;
            delta += elapsedTime / drawInterval;

            while (delta >= 1) {
                update();
                delta--;
                drawCount++;
                repaint();
            }


            sleepTime = targetTime - (System.currentTimeMillis() - timer);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer += 1000;
            }
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
