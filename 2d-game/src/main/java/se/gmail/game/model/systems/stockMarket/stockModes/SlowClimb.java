package se.gmail.game.model.systems.stockMarket.stockModes;

import se.gmail.game.util.Util;

public class SlowClimb extends StockMode {

    public SlowClimb() {
        super(1, "slow-climb");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] *= 0.99;
        result[1] += Util.randomDouble(-0.005, 0.045);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        return result;
    }
    
}
