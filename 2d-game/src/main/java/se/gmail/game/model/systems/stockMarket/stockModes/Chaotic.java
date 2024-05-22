package se.gmail.game.model.systems.stockMarket.modes;

import se.gmail.game.util.Util;

public class Chaotic extends StockMode {

    public Chaotic() {
        super(5, "chaotic");
    }

    @Override
    public double[] firstAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        result[1] += Util.randomDouble(-0.15, 0.15);
        return result;
    }

    @Override
    public double[] secondAdjustment(double stockValue, double stockDelta) {
        double result[] = {stockValue, stockDelta};
        if(Util.probabilityCheck(0.5)) {
            result[0] += Util.randomDouble(-5, 5);
        }
        if(Util.probabilityCheck(0.2)) {
            result[1] = Util.randomDouble(-1, 1);
        }
        return result;
    }
    
}
