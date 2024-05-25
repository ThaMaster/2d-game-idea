package se.gmail.game.controller;

import java.util.ArrayList;

import se.gmail.game.model.systems.stockMarket.*;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.JsonLoader;
import se.gmail.game.view.stockMarket.StockMarketWindow;

public class GameController {

    UpdateHandler updateHandler;
    StockMarketWindow smWindow;

    public GameController() {
        // KeyHandler keyHandler = new KeyHandler();
        // MainFrame window = new MainFrame();
        // window.getGamePanel().addKeyListener(keyHandler);
        // this.updateHandler = new UpdateHandler(keyHandler, window);
        // updateHandler.startGameThread();
        StockMarket sm = new StockMarket("Town of the Hoobaglooba");        
        ArrayList<ArrayList<String>> stockData = JsonLoader.loadStockData("/stockMarket/stocks/data/stockData.json");
        ArrayList<String> iron = stockData.get(4);
        Stock s1 = new Stock(sm.getLevel(), Integer.parseInt(iron.get(0)), iron.get(1), iron.get(2), iron.get(3));
        ArrayList<String> gold = stockData.get(6);
        Stock s2 = new Stock(sm.getLevel(), Integer.parseInt(gold.get(0)), gold.get(1), gold.get(2), gold.get(3));
        ArrayList<String> diamond = stockData.get(9);
        Stock s3 = new Stock(sm.getLevel(), Integer.parseInt(diamond.get(0)), diamond.get(1), diamond.get(2), diamond.get(3));
        sm.addStock(s1);
        sm.addStock(s2);
        sm.addStock(s3);
        for(int i = 0; i < 50; i++) {
            sm.performTick();
        }

        smWindow = new StockMarketWindow(sm.getName());
        setStockMarketListeners();
        smWindow.addStock(s1, ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
        smWindow.updateStock(s1, 5);
        smWindow.addStock(s2, ImageLoader.loadImage("/stockMarket/stocks/icons/gold.png"));
        smWindow.updateStock(s2, 5);
        smWindow.addStock(s3, ImageLoader.loadImage("/stockMarket/stocks/icons/iron.png"));
        smWindow.updateStock(s3, 5);
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
