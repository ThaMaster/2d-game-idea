package se.gmail.game.controller;

import java.awt.event.KeyEvent;
import java.util.Vector;

import se.gmail.game.model.entities.Player;
import se.gmail.game.view.MainFrame;

public class UpdateHandler implements Runnable {
    
    int fps = 60;

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
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        double remainingTime;
        while(gameThread != null) {

            update();
            repaint();

            remainingTime = nextDrawTime - System.nanoTime();
            remainingTime /= 1000000;
            try {
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if(keyHandler.isKeyActive(KeyEvent.VK_W)) {
            player.setYPosition(player.getYPosition() - player.getSpeed());
        } else if(keyHandler.isKeyActive(KeyEvent.VK_S)) {
            player.setYPosition(player.getYPosition() + player.getSpeed());
        } else if(keyHandler.isKeyActive(KeyEvent.VK_D)) {
            player.setXPosition(player.getXPosition() + player.getSpeed());
        } else if(keyHandler.isKeyActive(KeyEvent.VK_A)) {
            player.setXPosition(player.getXPosition() - player.getSpeed());
        }
    }

    private void repaint() {
        mainWindow.getGamePanel().updatePlayerData(player.getImage(), player.getXPosition(), player.getYPosition());
        mainWindow.getGamePanel().repaint();
    }
    
}
