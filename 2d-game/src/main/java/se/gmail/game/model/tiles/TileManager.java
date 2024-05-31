package se.gmail.game.model.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import se.gmail.game.util.ImageLoader;
import se.gmail.game.view.GamePanel;
import se.gmail.game.util.CollisionBox;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[10];
        mapTileNum = new int[gp.getMaxWorldColumns()][gp.getMaxWorldRows()];

        getTileImages();
        loadMap();
    }

    public int[][] getMapTileNum() {
        return this.mapTileNum;
    }

    public Tile getTile(int tileNum) {
        return this.tiles[tileNum];
    }

    public int getTileSize() {
        return this.gp.getTileSize();
    }

    public void getTileImages() {
        tiles[0] = new Tile("Grass");
        tiles[0].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands8.png"));
        tiles[1] = new Tile("Wall1");
        tiles[1].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands7.png"));
        tiles[1].getCollisionBox().enableCollision(true);
        tiles[2] = new Tile("Wall2");
        tiles[2].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands9.png"));
        tiles[2].getCollisionBox().enableCollision(true);
        tiles[3] = new Tile("Wall3");
        tiles[3].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands5.png"));
        tiles[3].getCollisionBox().enableCollision(true);
        tiles[4] = new Tile("Wall4");
        tiles[4].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands11.png"));
        tiles[4].getCollisionBox().enableCollision(true);
        tiles[5] = new Tile("Wall5");
        tiles[5].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands4.png"));
        tiles[5].getCollisionBox().enableCollision(true);
        tiles[6] = new Tile("Wall6");
        tiles[6].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands6.png"));
        tiles[6].getCollisionBox().enableCollision(true);
        tiles[7] = new Tile("Wall7");
        tiles[7].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands10.png"));
        tiles[7].getCollisionBox().enableCollision(true);
        tiles[8] = new Tile("Wall8");
        tiles[8].setImage(ImageLoader.loadImage("/tiles/sprites/grasslands12.png"));
        tiles[8].getCollisionBox().enableCollision(true);
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            for(int row = 0; row < gp.getMaxWorldRows(); row++) {
                line = br.readLine();
                for(int col = 0; col < gp.getMaxWorldColumns(); col++) {
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
        for(int worldRow = 0; worldRow < gp.getMaxWorldRows(); worldRow++) {
            for(int worldCol = 0; worldCol < gp.getMaxWorldColumns(); worldCol++) {
                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol * gp.getTileSize();
                int worldY = worldRow * gp.getTileSize();

                if(insideScreen(worldX, worldY)) {
                    int screenX = worldX - gp.getPlayer().getWorldXPosition() + gp.getPlayer().getScreenXPosition();
                    int screenY = worldY - gp.getPlayer().getWorldYPosition() + gp.getPlayer().getScreenYPosition();
                    g2.drawImage(tiles[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                    CollisionBox cBox = tiles[tileNum].getCollisionBox();
                    if(cBox.hasCollision()) {
                        g2.setColor(Color.RED);
                        g2.drawRect(screenX, screenY, cBox.getWidth(), cBox.getHeight());
                    }
                }
            }
        }
    }

    public boolean insideScreen(int worldX, int worldY) {
        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldXPosition() - gp.getPlayer().getScreenXPosition() && 
            worldX - gp.getTileSize() < gp.getPlayer().getWorldXPosition() + gp.getPlayer().getScreenXPosition() && 
            worldY + gp.getTileSize() > gp.getPlayer().getWorldYPosition() - gp.getPlayer().getScreenYPosition() && 
            worldY - gp.getTileSize() < gp.getPlayer().getWorldYPosition() + gp.getPlayer().getScreenYPosition()) {
            return true;
        }
        return false;
    }
}
