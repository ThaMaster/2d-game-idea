package se.gmail.game.model.systems.stockMarket.modes;

import se.gmail.game.util.Util;

public class Stable extends StockMode {

    public Stable() {
        super(0, "stable");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] *= 0.95;
        result[1] += Util.randomDouble(-0.025, 0.025);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        return result;
    }
    
}
