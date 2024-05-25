package se.gmail.game.controller;

import java.util.ArrayList;

import se.gmail.game.model.systems.stockMarket.*;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.JsonLoader;
import se.gmail.game.view.stockMarket.StockMarketWindow;
import se.gmail.game.view.stockMarket.StockPanel;

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
        smWindow = new StockMarketWindow(sm.getName());
        
        ArrayList<ArrayList<String>> stockData = JsonLoader.loadStockData("/stockMarket/stocks/data/stockData.json");
        ArrayList<String> diamond = stockData.get(9);
        Stock s1 = new Stock(sm.getLevel(), Integer.parseInt(diamond.get(0)), diamond.get(1), diamond.get(2), diamond.get(3));
        s1.setMarketCap(1);
        StockPanel sp1 = new StockPanel(ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"), s1.getName(), s1.getSymbol(), s1.getDescription());

        sm.addStock(s1);
        smWindow.addStockPanel(sp1);
        for(int i = 0; i < 100; i++) {
            sm.performTick();
        }
        
        smWindow.updateStock(s1, 5);
        smWindow.addStockValueHistory(sm.getStockValueHistory(s1.getId()), ImageLoader.loadImage("/stockMarket/stocks/icons/diamond.png"));
    }
}
