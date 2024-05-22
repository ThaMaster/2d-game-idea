package se.gmail.game.model.tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import se.gmail.game.util.ImageLoader;
import se.gmail.game.view.GamePanel;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];
        mapTileNum = new int[gp.getMaxScreenColumns()][gp.getMaxScreenRows()];

        getTileImages();
        loadMap();
    }

    public void getTileImages() {
        tiles[0] = new Tile();
        tiles[0].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands8.png"));
        tiles[1] = new Tile();
        tiles[1].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands7.png"));
        tiles[2] = new Tile();
        tiles[2].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands9.png"));
        tiles[3] = new Tile();
        tiles[3].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands5.png"));
        tiles[4] = new Tile();
        tiles[4].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands11.png"));
        tiles[5] = new Tile();
        tiles[5].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands4.png"));
        tiles[6] = new Tile();
        tiles[6].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands6.png"));
        tiles[7] = new Tile();
        tiles[7].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands10.png"));
        tiles[8] = new Tile();
        tiles[8].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands12.png"));
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            for(int row = 0; row < gp.getMaxScreenRows(); row++) {
                line = br.readLine();
                for(int col = 0; col < gp.getMaxScreenColumns(); col++) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;
        for(int row = 0; row < gp.getMaxScreenRows(); row++) {
            for(int col = 0; col < gp.getMaxScreenColumns(); col++) {
                int tileNum = mapTileNum[col][row];
                g2.drawImage(tiles[tileNum].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
                x += gp.getTileSize();
            }
            x = 0;
            y += gp.getTileSize();
        }
    }
}
