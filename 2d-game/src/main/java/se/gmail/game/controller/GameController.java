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
        StockMarket sm = new StockMarket("Stockholm");        
        ArrayList<ArrayList<String>> stockData = JsonLoader.loadStockData("/stockMarket/stocks/data/stockData.json");
        ArrayList<String> diamond = stockData.get(9);
        Stock s1 = new Stock(sm.getLevel(), Integer.parseInt(diamond.get(0)), diamond.get(1), diamond.get(2), diamond.get(3));
        s1.setMarketCap(1);
        sm.addStock(s1);
        for(int i = 0; i < 50; i++) {
            sm.performTick();
        }

        smWindow = new StockMarketWindow(sm.getName());
        setStockMarketListeners();
        smWindow.addStock(s1, ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
        smWindow.updateStock(s1, 5);
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
