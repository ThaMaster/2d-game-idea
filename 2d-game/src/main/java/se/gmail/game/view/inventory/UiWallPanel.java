package se.gmail.game.view.inventory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import se.gmail.game.util.ImageLoader;

public class UiWallPanel extends JPanel {

    private HashMap<String, BufferedImage> wallImages = new HashMap<>();
    private int wallWidth = 0;
    private int wallHeight = 0;

    private int panelWidth = 0;
    private int panelHeight = 0;

    public UiWallPanel(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;
        this.wallWidth = width / 3;
        this.wallHeight = height / 3;
        this.setPreferredSize(new Dimension(width, height));
        loadUiWalls();
    }

    public int getPanelWidth() {
        return this.panelWidth;
    }

    public int getPanelHeight() {
        return this.panelHeight;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        renderUiWall(g2);
    }

    private void loadUiWalls() {
        wallImages.put("t_l_c", ImageLoader.loadImage("/ui/ui_walls/ui_wall_1.png"));
        wallImages.put("t_r_c", ImageLoader.loadImage("/ui/ui_walls/ui_wall_2.png"));
        wallImages.put("b_l_c", ImageLoader.loadImage("/ui/ui_walls/ui_wall_3.png"));
        wallImages.put("b_r_c", ImageLoader.loadImage("/ui/ui_walls/ui_wall_4.png"));
        wallImages.put("r_w", ImageLoader.loadImage("/ui/ui_walls/ui_wall_5.png"));
        wallImages.put("l_w", ImageLoader.loadImage("/ui/ui_walls/ui_wall_6.png"));
        wallImages.put("b_w", ImageLoader.loadImage("/ui/ui_walls/ui_wall_7.png"));
        wallImages.put("t_w", ImageLoader.loadImage("/ui/ui_walls/ui_wall_8.png"));
        wallImages.put("fill", ImageLoader.loadImage("/ui/ui_walls/ui_wall_9.png"));
    }
    
    private void renderUiWall(Graphics2D g2) {
        g2.drawImage(wallImages.get("t_l_c"), 0, 0, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("t_w"), wallWidth, 0, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("t_r_c"), wallWidth*2, 0, wallWidth, wallHeight, null);

        g2.drawImage(wallImages.get("l_w"), 0, wallHeight, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("fill"), wallWidth, wallHeight, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("r_w"), wallWidth*2, wallHeight, wallWidth, wallHeight, null);

        g2.drawImage(wallImages.get("b_l_c"), 0, wallHeight*2, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("b_w"), wallWidth, wallHeight*2, wallWidth, wallHeight, null);
        g2.drawImage(wallImages.get("b_r_c"), wallWidth*2, wallHeight*2, wallWidth, wallHeight, null);
    }
}
