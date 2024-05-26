package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.gmail.game.util.ImageLoader;
import se.gmail.game.view.StyleManager;

public class StockMarketTopPanel extends JPanel {

    JLabel marketName;

    JLabel moneyIcon;
    JLabel moneyLabel;

    public StockMarketTopPanel(String name) {
        this.setLayout(new BorderLayout());
        JPanel marketNamePanel = new JPanel(new FlowLayout());
        marketName = new JLabel(name);
        marketNamePanel.add(marketName);

        JPanel moneyPanel = new JPanel(new FlowLayout());
        this.moneyIcon = new JLabel();
        ImageIcon icon = new ImageIcon(ImageLoader.loadImage("/general/icons/gold_coin.png"));
        this.moneyIcon.setIcon(icon);

        this.moneyLabel = new JLabel("00.00");

        moneyPanel.add(moneyIcon);
        moneyPanel.add(moneyLabel);
        
        StyleManager.applyTitleStyle(marketName);

        this.add(marketNamePanel, BorderLayout.CENTER);
        this.add(moneyPanel, BorderLayout.EAST);
    }

    public void setMoneyAmount(double money) {
        this.moneyLabel.setText("$" + String.format("%.02f", money));
    }
}
