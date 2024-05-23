package se.gmail.game.view.stockMarket;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import se.gmail.game.view.StyleManager;

public class StockMarketTopPanel extends JPanel {

    JLabel marketName;

    public StockMarketTopPanel(String name) {
        this.setLayout(new FlowLayout());
        marketName = new JLabel(name);
        StyleManager.applyTitleStyle(marketName);

        JPanel marketNamePanel = new JPanel(new FlowLayout());
        marketNamePanel.add(marketName);
        this.add(marketNamePanel);
    }
}
