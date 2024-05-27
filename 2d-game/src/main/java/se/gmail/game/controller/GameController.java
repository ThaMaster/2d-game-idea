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
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameController implements ActionListener{

    private Timer timer;
    private static final int FRAME_RATE = 60;
    private double currentTime = 0, lastTime = 0;

    private double smElapsedTime = 0;
    //private int fpsCounter = 0;

    private KeyHandler keyHandler = new KeyHandler();
    private MainFrame window = new MainFrame();

    private User user = new User("FrasByxan");;
    private Player player;
    private StockMarket sm;
    private StockMarketWindow smWindow;
    private Stock s1, s2, s3;
    private Boolean enableBuy[] = {false, false, false};
    private Boolean enableSell[] = {false, false, false};


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
        smWindow.addStock(s1, sm.getMaxOwnage(s1.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/iron.png"));
        smWindow.addStock(s2, sm.getMaxOwnage(s2.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/gold.png"));
        smWindow.addStock(s3, sm.getMaxOwnage(s3.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
        
        setStockMarketListeners();
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
                for(int stockId : sm.getStockIds()) {
                    updateStockButtons(stockId);
                    smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                    smWindow.updateUserMoney(user.getMoney());
                }
            }
            smElapsedTime = 0;
        }
    }

    /**
     * For the love of god, refactor this shit!
     */
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
            buyButtons.get(0).addActionListener(e -> {
                System.out.println(stockId);
                user.decreaseMoney(1 * sm.getStockValue(stockId));
                sm.buyStocks(stockId, 1);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell); 
                smWindow.updateUserMoney(user.getMoney());
            });

            buyButtons.get(1).addActionListener(e -> {
                user.decreaseMoney(10 * sm.getStock(stockId).getValue());
                sm.buyStocks(stockId, 10);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });

            buyButtons.get(2).addActionListener(e -> {
                user.decreaseMoney(100 * sm.getStock(stockId).getValue());
                sm.buyStocks(stockId, 100);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });

            buyButtons.get(3).addActionListener(e -> {
                int maxAmount = sm.getMaxPurchase(stockId, user.getMoney());
                user.decreaseMoney(maxAmount * sm.getStock(stockId).getValue());
                sm.buyStocks(stockId, maxAmount);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });

            sellButtons = smWindow.getSellButtons(stockId);
            sellButtons.get(0).addActionListener(e -> {
                user.increaseMoney(1 * sm.getStock(stockId).getValue());
                sm.sellStocks(stockId, 1);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });
            sellButtons.get(1).addActionListener(e -> {
                user.increaseMoney(10 * sm.getStock(stockId).getValue());
                sm.sellStocks(stockId, 10);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });
            sellButtons.get(2).addActionListener(e -> {
                user.increaseMoney(100 * sm.getStock(stockId).getValue());
                sm.sellStocks(stockId, 100);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });
            sellButtons.get(3).addActionListener(e -> {
                int allAmount = sm.getOwnage(stockId);
                user.increaseMoney(allAmount * sm.getStock(stockId).getValue());
                sm.sellStocks(stockId, allAmount);
                updateStockButtons(stockId);
                smWindow.updateStock(sm.getStock(stockId), sm.getOwnage(stockId), sm.getMaxOwnage(stockId), enableBuy, enableSell);
                smWindow.updateUserMoney(user.getMoney());
            });
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
