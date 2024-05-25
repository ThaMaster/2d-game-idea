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

    private ArrayList<Stock> stocks = new ArrayList<>();
    private ArrayList<StockMode> stockModes = new ArrayList<>();

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

    public int getLevel() {
        return this.level;
    }

    public void addStock(Stock s) {
        s.setCurrentStockMode(stockModes.get(0));
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
            if(Util.probabilityCheck(0.1) || s.getStockModeDuration() <= 0) {
                s.setCurrentStockMode(selectNextStockMode(s));
            }
            s.performTick(bankCeiling, globalSpike, globalDelta, globalProbability);
            System.out.println(s.getCurrentStockMode().getModeName());
        }
    }

    private void initStockModes() {
        stockModes.add(new Stable());
        stockModes.add(new SlowClimb());
        stockModes.add(new SlowFall());
        stockModes.add(new FastClimb());
        stockModes.add(new FastFall());
        stockModes.add(new Chaotic());
    }

    private StockMode selectNextStockMode(Stock s) {
        double prob = Util.randomDouble(0, 1);
        int mode = s.getCurrentStockMode().getId();

        /**
         * If the stockmode is either fast fall or fast rise there
         * is a 70% chance that it turns Chaotic instead of choosing
         * normally.
         */ 
        if((mode == 3 || mode == 4) && Util.probabilityCheck(0.7)) {
            return stockModes.get(5);
        }

        /**
         * Choose new stockmode normally.
         */
        if(prob < 0.125) {
            return stockModes.get(0);
        } else if(prob >= 0.125 && prob < 0.375) {
            return stockModes.get(1);
        } else if(prob >= 0.375 && prob < 0.625) {
            return stockModes.get(2);
        } else if(prob >= 0.625 && prob < 0.75) {
            return stockModes.get(3);
        } else if(prob >= 0.75 && prob < 0.875) {
            return stockModes.get(4);
        } else {
            return stockModes.get(5);
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Double> getStockValueHistory(int id) {
        for (Stock s : stocks) {
            if(s.getId() == id) {
                return s.getValueHistory();
            }     
        }
        return new ArrayList<Double>();
    }

    public ArrayList<Double> getStockValues() {
        ArrayList<Double> currentValues = new ArrayList<>();
        for (Stock s : stocks) {
            currentValues.add(s.getValue());            
        }
        return currentValues;
    }
}
