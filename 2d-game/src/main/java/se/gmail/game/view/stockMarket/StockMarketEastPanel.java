package se.gmail.game.view.stockMarket;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.gmail.game.model.systems.stockMarket.Stock;

public class StockMarketEastPanel extends JPanel {

    JLabel marketName;
    JPanel eastPanel;

    ArrayList<StockPanel> stockPanels;

    public StockMarketEastPanel() {
        stockPanels = new ArrayList<>();
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));       
        this.add(eastPanel); 
        this.setBackground(Color.BLACK);
    }

    public void addStockPanel(StockPanel sp) {
        stockPanels.add(sp);
        eastPanel.add(sp);
        this.repaint();
    }

    public void updateStock(Stock s, int amount) {
        String updateSymbol = s.getSymbol();
        for(StockPanel sp : stockPanels) {
            if(sp.getSymbol().equals(updateSymbol)) {
                sp.setStockValue(s.getValue(), s.getPercentage());
                sp.setStockOwnage(ALLBITS, ABORT);
            }
        }
    }
}
