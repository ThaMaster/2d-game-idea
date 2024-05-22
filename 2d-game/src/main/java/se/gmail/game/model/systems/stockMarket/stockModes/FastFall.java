package se.gmail.game.model.systems.stockMarket.modes;

import se.gmail.game.util.Util;

public class FastFall extends StockMode{

    public FastFall() {
        super(4, "fast-fall");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] += Util.randomDouble(-0.134, 0.015);
        result[0] += Util.randomDouble(-5, 0);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        if(Util.probabilityCheck(0.3)) {
            result[0] += Util.randomDouble(-3, 7);
            result[1] += Util.randomDouble(-0.05, 0.05);
        }
        return result;
    }
    
}
