package se.gmail.game.view.stockMarket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class StockValueGraphPanel extends JPanel {

    final int originalTileSize = 80;
    final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 10;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    private ArrayList<Double> stockValues = new ArrayList<>();
    private BufferedImage stockIcon;

    int viewInterval = 10;

    public StockValueGraphPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void addStockValue(double value) {
        this.stockValues.add(value);
    }

    public void addStockValues(ArrayList<Double> values, BufferedImage stockIcon) {
        stockValues.addAll(values);
        this.stockIcon = stockIcon;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Cast to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;

        // Set rendering hints for better graphics quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determine the width and height of the panel
        int width = getWidth();
        int height = getHeight();

        // Determine the horizontal spacing between data points
        int maxDataPoints = stockValues.size();
        int xSpacing = width / (maxDataPoints - 1);

        // Find the maximum value in the data set to scale the graph
        double maxValue = Double.MIN_VALUE;
        for (double value : stockValues) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // Define slope thresholds
        double slopeThresholdUp = 0.01;
        double slopeThresholdDown = -0.01;

        // Draw vertical grid lines at each data point x position
        g2.setColor(Color.GRAY);
        for (int i = 0; i < maxDataPoints; i += 5) {
            int x = i * xSpacing;
            g2.drawLine(x, 0, x, height);
            g2.drawString(String.valueOf(i), x - 5, height - 5);  // Label the x positions
        }

        // Draw horizontal grid lines at even intervals and label them
        int numHorizontalLines = 10;
        for (int i = 0; i <= numHorizontalLines; i++) {
            int y = height - (i * height / numHorizontalLines);
            g2.drawLine(0, y, width, y);
            String label = String.format("%.2f", maxValue * i / numHorizontalLines);
            g2.drawString(label, 5, y - 5);  // Adjust label position as needed
        }

        // Draw the line graph
        for (int i = 0; i < maxDataPoints - 1; i++) {
            int x1 = i * xSpacing;
            int y1 = (int) (height - (stockValues.get(i) * height / maxValue));
            int x2 = (i + 1) * xSpacing;
            int y2 = (int) (height - (stockValues.get(i + 1) * height / maxValue));

            // Calculate the slope between the two points
            double slope = (stockValues.get(i + 1) - stockValues.get(i)) / xSpacing;

            // Determine the color of the line based on the slope
            if (slope > slopeThresholdUp) {
                g2.setColor(Color.GREEN);  // Line going up
            } else if (slope < slopeThresholdDown) {
                g2.setColor(Color.RED);  // Line going down
            } else {
                g2.setColor(Color.YELLOW);  // Line stable
            }

            g2.drawLine(x1, y1, x2, y2);

            // Draw the image at the end of the curve
            if (i == maxDataPoints - 2 && stockIcon != null) {
                int imageWidth = stockIcon.getWidth();
                int imageHeight = stockIcon.getHeight();
                int xImage = (x1 - imageWidth / 2);
                int yImage = (y1 + y2) / 2 - imageHeight / 2;
                g2.drawImage(stockIcon, xImage, yImage, null);
            }
        }
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public int getMaxScreenColumns() {
        return this.maxScreenCol;
    }

    public int getMaxScreenRows() {
        return this.maxScreenRow;
    }

    public int getMaxScreenWidth() {
        return this.screenWidth;
    }

    public int getMaxScreenHeight() {
        return this.screenHeight;
    }

    public void setViewInterval(int interval) {
        this.viewInterval = interval;
    }
}
