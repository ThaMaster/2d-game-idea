package se.gmail.game.controller;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import se.gmail.game.model.User;
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
import java.util.ArrayList;

public class GameController implements ActionListener{

    private Timer timer;
    private static final int FRAME_RATE = 60;
    private double currentTime = 0, lastTime = 0;

    private double smElapsedTime = 0;
    //private int fpsCounter = 0;

    private KeyHandler keyHandler = new KeyHandler();
    private MainFrame window;

    private User user = new User("FrasByxan");
    private Player player = new Player();
    private StockMarket sm;
    private StockMarketWindow smWindow;
    private Stock s1, s2, s3;
    private Boolean enableBuy[] = {false, false, false};
    private Boolean enableSell[] = {false, false, false};


    public GameController() {
        keyHandler.setToggleKey('q');
        window = new MainFrame(player);
        this.window.getGamePanel().addKeyListener(keyHandler);
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
            window.getGamePanel().revalidate();
            window.getGamePanel().repaint();
        });
    }
    
    private void updatePlayer() {
        player.getAnimator().update();
        if(keyHandler.movementKeysActive()) {
            player.getAnimator().setAnimation("run");
            if (keyHandler.isKeyActive('w')) {
                player.setYPosition(player.getYPosition() - player.getSpeed());
                player.setDirection(Direction.NORTH);
            }
            if (keyHandler.isKeyActive('s')) {
                player.setYPosition(player.getYPosition() + player.getSpeed());
                player.setDirection(Direction.SOUTH);
            }
            if (keyHandler.isKeyActive('d')) {
                player.setXPosition(player.getXPosition() + player.getSpeed());
                player.setDirection(Direction.EAST);
            }
            if (keyHandler.isKeyActive('a')) {
                player.setXPosition(player.getXPosition() - player.getSpeed());
                player.setDirection(Direction.WEST);
            }
        } else {
            player.getAnimator().setAnimation("idle");
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
        smWindow.addStock(s1, sm.getMaxOwnage(s1.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/iron.png"));
        smWindow.addStock(s2, sm.getMaxOwnage(s2.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/gold.png"));
        smWindow.addStock(s3, sm.getMaxOwnage(s3.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
        
        setStockMarketListeners();
    }

    private void updateStockMarket() {
        boolean toggleMarket = keyHandler.isKeyActive('q');
        if(Boolean.compare(toggleMarket, smWindow.isVisible()) != 0) {
            smWindow.setVisible(toggleMarket);
        }

        smElapsedTime += currentTime - lastTime;
        if(smElapsedTime >= 1000) {
            sm.incTick();
            if(smWindow.isVisible()) {
                sm.performTicks();
                for(int stockId : sm.getStockIds()) {
                    updateStockButtons(stockId);
                    smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                    smWindow.updateUserMoney(user.getMoney());
                }
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

        ArrayList<JButton> buyButtons = new ArrayList<>();
        ArrayList<JButton> sellButtons = new ArrayList<>();

        for(int stockId : sm.getStockIds()) {
            buyButtons = smWindow.getBuyButtons(stockId);
            sellButtons = smWindow.getSellButtons(stockId);
            for(int i = 0; i <= 3; i++) {
                buyButtons.get(i).addActionListener(new BuyButtonListener(stockId, i));
                sellButtons.get(i).addActionListener(new SellButtonListener(stockId, i));
            }
        }
    }

    public class BuyButtonListener implements ActionListener {

        private int id, index, buyAmount = 0;

        public BuyButtonListener(int stockId, int bIndex) {
            id = stockId;
            index = bIndex;
            if(bIndex != 3) {
                buyAmount = (int)Math.pow(10, bIndex);
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(index == 3) {
                buyAmount = sm.getMaxPurchase(id, user.getMoney());
            }
            user.decreaseMoney(buyAmount * sm.getStock(id).getValue());
            sm.buyStocks(id, buyAmount);
            updateStockButtons(id);
            smWindow.updateStock(sm.getStock(id), sm.getOwnage(id), sm.getMaxOwnage(id), enableBuy, enableSell);
            smWindow.updateUserMoney(user.getMoney());
        }
    }

    public class SellButtonListener implements ActionListener {

        private int id, index, sellAmount = 0;

        public SellButtonListener(int stockId, int bIndex) {
            id = stockId;
            index = bIndex;
            if(index != 3) {
                sellAmount = (int)Math.pow(10, bIndex);
            }
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if(index == 3) {
                sellAmount = sm.getOwnage(id);
            }
            user.increaseMoney(sellAmount * sm.getStock(id).getValue());
            sm.sellStocks(id, sellAmount);
            updateStockButtons(id);
            smWindow.updateStock(sm.getStock(id), sm.getOwnage(id), sm.getMaxOwnage(id), enableBuy, enableSell);
            smWindow.updateUserMoney(user.getMoney());
        }

    }

    private void updateStockButtons(int stockId) {
        enableBuy[0] = sm.canBuy(stockId, user.getMoney(), 1);
        enableBuy[1] = sm.canBuy(stockId, user.getMoney(), 10);
        enableBuy[2] = sm.canBuy(stockId, user.getMoney(), 100);
        enableSell[0] = sm.canSell(stockId, 1);
        enableSell[1] = sm.canSell(stockId, 10);
        enableSell[2] = sm.canSell(stockId, 100);
    }
}
