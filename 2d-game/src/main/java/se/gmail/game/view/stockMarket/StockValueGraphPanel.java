package se.gmail.game.view.stockMarket;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

public class StockValueGraphPanel extends JPanel {

    final int originalTileSize = 80;
    final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 10;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    private ArrayList<Integer> stockIds = new ArrayList<>();
    private HashMap<Integer, Boolean> stockVisible = new HashMap<>();
    private HashMap<Integer, ArrayList<Double>> stockValues = new HashMap<>();
    private HashMap<Integer, BufferedImage> stockIcons = new HashMap<>();

    int viewInterval = 50;

    public StockValueGraphPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void addNewStock(int stockId, BufferedImage stockIcon, ArrayList<Double> values) {
        stockIds.add(stockId);
        stockValues.put(stockId, values);
        stockIcons.put(stockId, stockIcon);
    }

    public void updateStockValues(int stockId, ArrayList<Double> values) {
        stockValues.put(stockId, values);
    }

    public void removeStock(int stockId) {
        stockIds.remove(stockId);
        stockValues.remove(stockId);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast to Graphics2D for better control
        Graphics2D g2 = (Graphics2D) g;

        // Set rendering hints for better graphics quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Determine the width and height of the panel
        int width = getWidth();
        int height = getHeight();

        // Determine the horizontal spacing between data points
        if (stockValues.size() == 0 || stockIds.size() == 0) {
            return;
        }

        ArrayList<Double> stockData = stockValues.get(stockIds.get(0));
        BufferedImage stockIcon = stockIcons.get(stockIds.get(0));

        int maxDataPoints = stockData.size();

        // Calculate xSpacing to use floating-point division
        double xSpacing = (viewInterval > 1) ? (double) width / (viewInterval - 1) : width;

        // Find the maximum and minimum values in the recent data set to scale the graph
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < maxDataPoints; i++) {
            double value = stockData.get(i);
            if (value > maxValue) {
                maxValue = value;
            }
            if (value < minValue) {
                minValue = value;
            }
        }

        // Define slope thresholds
        double slopeThresholdUp = 0.01;
        double slopeThresholdDown = -0.01;

        // Draw vertical grid lines at each data point x position
        g2.setColor(Color.GRAY);
        for (int i = 0; i < viewInterval; i+=viewInterval/10) {
            int x = (int) (i * xSpacing);
            g2.drawLine(x, 0, x, height);
            g2.drawString(String.valueOf(viewInterval - i), x - 20, height - 5);
        }

        // Draw horizontal grid lines at even intervals and label them
        int numHorizontalLines = 10;
        for (int i = 0; i <= numHorizontalLines; i++) {
            int y = height - (i * height / numHorizontalLines);
            g2.drawLine(0, y, width, y);
            String label = String.format("%.2f", minValue + (maxValue - minValue) * i / numHorizontalLines);
            g2.drawString(label, 5, y - 5); // Adjust label position as needed
        }

        // Draw the line graph
        for (int i = 0; i < Math.min(viewInterval - 1, maxDataPoints  - 1); i++) {
            int x1 = (int) (i * xSpacing);
            int y1 = (int) (height - ((stockData.get(i) - minValue) * height / (maxValue - minValue)));
            int x2 = (int) ((i + 1) * xSpacing);
            int y2 = (int) (height - ((stockData.get(i + 1) - minValue) * height / (maxValue - minValue)));

            // Calculate the slope between the two points
            double slope = (stockData.get(i + 1) - stockData.get(i)) / xSpacing;

            // Determine the color of the line based on the slope
            if (slope > slopeThresholdUp) {
                g2.setColor(Color.GREEN); // Line going up
            } else if (slope < slopeThresholdDown) {
                g2.setColor(Color.RED); // Line going down
            } else {
                g2.setColor(Color.YELLOW); // Line stable
            }

            g2.drawLine(x1, y1, x2, y2);

            // Draw the image at the end of the curve
            if (i == viewInterval - 2 && stockIcon != null) {
                int imageWidth = stockIcon.getWidth();
                int imageHeight = stockIcon.getHeight();
                int xImage = (int) ((x1 + x2) / 2 - imageWidth / 2);
                int yImage = (int) ((y1 + y2) / 2 - imageHeight / 2);
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
        this.revalidate();
        this.repaint();
    }

    public void setStockVisible(int stockId, boolean bool) {
        stockVisible.put(stockId, bool);
    }
}
