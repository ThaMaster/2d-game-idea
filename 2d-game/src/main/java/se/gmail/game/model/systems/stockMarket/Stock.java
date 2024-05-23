package se.gmail.game.model.systems.stockMarket;

import java.util.ArrayList;

import se.gmail.game.model.systems.stockMarket.stockModes.StockMode;
import se.gmail.game.util.Util;

public class Stock {

    private int id;
    private String name;
    private String symbol;
    private String description;

    private double value;
    private ArrayList<Double> valueHistory;
    private double restingValue;
    private double delta;

    private StockMode currentSM;
    private int stockModeDuration;
    private double percentage;

    private int marketCap;

    public Stock(int id, String name, String symbol, String desc) {
        this.value = 1.0;
        this.percentage = 0.0;
        valueHistory = new ArrayList<>();
        valueHistory.add(value);
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = desc;
        this.description = "";
        this.delta = 1;
    }

    public ArrayList<Double> getValueHistory() {
        return this.valueHistory;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getDescription() {
        return this.description;
    }

    public void setMarketCap(int bankLevel) {
        this.marketCap = 100 + 3 * (bankLevel - 1);
    }

    public int getMarketCap() {
        return this.marketCap;
    }

    public void setRestingValue(int bankLevel) {
        this.restingValue = 10 * (this.id + 1) + bankLevel - 1;
    }

    public double getValue() {
        return this.value;
    }

    public double getPercentage() {
        return this.percentage;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Every minute in real time, te stock market "ticks" where every stock is 
     * updated with a new value. This function will perform a series of steps that
     * that depend on RNG.
     */
    public void performTick(double bankCeiling, boolean globalSpike, double globalDelta, double globalProbability) {
        double prevValue = value;
        // Step 1
        delta *= 0.97;
        
        // Step 2
        double modeResults[] = currentSM.firstAdjustment(value, delta);
        value = modeResults[0];
        delta = modeResults[1];

        // Step 3
        value += (restingValue - value) * 0.01;
        
        // Step 4
        if(globalSpike) {
            if(Util.probabilityCheck(globalProbability)) {
                value -= globalDelta * (1 + 7 * (Math.pow(Util.randomDouble(0, 1), 3.0) * delta));
                value -= globalDelta * (1 + 7 * (Math.pow(Util.randomDouble(0, 1), 3.0)));
                delta += globalDelta * Util.randomDouble(1, 5);
                stockModeDuration = 0;
            }
        }
        
        // Step 5
        value += 3 * Math.pow(Util.randomDouble(-1, 1), 11);
        
        // Step 6
        delta += Util.randomDouble(-0.05, 0.05);
        
        // Step 7
        if(Util.probabilityCheck(0.15)) {
            value += Util.randomDouble(-1.5, 1.5);
        }

        // Step 8
        if(Util.probabilityCheck(0.03)) {
            value += Util.randomDouble(-5, 5);
        }
        
        // Step 9
        if(Util.probabilityCheck(0.1)) {
            delta += Util.randomDouble(-0.15, 0.15);
        }
        
        // Step 10
        modeResults = currentSM.secondAdjustment(value, delta);
        value = modeResults[0];
        delta = modeResults[1];

        // Step 11
        if(value > bankCeiling) {
            if(delta > 0) {
                delta *= 0.9;
            }
        }

        // Step 12
        value += delta;

        if(value < 1.0) {
            value = 1.0;
        } else if(value < 5.0) {
            value += (5.0 - value)/2;
            if(delta < 0) {
                delta *= 0.95;
            }
        }
        percentage = ((value / prevValue) - 1) * 100;
        valueHistory.add(value);
    }

    public void setStockModeDuration(int duration) {
       this.stockModeDuration = duration;
    }

    public int getStockModeDuration() {
        return this.stockModeDuration;
    }

    public StockMode getCurrentStockMode()  {
        return this.currentSM;
    }

    public void setCurrentStockMode(StockMode sm) {
        this.currentSM = sm;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass() ) {
            Stock inputObj = (Stock)obj;
            return this.name.equals(inputObj.name) && this.id == inputObj.id; 
        }
        return false;
    }
}
