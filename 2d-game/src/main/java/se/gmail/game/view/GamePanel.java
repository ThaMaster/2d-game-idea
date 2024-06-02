package se.gmail.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.object.equipment.consumables.potions.HealthPotion;
import se.gmail.game.model.object.equipment.weapons.melee.spears.WoddenSpear;
import se.gmail.game.model.object.equipment.weapons.ranged.bows.WoddenBow;
import se.gmail.game.model.tiles.TileManager;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS
    final int originalTileSize = 80;
    final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 12;
    private final int maxScreenRow = 10;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    private Player player;

    private final int pScreenX = screenWidth/2 - (tileSize/2);
    private final int pScreenY = screenHeight/2 - (tileSize/2);

    private TileManager tileManager = new TileManager(this);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<GameObject> objects = new ArrayList<>();

    private int hotbarSelection;

    public GamePanel(Player p) {
        this.player = p;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        for(int i = 0; i < 3; i++) {
            Enemy e = new Enemy();
            e.setWorldPosition((int)(screenWidth/2), (int)(screenHeight/2));
            this.enemies.add(e);
        }

        this.player.setScreenPosition(pScreenX, pScreenY);
        this.player.setWorldPosition((int)(screenWidth/2), (int)(screenHeight/2));
        setupGame();
    }

    public void setupGame() {
        HealthPotion hPotion = new HealthPotion();
        hPotion.setWorldPosition(5 * tileSize, 5 * tileSize);
        WoddenBow wBow = new WoddenBow();
        wBow.setWorldPosition(6 * tileSize, 6 * tileSize);
        WoddenSpear wSpear = new WoddenSpear();
        wSpear.setWorldPosition(7 * tileSize, 6 * tileSize);
        objects.add(hPotion);
        objects.add(wBow);
        objects.add(wSpear);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        synchronized(g2) {
            tileManager.draw(g2);

            for(int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);
                int screenX = obj.getWorldXPosition() - player.getWorldXPosition() + player.getScreenXPosition();
                int screenY = obj.getWorldYPosition() - player.getWorldYPosition() + player.getScreenYPosition();
                obj.setScreenPosition(screenX, screenY);
                if(tileManager.insideScreen(obj.getWorldXPosition(), obj.getWorldYPosition())) {
                    obj.draw(g2);
                    obj.drawCollisionBox(g2);
                    if(obj.getCollisionBox().checkCollision(player)) {
                        obj.onPickup(player);
                        objects.remove(obj);
                    }
                }
            }
            
            player.draw(g2);
            player.drawCollisionBox(g2);

            for(Enemy e : enemies) {
                int screenX = e.getWorldXPosition() - player.getWorldXPosition() + player.getScreenXPosition();
                int screenY = e.getWorldYPosition() - player.getWorldYPosition() + player.getScreenYPosition();
                e.setScreenPosition(screenX, screenY);
                e.update();
                e.getAnimator().update();
                // Prevent enemies going outside the screen, for now...
                if(e.getWorldXPosition() > screenWidth) {
                    e.setWorldXPosition(screenWidth-1);
                } 
                if(e.getWorldXPosition() < 0) {
                    e.setWorldXPosition(1);
                }
                if(e.getWorldYPosition() > screenHeight) {
                    e.setWorldYPosition(screenHeight-1);
                } 
                if(e.getWorldYPosition() < 0) {
                    e.setWorldYPosition(1);
                }

                if(tileManager.insideScreen(e.getWorldXPosition(), e.getWorldYPosition())) {
                    e.draw(g2);
                    e.drawCollisionBox(g2);
                }
            }

            // for(int i = 1; i <= 9; i++) {
            //     g2.setColor(new Color(128, 128, 128, 127));
            //     g2.fillRect((i+1)*tileSize, screenHeight-tileSize, tileSize, tileSize);
            //     if(i == hotbarSelection) {
            //         g2.setColor(Color.WHITE);
            //     } else {
            //         g2.setColor(Color.BLACK);
            //     }
            //     g2.drawRect((i+1)*tileSize, screenHeight-tileSize, tileSize, tileSize);
            //     g2.drawString(String.valueOf(i), (i+1)*tileSize, screenHeight);
            //     if(player.getInventory()[i-1] != null) {
            //         player.getInventory()[i-1].draw(g2, (i+1)*tileSize, screenHeight-tileSize);
            //     }
            // }
        }
    }

    public void setHotbarSelection(int selection) {
        this.hotbarSelection = selection;
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

    public int getMaxWorldColumns() {
        return this.maxWorldCol;
    }

    public int getMaxWorldRows() {
        return this.maxWorldRow;
    }

    public Player getPlayer() {
        return this.player;
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    public void addGameObject(GameObject gObj) {
        this.objects.add(gObj);
    }
}
