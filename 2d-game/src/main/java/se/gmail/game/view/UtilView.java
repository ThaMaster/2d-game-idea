package se.gmail.game.view;

public class UtilView {

    // SCREEN SETTINGS
    public static final int originalTileSize = 80;
    public static final int scale = 1;

    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 12;
    public static final int maxScreenRow = 10;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int worldWidth = tileSize * maxWorldCol;
    public static final int worldHeight = tileSize * maxWorldRow;

    public static final int pScreenX = screenWidth/2 - (tileSize/2);
    public static final int pScreenY = screenHeight/2 - (tileSize/2);
}
