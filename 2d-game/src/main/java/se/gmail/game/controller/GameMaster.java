package se.gmail.game.controller;

import java.util.ArrayList;

import se.gmail.game.model.entities.Enemy;
import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.object.equipment.consumables.potions.HealthPotion;
import se.gmail.game.model.object.equipment.weapons.melee.spears.WoddenSpear;
import se.gmail.game.model.object.equipment.weapons.ranged.bows.WoddenBow;
import se.gmail.game.view.UtilView;

public class GameMaster {

    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<GameObject> objects = new ArrayList<>();

    public GameMaster() {
        for(int i = 0; i < 3; i++) {
            spawnEnemy(new Enemy(), (int)(UtilView.screenWidth/2), (int)(UtilView.screenHeight/2));
        }
        setupWorld();
    }

    public void setupWorld() {
        HealthPotion hPotion = new HealthPotion();
        hPotion.setWorldPosition(5 * UtilView.tileSize, 5 * UtilView.tileSize);
        WoddenBow wBow = new WoddenBow();
        wBow.setWorldPosition(6 * UtilView.tileSize, 6 * UtilView.tileSize);
        WoddenSpear wSpear = new WoddenSpear();
        wSpear.setWorldPosition(7 * UtilView.tileSize, 6 * UtilView.tileSize);
        objects.add(hPotion);
        objects.add(wBow);
        objects.add(wSpear);
    }

    public void updateWorld() {
        for (Enemy e : enemies) {
            e.update();
            e.getAnimator().update();
            if(e.getWorldXPosition() > UtilView.screenWidth) {
                e.setWorldXPosition(UtilView.screenWidth-1);
            } 
            if(e.getWorldXPosition() < 0) {
                e.setWorldXPosition(1);
            }
            if(e.getWorldYPosition() > UtilView.screenHeight) {
                e.setWorldYPosition(UtilView.screenHeight-1);
            } 
            if(e.getWorldYPosition() < 0) {
                e.setWorldYPosition(1);
            }
        }
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static ArrayList<GameObject> getObjects() {
        return objects;
    }

    public static void spawnEnemy(Enemy e, int xPos, int yPos) {
        e.setWorldPosition(xPos, yPos);
        enemies.add(e);
    }

    public static void removeEnemy(Enemy e) {
        enemies.remove(e);
    }
}
