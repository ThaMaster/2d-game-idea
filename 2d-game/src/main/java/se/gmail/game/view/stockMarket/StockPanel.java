package se.gmail.game.view.stockMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import se.gmail.game.view.GuiUtil;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StockPanel extends JPanel {

    JLabel stockIcon;
    JLabel stockSymbol;
    JLabel stockPercentage;

    JLabel stockValue;
    JLabel stockAmount;

    JLabel buyLabel;
    ArrayList<JButton> buyButtons = new ArrayList<>();

    JLabel sellLabel;
    ArrayList<JButton> sellButtons = new ArrayList<>();

    JButton hideButton;

    public StockPanel(BufferedImage icon, String name, String symbol, String desc) {
        this.setLayout(new BorderLayout());
        this.add(createTopPanel(icon, symbol, name, desc), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
        this.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTopPanel(BufferedImage bi, String symbol, String name, String desc) {
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel stockSymbolPanel = new JPanel(new FlowLayout());
        stockIcon = new JLabel();
        ImageIcon icon = new ImageIcon(bi);
        stockIcon.setIcon(icon);
        stockSymbol = new JLabel(symbol);
        stockSymbol.setFont(new Font("serif", Font.BOLD, 14));

        stockPercentage = new JLabel("0.00%");        
        stockPercentage.setForeground(Color.RED);

        stockSymbolPanel.add(stockIcon);
        stockSymbolPanel.add(stockSymbol);
        stockSymbolPanel.setToolTipText(GuiUtil.createStockToolTipText(name, desc));

        hideButton = new JButton("Hide");
        
        topPanel.add(stockSymbolPanel);
        topPanel.add(stockPercentage);
        topPanel.add(hideButton);
        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        JPanel valuePanel = new JPanel(new FlowLayout());
        JLabel valText = new JLabel("value:");
        stockValue = new JLabel("$00.00");
        stockValue.setFont(new Font("serif", Font.BOLD, 14));
        stockValue.setForeground(Color.WHITE);
        valuePanel.add(valText);
        valuePanel.add(stockValue);

        JPanel amountPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("stock:");
        stockAmount = new JLabel("0");
        amountPanel.add(amountLabel);
        amountPanel.add(stockAmount);

        centerPanel.add(valuePanel);
        centerPanel.add(amountPanel);
        return centerPanel;
    }

    private JPanel createSouthPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        createStockButtons();
        
        JPanel buyPanel = new JPanel(new FlowLayout());
        buyLabel = new JLabel("Buy");
        buyPanel.add(buyLabel);
        for(JButton b : buyButtons) {
            buyPanel.add(b);
        }

        JPanel sellPanel = new JPanel(new FlowLayout());
        sellLabel = new JLabel("Sell");
        sellPanel.add(sellLabel);
        for(JButton b : sellButtons) {
            sellPanel.add(b);
        }

        buttonPanel.add(buyPanel);
        buttonPanel.add(sellPanel);
        return buttonPanel;
    }

    private void createStockButtons() {
        this.buyButtons.add(new JButton("1"));
        this.buyButtons.add(new JButton("10"));
        this.buyButtons.add(new JButton("100"));
        this.buyButtons.add(new JButton("Max"));
        this.sellButtons.add(new JButton("1"));
        this.sellButtons.add(new JButton("10"));
        this.sellButtons.add(new JButton("100"));
        this.sellButtons.add(new JButton("All"));
    }

    public void setStockValue(double stockValue, double percentage) {
        this.stockValue.setText("$" + String.format("%.02f", stockValue));
        this.stockPercentage.setText(String.format("%.02f", stockValue) + "%");
        if(percentage > 0) {
            this.stockPercentage.setForeground(Color.GREEN);
        } else {
            this.stockPercentage.setForeground(Color.RED);
        }
    }

    public void setStockOwnage(int stockAmount, int stockMaxAmount) {
        this.stockAmount.setText(stockAmount + "/" + stockMaxAmount);
    }

    public String getSymbol() {
        return stockSymbol.getText();
    }
}
