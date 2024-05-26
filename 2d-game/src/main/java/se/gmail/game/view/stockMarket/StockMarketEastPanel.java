package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import se.gmail.game.model.systems.stockMarket.Stock;

public class StockMarketEastPanel extends JPanel {

    JLabel marketName;
    JPanel eastPanel;
    JScrollPane scroll;

    ArrayList<StockPanel> stockPanels;
    
    public StockMarketEastPanel() {
        stockPanels = new ArrayList<>();
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));   
        eastPanel.setBackground(Color.BLACK);    
        scroll = new JScrollPane(eastPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBackground(Color.BLACK);
        this.add(scroll, BorderLayout.CENTER); 
        this.setBackground(Color.BLACK);
    }

    public void addStockPanel(StockPanel sp) {
        stockPanels.add(sp);
        JPanel wrapperPanel = new JPanel(new FlowLayout());
        wrapperPanel.add(sp);
        wrapperPanel.setBackground(Color.BLACK);
        wrapperPanel.setBorder(new EmptyBorder(2, 4, 2, 4));
        eastPanel.add(wrapperPanel);
        eastPanel.revalidate();
        eastPanel.repaint();
    }

    public void updateStock(Stock s, int amount, int maxAmount, Boolean canBuy[], Boolean canSell[]) {
        String updateSymbol = s.getSymbol();
        for(StockPanel sp : stockPanels) {
            if(sp.getSymbol().equals(updateSymbol)) {
                sp.setStockValue(s.getValue(), s.getPercentage());
                sp.setStockOwnage(amount, maxAmount);
                sp.updateButtons(canBuy, canSell);
            }
        }
    }

    public ArrayList<JButton> getBuyButtons(int stockId) {
        for(StockPanel sp : stockPanels) {
            if(sp.getStockId() == stockId) {
                return sp.getBuyButtons();
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<JButton> getSellButtons(int stockId) {
        for(StockPanel sp : stockPanels) {
            if(sp.getStockId() == stockId) {
                return sp.getSellButtons();
            }
        }
        return new ArrayList<>();
    }
}
