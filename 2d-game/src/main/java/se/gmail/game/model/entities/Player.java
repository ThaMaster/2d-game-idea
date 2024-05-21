package se.gmail.game.model.entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import se.gmail.game.util.ImageLoader;

public class Player extends Entity {

    private double health;
    private double mana;

    private ArrayList<BufferedImage> idleImages;

    public Player() {
        idleImages = new ArrayList<>();
        setPlayerDefaultValues();
        loadPlayerImages();
    }

    /**
     * Can only load the images from the absolute path, which is not good at all!
     */
    private void loadPlayerImages() {
        idleImages.add(ImageLoader.loadImage("/player_sprites/idle/_idle1.png"));
    }

    public void setPlayerDefaultValues() {
        this.health = 100.0;
        this.mana = 50.0;
        this.setSpeed(4);
        this.setPosition(1, 1);
    }

    public BufferedImage getImage() {
        return idleImages.get(0);
    }
}
