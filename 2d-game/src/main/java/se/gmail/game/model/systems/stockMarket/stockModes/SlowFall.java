package se.gmail.game.model.systems.stockMarket.modes;

import se.gmail.game.util.Util;

public class SlowFall extends StockMode {

    public SlowFall() {
        super(2, "slow-fall");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] *= 0.99;
        result[1] += Util.randomDouble(-0.045, 0.005);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        return result;
    }
    
}
