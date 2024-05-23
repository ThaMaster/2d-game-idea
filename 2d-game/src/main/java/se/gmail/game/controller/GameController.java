package se.gmail.game.controller;

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
        String desc = "Tiny and adorable, with twinkling eyes that sparkle with curiosity. Their infectious laughter fills the air, accompanied by a mischievous grin that lights up their face. They radiate warmth and kindness, captivating everyone around them with their playful spirit.";
        Stock s1 = new Stock(0, "Human", "HUM", desc);
        s1.setMarketCap(1);
        s1.setRestingValue(1);
        StockPanel sp = new StockPanel(ImageLoader.loadImage("/player/sprites/executioner/male/idle/idle1.png"), s1.getName(), s1.getSymbol(), s1.getDescription());
        sm.addStock(s1);
        smWindow.addStockPanel(sp);
        for(int i = 0; i < 50; i++) {
            sm.performTick();
        }
        
        JsonLoader.loadStock("/stockMarket/stock/data/stockData.json");
        
        smWindow.updateStock(s1, 5);
        smWindow.addStockValueHistory(sm.getStockValueHistory(s1.getId()), ImageLoader.loadImage("/player/sprites/executioner/male/idle/idle1.png"));
    }
}
