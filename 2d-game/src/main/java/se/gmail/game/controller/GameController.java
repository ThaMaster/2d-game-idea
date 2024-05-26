package se.gmail.game.controller;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.systems.stockMarket.Stock;
import se.gmail.game.model.systems.stockMarket.StockMarket;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.JsonLoader;
import se.gmail.game.util.enums.Direction;
import se.gmail.game.view.MainFrame;
import se.gmail.game.view.stockMarket.StockMarketWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameController implements ActionListener{

    private Timer timer;
    private static final int FRAME_RATE = 60;
    private double currentTime = 0, lastTime = 0;

    private double smElapsedTime = 0;
    //private int fpsCounter = 0;

    KeyHandler keyHandler = new KeyHandler();
    MainFrame window = new MainFrame();

    Player player;
    StockMarket sm;
    StockMarketWindow smWindow;
    Stock s1, s2, s3;


    public GameController() {
        keyHandler.setToggleKey(KeyEvent.VK_Q);
        this.window.getGamePanel().addKeyListener(keyHandler);
        this.player = new Player();

        this.timer = new Timer(1000/FRAME_RATE, this);
        initStockMarket();
        startGame();
    }

    public void startGame() {
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        currentTime = System.currentTimeMillis();

        //fpsCounter++;
        update();
        render(); 
        
        lastTime = currentTime;
    }


    /**
     * Function for updating the logic of the game.
     */
    private void update() {
        updatePlayer();
        updateStockMarket();
    }

    private void render() {
        SwingUtilities.invokeLater(() -> {
            window.getGamePanel().updatePlayerData(player.getAnimator().getCurrentSprite(), player.getXPosition(), player.getYPosition(), player.getDirection());
            window.getGamePanel().revalidate();
            window.getGamePanel().repaint();
        });
    }
    
    private void updatePlayer() {
        player.getAnimator().update();
        if(keyHandler.movementKeysActive()) {
            player.getAnimator().changeAnimation("run");
            if (keyHandler.isKeyActive(KeyEvent.VK_W)) {
                player.setYPosition(player.getYPosition() - player.getSpeed());
                player.setDirection(Direction.NORTH);
            }
            if (keyHandler.isKeyActive(KeyEvent.VK_S)) {
                player.setYPosition(player.getYPosition() + player.getSpeed());
                player.setDirection(Direction.SOUTH);
            }
            if (keyHandler.isKeyActive(KeyEvent.VK_D)) {
                player.setXPosition(player.getXPosition() + player.getSpeed());
                player.setDirection(Direction.EAST);
            }
            if (keyHandler.isKeyActive(KeyEvent.VK_A)) {
                player.setXPosition(player.getXPosition() - player.getSpeed());
                player.setDirection(Direction.WEST);
            }
        } else {
            player.getAnimator().changeAnimation("idle");
        }
    }

    private void initStockMarket() {
        this.sm = new StockMarket("Town of the Hoobaglooba");        
        ArrayList<ArrayList<String>> stockData = JsonLoader.loadStockData("/stockMarket/stocks/data/stockData.json");
        ArrayList<String> iron = stockData.get(4);
        this.s1 = new Stock(sm.getLevel(), Integer.parseInt(iron.get(0)), iron.get(1), iron.get(2), iron.get(3));
        ArrayList<String> gold = stockData.get(6);
        this.s2 = new Stock(sm.getLevel(), Integer.parseInt(gold.get(0)), gold.get(1), gold.get(2), gold.get(3));
        ArrayList<String> diamond = stockData.get(9);
        this.s3 = new Stock(sm.getLevel(), Integer.parseInt(diamond.get(0)), diamond.get(1), diamond.get(2), diamond.get(3));
        this.sm.addStock(s1);
        this.sm.addStock(s2);
        this.sm.addStock(s3);

        smWindow = new StockMarketWindow(sm.getName());
        setStockMarketListeners();
        smWindow.addStock(s1, ImageLoader.loadImage("/stockMarket/stocks/icons/iron.png"));
        smWindow.addStock(s2, ImageLoader.loadImage("/stockMarket/stocks/icons/gold.png"));
        smWindow.addStock(s3, ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
    }

    private void updateStockMarket() {
        boolean toggleMarket = keyHandler.isKeyActive(KeyEvent.VK_Q);
        if(Boolean.compare(toggleMarket, smWindow.isVisible()) != 0) {
            smWindow.setVisible(toggleMarket);
        }

        smElapsedTime += currentTime - lastTime;
        if(smElapsedTime >= 1000) {
            sm.incTick();
            if(smWindow.isVisible()) {
                sm.performTicks();
                smWindow.updateStock(s1, 5);
                smWindow.updateStock(s2, 5);
                smWindow.updateStock(s3, 5);
            }
            smElapsedTime = 0;
        }
    }

    private void setStockMarketListeners() {
        smWindow.getScalingOption1Item().addItemListener(e -> {
            smWindow.updateGraphInterval(10);
        });
        smWindow.getScalingOption2Item().addItemListener(e -> {
            smWindow.updateGraphInterval(50);
        });
        smWindow.getScalingOption3Item().addItemListener(e -> {
            smWindow.updateGraphInterval(100);
        });
    }
}
