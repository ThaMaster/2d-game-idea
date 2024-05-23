package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.RepaintManager;

import se.gmail.game.model.systems.stockMarket.Stock;

public class StockMarketWindow extends JFrame {

    StockMarketTopPanel smTopPanel;
    StockMarketEastPanel smEastPanel;
    StockValueGraphPanel svGraphPanel;

    public StockMarketWindow(String marketName) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Stock Market");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);
        this.setLayout(new BorderLayout());
        
        smTopPanel = new StockMarketTopPanel(marketName);
        svGraphPanel = new StockValueGraphPanel();
        smEastPanel = new StockMarketEastPanel();
        this.add(smTopPanel, BorderLayout.NORTH);
        this.add(smEastPanel, BorderLayout.EAST);
        this.add(svGraphPanel, BorderLayout.CENTER);

        this.setJMenuBar(createMenuBar());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu stockMenu = new JMenu("Stock Options");
        JMenu graphMenu = new JMenu("Graph Options");
        menuBar.add(stockMenu);
        menuBar.add(graphMenu);
        return menuBar;
    }

    public void addStockValueHistory(ArrayList<Double> values) {
        svGraphPanel.addStockValues(values);
        svGraphPanel.repaint();
    }

    public void addStockPanel(StockPanel sp) {
        smEastPanel.addStockPanel(sp);
    }

    public void updateStock(Stock s, int amount) {
        smEastPanel.updateStock(s, amount);
    }
}
