package se.gmail.game.model.systems.stockMarket;

import java.util.ArrayList;

import se.gmail.game.model.systems.stockMarket.stockModes.*;
import se.gmail.game.util.Util;

public class StockMarket {

    private String name;
    private int level;
    private double bankCeiling;

    private double globalSpikeChance;
    private double globalDelta;
    private double globalProbability;

    private ArrayList<Stock> stocks;
    private ArrayList<StockMode> stockModes;

    public StockMarket(String name) {
        this.name = name;
        setDefaultMarketValues();
        initStockModes();
    }

    private void setDefaultMarketValues() {
        level = 1;
        bankCeiling = 97.0 + (level * 3.0);
        globalSpikeChance = 0.1;
        globalDelta = 0;
        globalProbability = 0;
    }

    public void addStock(Stock s) {
        stocks.add(s);
    }

    public void removeStock(Stock s) {
        stocks.remove(s);
    }

    public Stock getStock(Stock s) {
        return stocks.get(stocks.indexOf(s));
    }

    public void performTick() {
        boolean globalSpike = false;
        if(Util.probabilityCheck(globalSpikeChance)) {
            globalSpike = true;
            globalDelta = Util.randomDouble(-1, 1);
            globalProbability = Util.randomDouble(0, 1);
        } 
        for (Stock s : stocks) {
            s.performTick(bankCeiling, globalSpike, globalDelta, globalProbability);
        }
    }

    private void initStockModes() {
        stockModes.add(new Stable());
        stockModes.add(new SlowClimb());
        stockModes.add(new SlowFall());
        stockModes.add(new FastFall());
        stockModes.add(new Chaotic());
    }

    public String getName() {
        return this.name;
    }
}
