package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.RepaintManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;

import se.gmail.game.model.systems.stockMarket.Stock;

public class StockMarketWindow extends JFrame {

    StockMarketTopPanel smTopPanel;
    StockMarketEastPanel smEastPanel;
    StockValueGraphPanel svGraphPanel;

    JCheckBoxMenuItem scalingOption1;
    JCheckBoxMenuItem scalingOption2;
    JCheckBoxMenuItem scalingOption3;

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
        JMenu scalingMenu = new JMenu("Scaling Options");

        this.scalingOption1 = new JCheckBoxMenuItem("10x");
        this.scalingOption2 = new JCheckBoxMenuItem("50x");
        this.scalingOption2.setSelected(true);
        this.scalingOption3 = new JCheckBoxMenuItem("100x");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(scalingOption1);
        bg.add(scalingOption2);
        bg.add(scalingOption3);

        scalingMenu.add(scalingOption1);
        scalingMenu.add(scalingOption2);
        scalingMenu.add(scalingOption3);

        graphMenu.add(scalingMenu);
        menuBar.add(stockMenu);
        menuBar.add(graphMenu);
        return menuBar;
    }

    public void addStock(Stock s, int maxAmount, BufferedImage stockIcon) {
        StockPanel sp = new StockPanel(s.getId(), stockIcon, maxAmount, s.getName(), s.getSymbol(), s.getDescription());
        smEastPanel.addStockPanel(sp);
        sp.getHideButton().addActionListener(e -> {
            sp.toggleHideAction();
            svGraphPanel.setStockVisible(s.getId(), sp.getExpanded());
        });
        svGraphPanel.addNewStock(s.getId(), stockIcon, s.getValueHistory());
    }

    public void updateStock(Stock s, int amount, int maxAmount, Boolean canBuy[], Boolean canSell[]) {
        smEastPanel.updateStock(s, amount, maxAmount, canBuy, canSell);
        svGraphPanel.updateStockValues(s.getId(), s.getValueHistory());
    }

    public void updateUserMoney(double money) {
        smTopPanel.setMoneyAmount(money);
    }

    public void removeStock(int stockId) {
        svGraphPanel.removeStock(stockId);
    }

    public void updateGraphInterval(int newInterval) {
        this.svGraphPanel.setViewInterval(newInterval);
    }

    public JCheckBoxMenuItem getScalingOption1Item() {
        return this.scalingOption1;
    }

    public JCheckBoxMenuItem getScalingOption2Item() {
        return this.scalingOption2;
    }

    public JCheckBoxMenuItem getScalingOption3Item() {
        return this.scalingOption3;
    }

    public ArrayList<JButton> getBuyButtons(int stockId) {
        return this.smEastPanel.getBuyButtons(stockId);
    }
    public ArrayList<JButton> getSellButtons(int stockId) {
        return this.smEastPanel.getSellButtons(stockId);
    }
}
