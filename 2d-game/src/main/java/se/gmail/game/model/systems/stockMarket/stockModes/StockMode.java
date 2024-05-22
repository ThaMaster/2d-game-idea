package se.gmail.game.model.systems.stockMarket.stockModes;

public abstract class StockMode {

    private int id;
    private String modeName;

    public StockMode(int id, String modeName) {
        this.id = id;
        this.modeName = modeName;
    }

    public int getId() {
        return this.id;
    }

    public String getModeName() {
        return this.modeName;
    }

    public abstract double[] firstAdjustment(double stockValue, double stockDelta);
    public abstract double[] secondAdjustment(double stockValue, double stockDelta);
}