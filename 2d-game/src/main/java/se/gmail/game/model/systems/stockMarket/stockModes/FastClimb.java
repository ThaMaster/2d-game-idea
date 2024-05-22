package se.gmail.game.model.systems.stockMarket.stockModes;

import se.gmail.game.util.Util;

public class FastClimb extends StockMode {

    public FastClimb() {
        super(3, "fast-climb");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] += Util.randomDouble(-0.015, 0.135);
        result[0] += Util.randomDouble(0, 5);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        if(Util.probabilityCheck(0.3)) {
            result[0] += Util.randomDouble(-7, 3);
            result[1] += Util.randomDouble(-0.05, 0.05);
        }
        return result;
    }
    
}
