package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import se.gmail.game.model.systems.stockMarket.Stock;

public class StockMarketEastPanel extends JPanel {

    JLabel marketName;
    JPanel eastPanel;
    JScrollPane scroll;

    ArrayList<StockPanel> stockPanels;
    
    public StockMarketEastPanel() {
        this.setLayout(new BorderLayout());
        stockPanels = new ArrayList<>();
        eastPanel = new JPanel();
        scroll = new JScrollPane(eastPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));       
        this.add(scroll, BorderLayout.CENTER); 
        this.setBackground(Color.BLACK);
    }

    public void addStockPanel(StockPanel sp) {
        sp.setBorder(new MatteBorder(5, 10, 5, 25, Color.GRAY));
        stockPanels.add(sp);
        eastPanel.add(sp);
        eastPanel.revalidate();
        eastPanel.repaint();
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
